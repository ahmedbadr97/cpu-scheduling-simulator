package main.java.com.cpuschedular.controllerClasses;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import main.java.com.cpuschedular.datastructure.Process;

public class ResultController {
    ObservableList<Process> resultsObservableList;
    public TableView<Process> PTable;
    public TableColumn<Process, Float> Arrival, Burst, waitingTime, turnAround;
    public TableColumn<Process, String> PID;
    public Label avgW_LB, avgT_LB, title;

    public void Ini(ObservableList<Process> resultsObservableList, float avgW, float avgT, String AlgorithName) {
        title.setText(AlgorithName);
        this.resultsObservableList = resultsObservableList;
        avgW_LB.setText(Float.toString(avgW));
        avgT_LB.setText(Float.toString(avgT));
        TableviewIni();

    }

    public void TableviewIni() {
        PID.setCellValueFactory((new PropertyValueFactory<>("processID")));
        Arrival.setCellValueFactory((new PropertyValueFactory<>("arrival")));
        Burst.setCellValueFactory((new PropertyValueFactory<>("burst")));
        waitingTime.setCellValueFactory((new PropertyValueFactory<>("waitTime")));
        turnAround.setCellValueFactory((new PropertyValueFactory<>("turnaround")));
        PTable.setItems(resultsObservableList);
    }
}
