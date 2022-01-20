package main.java.com.cpuschedular.controllerClasses;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import main.java.com.cpuschedular.view.Result;

import java.text.DecimalFormat;

public class ComparisonController {
    Result results[];
    @FXML
    private BarChart algchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    public void Ini(Result results[]) {
        this.results = results;
        XYChart.Series dataSeries1 = new XYChart.Series<>();
        XYChart.Series dataSeries2 = new XYChart.Series<>();
        DecimalFormat df = new DecimalFormat("##.##");
        System.out.println(df.format(results[0].avgT));


        dataSeries1.getData().add(new XYChart.Data("FCFS", results[0].avgT));
        dataSeries1.getData().add(new XYChart.Data("SJF", results[1].avgT));
        dataSeries1.getData().add(new XYChart.Data("SRJF", results[2].avgT));
        dataSeries1.getData().add(new XYChart.Data("RR", results[3].avgT));
        algchart.getData().add(dataSeries1);
        dataSeries1.setName("Avg(TurnAround)");
        dataSeries2.getData().add(new XYChart.Data("FCFS", results[0].avgW));
        dataSeries2.getData().add(new XYChart.Data("SJF", results[1].avgW));
        dataSeries2.getData().add(new XYChart.Data("SRJF", results[2].avgW));
        dataSeries2.getData().add(new XYChart.Data("RR", results[3].avgW));
        dataSeries2.setName("Avg(Wating Time)");
        algchart.getData().add(dataSeries2);

    }

}
