package com.viewsAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatabaseAdministratorHome implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void newDeveloperButton(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "NewDeveloper.fxml", "VisionWorks Database CRUD GUI");

    }



    public void editDeveloperButton (ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "EditDeveloper.fxml", "VisionWorks Database CRUD GUI");
    }


    public void viewDevelopersButton(ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "ViewDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }
}
