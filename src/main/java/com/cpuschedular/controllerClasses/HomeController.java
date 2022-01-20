package main.java.com.cpuschedular.controllerClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import main.java.com.cpuschedular.view.Comparison;
import main.java.com.cpuschedular.datastructure.ProcessTableRow;
import main.java.com.cpuschedular.Main;
import main.java.com.cpuschedular.view.Result;

public class HomeController {
    ObservableList<ProcessTableRow> processTableRowObsList;
    public TableView<ProcessTableRow> PTable;
    public TableColumn<ProcessTableRow, Float> Arrival, Burst;
    public TableColumn<ProcessTableRow, String> PID;
    public TextField arrivalTF, burstTF, QT_TF;
    public HBox QT_Hbox;
    public float QT;
    public ChoiceBox<String> algorithmCB;

    public void Ini(String algorithmsList[]) {
        ObservableList cbitems = FXCollections.observableArrayList(algorithmsList);
        algorithmCB.setItems(cbitems);
        QT_Hbox.setVisible(false);
        algorithmCB.getSelectionModel().selectedItemProperty().addListener(event -> {
            //round Robin QuantumTime TF enable when selecting roundrobin in choicebox or comparison
            if (algorithmCB.getSelectionModel() != null && (algorithmCB.getSelectionModel().isSelected(3) | algorithmCB.getSelectionModel().isSelected(4))) {
                QT_Hbox.setVisible(true);

            } else
                QT_Hbox.setVisible(false);

        });
        processTableRowObsList = FXCollections.observableArrayList();
        TableviewIni();
    }

    public void TableviewIni() {

        Arrival.setCellValueFactory((new PropertyValueFactory<>("arrival")));
        Burst.setCellValueFactory((new PropertyValueFactory<>("burst")));
        PID.setCellValueFactory((new PropertyValueFactory<>("PID")));
        PTable.setItems(processTableRowObsList);
    }

    public void Add() {
        //getinput
        float arrival = 0, burst = 0;
        try {
            arrival = Float.parseFloat(arrivalTF.getText());
            burst = Float.parseFloat(burstTF.getText());

        } catch (Exception e) {
            Main.AlertShow(e.getMessage());
            ClearTF();
            return;

        }
        processTableRowObsList.add(new ProcessTableRow(Integer.toString(processTableRowObsList.size() + 1), arrival, burst));
        ClearTF();
        arrivalTF.requestFocus();//to autoselect arrival textfield
    }

    public void Clear() {
        ClearTF();
        processTableRowObsList.clear();
    }

    public void ClearTF() {
        arrivalTF.clear();
        burstTF.clear();
    }

    public void Run() {
        int algno = 0;
        if (algorithmCB.getSelectionModel().getSelectedItem() == null) {
            Main.AlertShow("Please Select algorithm First");
            return;
        } else {

            algno = algorithmCB.getSelectionModel().getSelectedIndex() + 1;//plus one bec it stats at zero index
            if (algno == 5)//show comparison bar
            {
                Comparison comparisonstage = new Comparison(processTableRowObsList, Integer.parseInt(QT_TF.getText()));
                comparisonstage.Show();

            } else {
                Result result = new Result(processTableRowObsList, algno);
                if (algno == 4) {
                    result.setQT(Integer.parseInt(QT_TF.getText()));

                } else
                    result = new Result(processTableRowObsList, algno);
                result.Show();
            }
        }
//
//        Data dataarray[]=(Data[]) dataObsList.toArray();
//        Queue<Process> processQueue=CpuScheduler.input(algno,dataarray);
//        CpuScheduler.SRTF(processQueue);
    }


}
