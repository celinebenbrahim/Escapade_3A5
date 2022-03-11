/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author KattaX
 */
public class firstdashboard implements Initializable {
    User u = new User ();

    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AL(ActionEvent event) throws IOException {
         if ( u.getRole()=="admin") {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesAgences.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
         }
         else {
             AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/HomeAgence.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
         }
    }

    @FXML
    private void MDT(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesTransport.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }
    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/Chartdispo.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }
    
    
}
