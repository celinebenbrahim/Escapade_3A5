/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.HotelService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class HotelFXMLController implements Initializable {

    private Button Back;
    private Button Next;
    @FXML
    private Button Home;
    @FXML
    private Button Hotel;
    @FXML
    private Button MoyT;
    @FXML
    private Button Chambre;
    @FXML
    private Button btnSignout;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<Hotel> listeHotel = new ArrayList<>();
    HotelService hs = new HotelService();

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

    @FXML
    private void Home(ActionEvent event) {
    }

    @FXML
    private void Hotel(ActionEvent event) {
    }

    @FXML
    private void MoyT(ActionEvent event) {
    }

    @FXML
    private void Chambre(ActionEvent event) {
    }
    
    public void afficher(){
        try {
            // TODO
            System.out.println("aaa"+id);
            listeHotel = hs.afficherByDestination(idDestination);
            System.out.println(listeHotel);

            int column = 0;
            int row = 1;
            for (int i = 0; i < listeHotel.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/gestion/HotelItemFXML.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                HotelItemFXMLController itemController = fxmlLoader.getController();
                itemController.setData(listeHotel.get(i));
                itemController.setId(id);

                System.out.println(listeHotel.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } // Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        catch (IOException ex) {
            //Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
