/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class EscapadeMain extends Application {

    Parent parent;
    Stage stage;

    @Override
    public void start(Stage primaryStage) {

        this.stage = primaryStage;
        try {

            // parent = FXMLLoader.load(getClass().getResource("/view/gestion/DestinationFXML.fxml"));
            parent = FXMLLoader.load(getClass().getResource("/view/gestion/BackFXML.fxml"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Escapade");
        stage.show();
    }

    /**
     * @param args the command line
     *
     */
    public static void main(String[] args) {
        launch(args);
    }

}
