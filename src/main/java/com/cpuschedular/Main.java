package main.java.com.cpuschedular;

import main.java.com.cpuschedular.view.Home;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage MainStage;
   public static Home home;

    @Override
    public void start(Stage primaryStage) throws Exception{
       MainStage=primaryStage;
       home=new Home();

    }
    public static void AlertShow(String message)
    {
        Alert alert=new Alert(Alert.AlertType.ERROR,message);
        alert.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
