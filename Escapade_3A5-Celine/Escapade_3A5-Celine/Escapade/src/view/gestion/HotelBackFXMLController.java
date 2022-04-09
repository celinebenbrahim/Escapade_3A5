/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.HotelService;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class HotelBackFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button Menu;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSignout1;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;

    @FXML
    private Button ajouterHotel;
    @FXML
    private Button updateHotel;
    @FXML
    private Button deleteHotel;
    @FXML
    private Button showHotel;
    @FXML
    private TableColumn<Hotel, String> nom;
    @FXML
    private TableColumn<Hotel, ImageView> image;
    @FXML
    private TableColumn<Hotel, Destination> pays;
    @FXML
    private TableView<Hotel> tableHotel;

    public static Hotel hotel;
    @FXML
    private TableColumn<Hotel, String> nom1;
    @FXML
    private Button showDetails;
    @FXML
    private Button rechercherH;
    @FXML
    private Button count;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        deleteHotel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableHotel.getSelectionModel().getSelectedItem() != null) {
                    int id = tableHotel.getSelectionModel().getSelectedItem().getIdHotel();
                    HotelService hotelService = new HotelService();
                    try {
                        hotelService.supprimerId(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Afficher();
                    } catch (SQLException ex) {
                        Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner un hôtel");
                    alert.show();
                }
            }
        });

         updateHotel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableHotel.getSelectionModel().getSelectedItem() != null) {

                    try {
                        
                        Hotel h = tableHotel.getSelectionModel().getSelectedItem();
                        h.setIdHotel(tableHotel.getSelectionModel().getSelectedItem().getIdHotel());
                        System.out.println(h.getIdHotel());
                        hotel = h;
                        System.out.println(hotel.getIdHotel());
                          FXMLLoader loader = new FXMLLoader();
                         loader.setLocation(getClass().getResource("HotelModifierFXML.fxml"));
                       Parent root = loader.load();
                        
                        HotelModifierFXMLController dc = loader.getController();
                        dc.setHotel(h);
                         try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.showAndWait();
                        try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner un hôtel");
                    alert.show();
                }
            }

        });
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);

    }

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
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, Destination>, ObservableValue<Destination>>() {
            @Override
            public ObservableValue<Destination> call(TableColumn.CellDataFeatures<Hotel, Destination> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination());
            }
        });

        image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Hotel, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        nom1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
               int i= param.getValue().getMaxChambre()-param.getValue().getNbChambreTotal();
                return new ReadOnlyObjectWrapper(i);
            }
        });
    }

    @FXML
    private void AjoutHotel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelAjouterFXML.fxml"));
        Parent root = loader.load();
        // ajouterDest.getScene().setRoot(root);
        HotelAjouterFXMLController dc = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void AfficherTri() throws SQLException {

        HotelService hotelService = new HotelService();

        ObservableList<Hotel> listeHotel = FXCollections.
                observableArrayList(Hotel.generateImageViews(hotelService.tri()));

        tableHotel.setItems(listeHotel);

        /* add column to the tableview and set its items */
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, Destination>, ObservableValue<Destination>>() {
            @Override
            public ObservableValue<Destination> call(TableColumn.CellDataFeatures<Hotel, Destination> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDestination());
            }
        });

        image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Hotel, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        nom1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
               int i= param.getValue().getMaxChambre()-param.getValue().getNbChambreTotal();
                return new ReadOnlyObjectWrapper(i);
            }
        });
    }

    @FXML
    private void trier(ActionEvent event) throws SQLException {
        
     AfficherTri() ;  
     
    }



   

    @FXML
    private void details(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelDetailsFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void rechercherH(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RechercheHFXML.fxml"));
        Parent root = loader.load();
        rechercherH.getScene().setRoot(root);
    }

    @FXML
    private void count(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountHFXML.fxml"));
        Parent root = loader.load();
        rechercherH.getScene().setRoot(root);
    }

}
