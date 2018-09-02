package com.viewsAndControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewSceneMaker {


    //1. Create a method that accepts a new ActionEvent, title of the new scene and the .fxml file with which to load the
    // new scene.
    public void newScene(ActionEvent newSceneButton, String sceneTitle, String title) throws IOException {
        FXMLLoader loader =  new FXMLLoader();
        loader.setLocation(getClass().getResource(sceneTitle));
        Parent parent = loader.load();


        Scene scene = new Scene(parent);

        // 2. Retrieve from the even the scene that was passed via the new ActionEvent.

        Stage stage = (Stage) ((Node)newSceneButton.getSource()).getScene().getWindow();

        // 3. Apply the title to the stage
        stage.setTitle(title);

        // 4. Apply the scene to the stage
        stage.setScene(scene);

        //5. Pull the curtain and start the show.
        stage.show();
    }


}
