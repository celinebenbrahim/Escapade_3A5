/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class DestinationItemsFXMLController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label pays;
    @FXML
    private Label ville;
    private Destination destination;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setData(Destination dest) {
        this.destination = dest;
        this.pays.setText(destination.getPays());
        this.ville.setText(destination.getVille());
//        Image img = new Image(getClass().getResourceAsStream("..\\..\\..\\..\\..\\..\\Desktop\\1.png"));
//        image.setImage(img);
        File f = new File("C:\\Users\\AH-INFO\\Documents\\NetBeansProjects\\"
                + "Escapade\\src\\view\\ressources\\images\\" + destination.getImg());
     
        image.setImage(new Image(f.toURI().toString()));
        
        

    }

}
