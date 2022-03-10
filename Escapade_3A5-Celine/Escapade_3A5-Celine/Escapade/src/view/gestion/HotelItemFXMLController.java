/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class HotelItemFXMLController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label nbEtoile;
    @FXML
    private Label description;
    private Hotel hotel;
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
    public void setData(Hotel h) {
        this.hotel = h;
        this.nom.setText(hotel.getNom());
        String s="";
        for (int i = 0; i < hotel.getNbrEtoile(); i++) {
            s+="*";   
        }
        this.nbEtoile.setText(s);
//        Image img = new Image(getClass().getResourceAsStream("..\\..\\..\\..\\..\\..\\Desktop\\1.png"));
//        image.setImage(img);
        File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\" + hotel.getImg());
     
        image.setImage(new Image(f.toURI().toString()));
        
        

    }

    @FXML
    private void afficherChambre(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/gestion/ChambreFXML.fxml"));
        Parent root = fxmlLoader.load();
        ChambreFXMLController controller = fxmlLoader.getController();
        controller.setId(id);
        controller.setIdHotel(hotel.getIdHotel());
        System.out.println("idCLient"+id);
        //controller.setIdDestination(idDestination);
        //controller.afficher();
        image.getScene().setRoot(root);
    }
}
