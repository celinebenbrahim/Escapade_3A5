/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
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
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private AnchorPane dest;
    private int id;
    
    private int idDestination;

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

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

    public void setData(Destination dest) {
        this.destination = dest;
        idDestination=dest.getId();
        this.pays.setText(destination.getPays());
        this.ville.setText(destination.getVille());
//        Image img = new Image(getClass().getResourceAsStream("..\\..\\..\\..\\..\\..\\Desktop\\1.png"));
//        image.setImage(img);
        File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\" + destination.getImg());

        image.setImage(new Image(f.toURI().toString()));

    }

    @FXML
    private void showHotel(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/gestion/HotelFXML.fxml"));
        Parent root = fxmlLoader.load();
        HotelFXMLController controller = fxmlLoader.getController();
        controller.setId(id);
        System.out.println("idDest="+idDestination);
        controller.setIdDestination(idDestination);
        controller.afficher();
        dest.getScene().setRoot(root);

    }


}
