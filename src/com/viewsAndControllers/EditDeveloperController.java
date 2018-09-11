package com.viewsAndControllers;

import com.models.Developer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditDeveloperController implements Initializable {



    @FXML private TextField developerEmployeeNumber;
    @FXML private TextField developerFirstName;
    @FXML private TextField developerLastName;
    @FXML private DatePicker developerBirthdate;
    @FXML private Label errMsgLabel;
    @FXML private Label createNewUserLabel;
    @FXML private Developer developer;


    public EditDeveloperController() {
    }

    //create a method for the submit button on the NewDeveloperScene
    public void editUserButton(ActionEvent event)  {

        try{
            //first, create an instance of the Developer class called developer, which retrieves the private attributes
            // of the developer class using the Developer class' getters.
            Developer developer = new Developer(developerEmployeeNumber.getText(),developerFirstName.getText(),
                    developerLastName.getText(), developerBirthdate.getValue());


            //sets the error message to blank
            errMsgLabel.setText("");

            //inserts the newly created developer instance and inserts the instance into the database.
            developer.insertIntoDB();


            NewSceneMaker nsm = new NewSceneMaker();
            nsm.newScene(event, "EditDeveloper.fxml", "VisionWorks Database CRUD GUI");

        } catch (Exception e) {
            //retrieves the particular error message that pertains to the attribute,
            // should the attribute throw an exception
            errMsgLabel.setText(e.getMessage());
        }



    }

    public void homeButton(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();
        nsm.newScene(click, "DatabaseAdministratorHomeController.fxml", "VisionWorks CRUD GUI");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errMsgLabel.setText("");
    }

    public void viewDeveloperListButton(ActionEvent event) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(event, "ViewDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }

    public void editDeveloperButton(ActionEvent event) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();
        nsm.newScene(event, "EditDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }


    public void databaseAdministratorHomeButton(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "DatabaseAdministratorHome.fxml", "VisionWorks Database CRUD GUI");
    }

    public void deleteDeveloperButton(ActionEvent event) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(event, "EditDeveloper.fxml", "VisionWorks Database CRUD GUI");
    }

   // @Override
    public void preloadData(Developer developer){
        this.developer = developer;
        this.developerEmployeeNumber.setText(developer.getEmployeeNumber());
        this.developerFirstName.setText(developer.getEmployeeFirstName());
        this.developerLastName.setText(developer.getEmployeeLastName());
        this.developerBirthdate.setValue(developer.getBirthday());
        this.createNewUserLabel.setText("Edit Developer");
    }
}