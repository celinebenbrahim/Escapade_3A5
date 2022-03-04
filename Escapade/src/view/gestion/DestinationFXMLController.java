/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.DestinationService;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import static view.gestion.Destination_BackFXMLController.dest;
import static view.gestion.HotelBackFXMLController.hotel;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class DestinationFXMLController implements Initializable {

    @FXML
    private Button Back;
     @FXML
    private Button Next;

  
    /**
     * Initializes the controller class.
     */
    private Connection conn;
   
    private Button nextDest;
    @FXML
    private TableColumn<Destination, ImageView> img;
    @FXML
    private TableColumn<Destination, String> pays;
    @FXML
    private TableColumn<Destination, String> ville;
    @FXML
    private TableView<Destination> tableDest;
    
    public static Destination dest;
    @FXML
    private Button showHotel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           
            
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
  
      
     
    }    
     public void Afficher() throws SQLException {

        DestinationService destinationService = new DestinationService();

        ObservableList<Destination> listeDest = FXCollections.
                observableArrayList(Destination.generateImageViews(destinationService.afficher()));

        tableDest.setItems(listeDest);

        /* add column to the tableview and set its items */
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPays());
            }
        });
        ville.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille());
            }
        });

        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Destination, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });

         tableDest.setCenterShape(true);
         
        
    }
      @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Back.getScene().setRoot(root);
    }
    
      @FXML
    private void Next(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("HotelFXML.fxml"));
        Parent root = loader.load();
        Next.getScene().setRoot(root);
    }
    
     
  

    
    
    
    
   }
