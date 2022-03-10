/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
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

    private Button inscrire;
    private Button authentifier;
    private Button Back;
    @FXML
    private Button btnSignout;
    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void Conx(ActionEvent event) throws IOException {

    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Acceuil4FXMLController controller = loader.getController();
        controller.setId(id);
        Home.getScene().setRoot(root);

    }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilFXML.fxml"));
        Parent root = loader.load();
        ProfilFXMLController controller = loader.getController();
        controller.setId(id);
        controller.Profil();
        Profil.getScene().setRoot(root);

    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReclamationFXML.fxml"));
        Parent root = loader.load();
        ReclamationFXMLController controller = loader.getController();
        controller.setId(id);
        Reclamation.getScene().setRoot(root);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReservationFXML.fxml"));
        Parent root = loader.load();
        ReservationFXMLController controller = loader.getController();
        controller.setId(id);
        System.out.println("idCLient1="+id);
        Reservation.getScene().setRoot(root);

    }

}
