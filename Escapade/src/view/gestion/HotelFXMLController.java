/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.HotelService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class HotelFXMLController implements Initializable {
    
    @FXML
    private Button Back;
    @FXML
    private Button Next;
    @FXML
    private TableView<Hotel> tableHotel;
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
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }    
    
    @FXML
    public void Afficher() throws SQLException {

        HotelService hotelService = new HotelService();

        ObservableList<Hotel> listeHotel = FXCollections.
                observableArrayList(Hotel.generateImageViews(hotelService.afficher()));

        tableHotel.setItems(listeHotel);

        /* add column to the tableview and set its items */
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
        
        nbEtoiles.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNbrEtoile());
            }
        });
        
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });

        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Hotel, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
    }
     @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("DestinationFXML.fxml"));
        Parent root = loader.load();
        Back.getScene().setRoot(root);
    }
    
      @FXML
    private void Next(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("BilletFXML.fxml"));
        Parent root = loader.load();
        Next.getScene().setRoot(root);
    }
    
}
