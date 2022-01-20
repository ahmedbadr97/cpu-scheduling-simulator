package main.java.com.cpuschedular.view;

import main.java.com.cpuschedular.controllerClasses.ComparisonController;
import main.java.com.cpuschedular.datastructure.ProcessTableRow;
import main.java.com.cpuschedular.Main;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Comparison {
    Scene compareScene;
    FXMLLoader compareLoader;
    ComparisonController compareController;
    Stage compareStage;
    Parent compareparent;
    ObservableList<ProcessTableRow> data;
    Result results[];
    int QT;

    public Comparison(ObservableList<ProcessTableRow> data, int QT) {
        this.data = data;
        compareLoader = new FXMLLoader(getClass().getResource("/main/resources/fxml/Comparison.fxml"));
        try {
            compareparent = compareLoader.load();
            compareController=compareLoader.getController();
        } catch (Exception e) {
            Main.AlertShow(e.getMessage());
        }

        compareScene = new Scene(compareparent, 800, 600);
        compareStage = new Stage();
        compareStage.setTitle("Cpu Scheduling Chart");
        compareStage.setScene(compareScene);
        this.QT = QT;
        compareStage.setResizable(false);

    }

    public void Show() {
        CalculateResult();
        compareController.Ini(results);
        compareStage.show();

    }

    public void CalculateResult() {
        results = new Result[4];
        for (int alg = 1; alg <= 4; alg++) {
            results[alg - 1] = new Result(data, alg);
            if (alg == 4) {
                results[alg - 1].QT = this.QT;
            }
            results[alg - 1].RunAlgorithm();


        }
    }
}
