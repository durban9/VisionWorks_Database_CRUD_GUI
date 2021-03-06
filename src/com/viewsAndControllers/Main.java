package com.viewsAndControllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("DatabaseAdministratorHome.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("VisionWorks Database CRUD GUI");
        primaryStage.setScene(scene);

        primaryStage.show();
        System.out.print(getClass().getResource("NewDeveloper.fxml"));


    }


    public static void main(String[] args) {
        launch(args);


    }
}
