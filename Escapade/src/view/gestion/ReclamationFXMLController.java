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
 * @author asus
 */
public class ReclamationFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    @FXML
    private Button btnSignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void Home(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilFXML.fxml"));
        Parent root = loader.load();
        Profil.getScene().setRoot(root);
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReclamationFXML.fxml"));
        Parent root = loader.load();
        Reclamation.getScene().setRoot(root);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReservationFXML.fxml"));
        Parent root = loader.load();
        Reservation.getScene().setRoot(root);
        
    }
}
