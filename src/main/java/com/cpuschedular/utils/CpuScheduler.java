package main.java.com.cpuschedular.utils;

import javafx.collections.ObservableList;
import main.java.com.cpuschedular.datastructure.MinHeap;

import main.java.com.cpuschedular.datastructure.ProcessTableRow;
import main.java.com.cpuschedular.datastructure.Process;
import java.util.LinkedList;
import java.util.Queue;

public class CpuScheduler {

    public static Queue<Process> SJF(Queue<Process> processQueue)//non premitive
    {
        MinHeap minHeap = new MinHeap(processQueue.size());
        Process currentProcess = null;
        float t;
        Queue<Process> ResultQueue = new LinkedList<>();
        for (int time = 0; !processQueue.isEmpty() | !minHeap.isEmpty() | currentProcess != null; time++) {
            if (currentProcess != null)
                currentProcess.DecRemainingTime();
            while (!processQueue.isEmpty() && time == processQueue.peek().getArrival()) {
                minHeap.Insert(processQueue.remove());
            }
            if (currentProcess != null && currentProcess.getRemainingT() == 0) //process finished it's work
            {
                //calculate the weight for the process and put it in the queue
                t = time - (currentProcess.getArrival() + currentProcess.getBurst());
                currentProcess.setWaitTime(t);
                currentProcess.setTurnaround(currentProcess.getWaitTime() + currentProcess.getBurst());
                ResultQueue.add(currentProcess);
                currentProcess = null;
            }
            if (currentProcess == null) {
                if (!minHeap.isEmpty())
                    currentProcess = minHeap.Remove();
                else
                    continue;
            }


        }
        return ResultQueue;

    }

    public static Queue<Process> FCFS(Queue<Process> processQueue) {

        float time = 0;//time increases by execution of each process by it's burst time
        Queue<Process> ResultQueue = new LinkedList<>();


        for (int i = 0; !processQueue.isEmpty(); i++) {
            Process currentProcess = processQueue.remove();
            currentProcess.setWaitTime(time - currentProcess.getArrival());
            currentProcess.setTurnaround(currentProcess.getWaitTime() + currentProcess.getBurst());
            time = time + currentProcess.getBurst();// increase the s time by the busrt time of the current process
            ResultQueue.add(currentProcess);

        }
        return ResultQueue;


    }

    public static Queue<Process> SRTF(Queue<Process> processQueue)//premitive
    {
        MinHeap minHeap = new MinHeap(processQueue.size());
        Process currentProcess = null;
        float t;
        Queue<Process> ResultQueue = new LinkedList<>();
        for (int time = 0; !processQueue.isEmpty() | !minHeap.isEmpty() | currentProcess != null; time++) {
            if (currentProcess != null)
                currentProcess.DecRemainingTime();
            while (!processQueue.isEmpty() && time == processQueue.peek().getArrival())
            //check if the queue not empty then check it's top the current time= the time of it's arrival or not
            {

                //if the cpu is idle then the minHeap is empty so remove the item from queue to current process directly

                if (currentProcess != null && processQueue.peek().getRemainingT() < currentProcess.getRemainingT()) {
                    minHeap.Insert(currentProcess);
                    currentProcess = processQueue.remove();
                } else
                    minHeap.Insert(processQueue.remove());

            }

            if (currentProcess != null && currentProcess.getRemainingT() == 0) //process finished it's work
            {
                //calculate the weight for the process and put it in the queue
                t = time - (currentProcess.getArrival() + currentProcess.getBurst());
                currentProcess.setWaitTime(t);
                currentProcess.setTurnaround(currentProcess.getWaitTime() + currentProcess.getBurst());
                ResultQueue.add(currentProcess);
                currentProcess = null;
                //check if there are waiting process take the min burst time if there is no process in the the minheap then leave the cpu idle

            }
            if (currentProcess == null) {
                if (minHeap.isEmpty()) {
                    currentProcess = null;
                    continue;
                } else
                    currentProcess = minHeap.Remove();
            }


        }
        return (ResultQueue);

    }

    public static Queue<Process> RR(Queue<Process> processQueue, int q) {
        Queue<Process> waitingQueue = new LinkedList<>();
        Process currentProcess = null;
        float t;
        int counter = q;
        Queue<Process> ResultQueue = new LinkedList<>();

        for (int time = 0; !processQueue.isEmpty() | !waitingQueue.isEmpty() | currentProcess != null; time++, counter--) {
            while (!processQueue.isEmpty() && time == processQueue.peek().getArrival()) {
                waitingQueue.add(processQueue.remove());
            }
            if (currentProcess != null && currentProcess.getRemainingT() == 0) //process finished it's work or threshold finished
            {

                //calculate the weight for the process and put it in the queue
                t = time - (currentProcess.getArrival() + currentProcess.getBurst());
                currentProcess.setWaitTime(t);
                currentProcess.setTurnaround(currentProcess.getWaitTime() + currentProcess.getBurst());
                ResultQueue.add(currentProcess);
                currentProcess = null;


            }

            if (currentProcess == null) {

                if (!waitingQueue.isEmpty()) {
                    currentProcess = waitingQueue.remove();
                    counter = q;
                } else
                    continue;
            }
            if (counter == 0 && !waitingQueue.isEmpty()) {
                waitingQueue.add(currentProcess);
                currentProcess = waitingQueue.remove();
                counter = q;
            }

            if (currentProcess != null)
                currentProcess.DecRemainingTime();
        }
        return (ResultQueue);
    }

    public static void print(Object obj) {
        System.out.println(obj);

    }

    public static void Results(Queue<Process> Resultsqueue) {
        String pname = "            ";
        String Weight = "Weights:    ";
        String Turnaround = "Turnaround: ";
        float sum = 0;
        float weight, t;
        int proccesssize = Resultsqueue.size();


        while (!Resultsqueue.isEmpty()) {
            Process currentprocess = Resultsqueue.remove();
            weight = currentprocess.getWaitTime();
            t = weight + currentprocess.getBurst();
            pname += currentprocess.getProcessID() + "  ";
            Weight += currentprocess.getWaitTime() + "  ";
            Turnaround = Turnaround + t + "  ";
            sum += t;


        }
        float avg = sum / proccesssize;
        print(pname);
        print(Weight);
        print(Turnaround);
        print("Avg(T)=" + avg);

    }

    public static Queue<Process> input(int algNo, ObservableList<ProcessTableRow> data) {
        Queue<Process> output = new LinkedList<>();
        for (int i = 0; i < data.size(); i++) {
            switch (algNo) {
                case 1:
                    output.add(new Process("P_" + Integer.toString(i + 1), data.get(i).getArrival(), data.get(i).getBurst()) {
                        @Override
                        public int compareTo(Process o) {
                            return 0;
                        }
                    });// parameters (Arrival,burst)
                    break;
                case 2:
                    output.add(new Process("P_" + Integer.toString(i + 1), data.get(i).getArrival(), data.get(i).getBurst()) {
                        @Override
                        public int compareTo(Process o) {
                            if (this.getBurst() < o.getBurst())
                                return -1;
                            else if (this.getBurst() > o.getBurst())
                                return 1;
                            return 0;
                        }
                    });// parameters (Arrival,burst)
                    break;
                case 3:
                    output.add(new Process("P_" + Integer.toString(i + 1), data.get(i).getArrival(), data.get(i).getBurst()) {
                        @Override
                        public int compareTo(Process o) {
                            if (this.getRemainingT() < o.getRemainingT())
                                return -1;
                            else if (this.getRemainingT() > o.getRemainingT())
                                return 1;
                            return 0;
                        }
                    });// parameters (Arrival,burst)
                    break;
                case 4:
                    output.add(new Process("P_" + Integer.toString(i + 1), data.get(i).getArrival(), data.get(i).getBurst()) {
                        @Override
                        public int compareTo(Process o) {
                            return 0;
                        }
                    });// parameters (Arrival,burst)
                    break;


            }

        }
        return output;
    }

}
