/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.DestinationService;
import gestionHotelDestination.services.HotelService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class DestinationHotelFXMLController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button Next;
    @FXML
    private TableView<Hotel> tableDest;
    @FXML
    private TableColumn<Hotel, ImageView> img;
    @FXML
    private TableColumn<Hotel, String> nbEtoiles;
    @FXML
    private TableColumn<Hotel, String> description;
    @FXML
    private TableColumn<Hotel, String> nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
