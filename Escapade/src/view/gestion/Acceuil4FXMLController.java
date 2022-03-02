/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class Acceuil4FXMLController implements Initializable {
     @FXML
    private Button inscrire;
    @FXML
    private Button authentifier;
  @FXML
    private Button Back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AcceuilFXML.fxml"));
        Parent root = loader.load();
        Back.getScene().setRoot(root);
    }
    
    
     @FXML
    private void Conx(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("ConxFXML.fxml"));
        Parent root = loader.load();
        authentifier.getScene().setRoot(root);
    }
    
        
  @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("AjouterFXML.fxml"));
        Parent root = loader.load();
        inscrire.getScene().setRoot(root);
    }
    
    
}
