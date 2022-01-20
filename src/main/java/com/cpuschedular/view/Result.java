package main.java.com.cpuschedular.view;

import main.java.com.cpuschedular.utils.CpuScheduler;
import main.java.com.cpuschedular.datastructure.ProcessTableRow;
import main.java.com.cpuschedular.datastructure.Process;
import main.java.com.cpuschedular.controllerClasses.ResultController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Queue;

public class Result {
    Scene resultScene;
    FXMLLoader resultLoader;
    ResultController resultController;
    Stage resultStage;
    ObservableList<Process> resultsObservableList;
    Parent resultparent;
    ObservableList<ProcessTableRow> data;
    String algorithmName;
    public float totalWait, totalTN, avgW, avgT;
    int QT;

    Queue<Process> resultsqueue;
    int algno;

    public Result(ObservableList<ProcessTableRow> data, int algno) {
        totalWait = totalTN = avgW = avgT = QT = 0;
        this.data = data;
        this.algno = algno;

        try {
            resultLoader = new FXMLLoader(getClass().getResource("/main/resources/fxml/Result.fxml"));
            resultparent = resultLoader.load();
            resultController = resultLoader.getController();
            resultScene = new Scene(resultparent, 431, 349);
            resultStage = new Stage();
            resultStage.setTitle("Cpu Scheduling");
            resultStage.setScene(resultScene);
            resultStage.setResizable(false);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();

        }
    }

    public void RunAlgorithm() {
        resultsqueue = runAlgorithm(algno, data);
        //convert to from queue to obs list for the table and calculate avg
        resultsObservableList = fromQueuetoObservableList(resultsqueue);

    }

    public void Show() {
        RunAlgorithm();
        resultController.Ini(resultsObservableList, avgW, avgT, algorithmName);
        resultStage.show();
    }

    public ObservableList fromQueuetoObservableList(Queue<Process> queue) {
        ObservableList result = FXCollections.observableArrayList();
        while (!queue.isEmpty()) {
            Process p = queue.remove();
            totalWait += p.getWaitTime();
            totalTN += p.getWaitTime() + p.getBurst();
            result.add(p);
        }
        avgW = totalWait / result.size();
        avgT = totalTN / result.size();
        return result;
    }

    public Queue<Process> runAlgorithm(int algno, ObservableList<ProcessTableRow> data)//returns Result in Queue
    {
        Queue<Process> processQueue;
        //input takes the data array and the algorithm type to override compare to function and returns queue of the proccess
        switch (algno) {
            case 1:
                processQueue = CpuScheduler.input(1, data);
                algorithmName = "First Come First Serve";

                return CpuScheduler.FCFS(processQueue);
            case 2:
                processQueue = CpuScheduler.input(2, data);
                algorithmName = "Shortest Job First";
                return CpuScheduler.SJF(processQueue);


            case 3://comparing with Remaining Time
                processQueue = CpuScheduler.input(3, data);
                algorithmName = "Shortest Remaining Job First";


                return CpuScheduler.SRTF(processQueue);

            case 4:
                processQueue = CpuScheduler.input(4, data);
                algorithmName = "Round Robin";
                //print("Q:");

                return CpuScheduler.RR(processQueue, QT);


        }
        return null;
    }

    public void setQT(int QT) {
        this.QT = QT;
    }
}
