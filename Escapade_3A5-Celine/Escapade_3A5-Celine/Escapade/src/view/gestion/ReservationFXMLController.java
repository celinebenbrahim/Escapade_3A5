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
public class ReservationFXMLController implements Initializable {

    private Button Home;
    private Button Profil;
    private Button Reclamation;
    private Button Reservation;
    @FXML
    private Button Home1;
    @FXML
    private Button Hotel;
    @FXML
    private Button MoyT;
    @FXML
    private Button Chambre;
    @FXML
    private Button btnSignout1;
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

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void Hotel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DestinationFXML.fxml"));
        Parent root = loader.load();
        DestinationFXMLController controller = loader.getController();
        System.out.println("idCLient2="+id);
        controller.setId(id);
        controller.afficher();
        Hotel.getScene().setRoot(root);
    }

    @FXML
    private void MoyT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        MoyT.getScene().setRoot(root);
    }

    @FXML
    private void Chambre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Chambre.getScene().setRoot(root);

    }

}
