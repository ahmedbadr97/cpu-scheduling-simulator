package main.java.com.cpuschedular.datastructure;

public abstract class Process implements Comparable<Process> {
    private float arrival, burst, remainingT, waitTime, turnaround;
    private String processID;
    public static float Totaltime = 0;

    public Process(String processID, float arrival, float burst) {
        this.arrival = arrival;
        this.burst = burst;
        this.remainingT = burst;
        Totaltime += this.burst;
        this.waitTime = 0;
        this.processID = processID;

    }

    public float getArrival() {
        return arrival;
    }

    public void setArrival(float arrival) {
        this.arrival = arrival;
    }

    public float getBurst() {
        return burst;
    }

    public void DecRemainingTime() {
        remainingT--;
    }

    public float getRemainingT() {
        return remainingT;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public String getProcessID() {
        return processID;
    }

    public void setWaitTime(float waitTime) {
        this.waitTime = +waitTime;

    }

    public float getTurnaround() {
        return turnaround;
    }

    public void setTurnaround(float turnaround) {
        this.turnaround = turnaround;
    }
}
