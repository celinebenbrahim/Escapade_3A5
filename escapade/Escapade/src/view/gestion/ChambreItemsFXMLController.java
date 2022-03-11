/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Hotel;
import gestionUserReclamation.entities.ReservationChambre;
import gestionUserReclamation.entities.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ChambreItemsFXMLController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label Desc;
    @FXML
    private Label Prix;

    private Chambre chambre;

    @FXML
    private AnchorPane ch;
    @FXML
    private Button reserver;
    private Date dateAller;
    private Date dateRetour;

    public Date getDateAller() {
        return dateAller;
    }

    public void setDateAller(Date dateAller) {
        this.dateAller = dateAller;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    private int idHotel;

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showChambre(MouseEvent event) {

    }

    public void setData(Chambre ch) {
        this.chambre = ch;
        this.Desc.setText(chambre.getDescription());
        this.Prix.setText(String.valueOf(chambre.getPrixNuit()));
//        Image img = new Image(getClass().getResourceAsStream("..\\..\\..\\..\\..\\..\\Desktop\\1.png"));
//        image.setImage(img);
        File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\" + chambre.getImg());

        image.setImage(new Image(f.toURI().toString()));

    }

    @FXML
    private void reserver(ActionEvent event) throws IOException, ParseException, SQLException {
        ReservationChambre reservation = new ReservationChambre(Session.getIdUser(),chambre.getId(), dateAller, dateRetour);
         FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/gestion/FactureFrontFXML.fxml"));
                Parent root = fxmlLoader.load();
                FactureFrontFXMLController Controller = fxmlLoader.getController();
                Controller.setReservation(reservation);
                Controller.afficher();
                reserver.getScene().setRoot(root);
                
                
        
    }

}
