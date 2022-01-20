package main.java.com.cpuschedular.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import main.java.com.cpuschedular.Main;
import main.java.com.cpuschedular.controllerClasses.HomeController;

public class Home {

    Scene homeScene;
    FXMLLoader homeLoader;
    HomeController homeController;
    Parent homeparent;

    public Home() {
        try {
            homeLoader = new FXMLLoader(getClass().getResource("/main/resources/fxml/Home.fxml"));

            String[] algorithms = {"First Come First Serve", "Shortest Job First", "Shortest Remaining Job First", "Round Robin", "Comparison Chart"};
            homeparent = homeLoader.load();
            homeController = homeLoader.getController();
            homeController.Ini(algorithms);
            homeScene = new Scene(homeparent, 600, 372);
            Main.MainStage.setTitle("Cpu Scheduling");
            Main.MainStage.setScene(homeScene);
            Main.MainStage.setResizable(false);
            Main.MainStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();

        }

    }
}
