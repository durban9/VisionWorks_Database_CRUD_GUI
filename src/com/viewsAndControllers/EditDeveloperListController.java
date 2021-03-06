package com.viewsAndControllers;

import com.models.Developer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;




public class EditDeveloperListController implements Initializable {

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


    private Developer developer;

    @FXML
    private Button editDeveloperButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //3. Tell the columns created above where to access the values that are to be displayed in the TableView
        developerIDColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeNumber"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeFirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Developer, String>("employeeLastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Developer, LocalDate>("birthday"));
        this.editDeveloperButton.setDisable(true);

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

    public void createDeveloperButton(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "NewDeveloper.fxml", "VisionWorks Database CRUD GUI");
    }

    public void viewDeveloperButton(ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "ViewDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }

    public void homeButton(ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "DatabaseAdministratorHome.fxml", "VisionWorks Database CRUD GUI");
    }

    public void editDeveloperButton (ActionEvent click) throws IOException{
        NewSceneMaker nsm = new NewSceneMaker();

        nsm.newScene(click, "EditDeveloperList.fxml", "VisionWorks Database CRUD GUI");
    }

    public void loadData(Developer developer){
        this.developer = developer;
        this.firstNameColumn.setText(developer.getEmployeeFirstName());
        this.lastNameColumn.setText(developer.getEmployeeLastName());
        this.developerIDColumn.setText(developer.getEmployeeNumber());
        this.birthdayColumn.setText(String.valueOf(developer.getBirthday()));


    }

    public void developerToBeEdited(){
        editDeveloperButton.setDisable(false);
    }

    public void editDeveloperButtonPushed(ActionEvent click) throws IOException {
        NewSceneMaker nsm = new NewSceneMaker();
        Developer developer = (Developer) this.developerTableView.getSelectionModel().getSelectedItem();
        NewDeveloperController ndc = new NewDeveloperController();
        nsm.newScene(click, "NewDeveloper.fxml", "VisionWorks CRUD GUI", developer, ndc);
    }

}

