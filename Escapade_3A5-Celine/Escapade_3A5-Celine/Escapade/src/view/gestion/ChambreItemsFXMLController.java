/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Hotel;
import gestionUserReclamation.entities.Session;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private int id;
    @FXML
    private AnchorPane ch;

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

}
