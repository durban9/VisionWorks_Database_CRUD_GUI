package com.viewsAndControllers;

import com.models.Developer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewDeveloperListController implements Initializable{


    // 1. Set up the table that will display volunteers and the corresponding columns
    // 2. Assign the columns to the appropriate fxml column and in scenebuilder.

    @FXML
    private TableView developerTableView;

    @FXML
    private TableColumn<Developer, String> developerIDColumn;

    @FXML
    private TableColumn<Developer, String> firstNameColumn;

    @FXML
    private TableColumn<Developer, String> lastNameColumn;

    @FXML
    private TableColumn<Developer, LocalDate> birthdayColumn;

    public ViewDeveloperListController(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //3. Tell the columns created above where to access the values that are to be displayed in the TableView
        developerIDColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeFirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeLastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Developer, LocalDate>("birthday"));

        //4. Load data
        try {

            loadVolunteers();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void loadVolunteers() throws SQLException {
        ObservableList<Developer> developers = FXCollections.observableArrayList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/visionworksframeinventory",
                            "root", "VisionWorks!");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM developer");

            while (resultSet.next()){
                Developer developerSet = new Developer(resultSet.getString("employeeNumber"),
                        resultSet.getString("employeeFirstName"),
                        resultSet.getString("employeeLastName"),
                        resultSet.getDate("birthday").toLocalDate());
                developers.add(developerSet);

            }
            developerTableView.getItems().addAll(developers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }

    }

    public void editDeveloperButton (ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "ViewDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }

    public void createDeveloperButton(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "NewDeveloper.fxml", "VisionWorks Database CRUD GUI");
    }

    public void homeButton(ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "DatabaseAdministratorHome.fxml", "VisionWorks Database CRUD GUI");


    }

}
