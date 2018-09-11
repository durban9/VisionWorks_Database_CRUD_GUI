package com.viewsAndControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.models.Developer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewDeveloperController implements Initializable, ControllerClass{


    @FXML private TextField developerEmployeeNumber;
    @FXML private TextField developerFirstName;
    @FXML private TextField developerLastName;
    @FXML private DatePicker developerBirthdate;
    @FXML private Label errMsgLabel;
    @FXML private Label createNewUserLabel;
    @FXML private Developer developer;
    @FXML private Button createNewUserButton;


    public NewDeveloperController() {
    }

    //create a method for the submit button on the NewDeveloperScene
    public void createNewUserButton(ActionEvent event)  {

        try{

            if (developer != null){
                updateDeveloper();
                developer.updateDB();
                NewSceneMaker nsm = new NewSceneMaker();
                nsm.newScene(event, "ViewDeveloperList.fxml", "VisionWorks Database CRUD GUI");
            }else {
                //first, create an instance of the Developer class called developer, which retrieves the private
                // attributes
                // of the developer class using the Developer class' getters.
                Developer developer = new Developer(developerEmployeeNumber.getText(), developerFirstName.getText(),
                        developerLastName.getText(), developerBirthdate.getValue());


                //sets the error message to blank
                errMsgLabel.setText("");

                //inserts the newly created developer instance and inserts the instance into the database.
                developer.insertIntoDB();


                NewSceneMaker nsm = new NewSceneMaker();
                nsm.newScene(event, "EditDeveloper.fxml", "VisionWorks Database CRUD GUI");

            }
        }
            catch (Exception e) {
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

    @Override
    public void preloadData(Developer developer){
        this.developer = developer;
        this.developerEmployeeNumber.setText(developer.getEmployeeNumber());
        this.developerFirstName.setText(developer.getEmployeeFirstName());
        this.developerLastName.setText(developer.getEmployeeLastName());
        this.developerBirthdate.setValue(developer.getBirthday());
        this.createNewUserLabel.setText("Edit Developer");
        this.createNewUserButton.setText("Save Edit to Database");
    }

    private void updateDeveloper() throws SQLException {
        developer.setEmployeeNumber(developerEmployeeNumber.getText());
        developer.setFirstName(developerFirstName.getText());
        developer.setLastName(developerLastName.getText());
        developer.setBirthday(developerBirthdate.getValue());

    }
}
