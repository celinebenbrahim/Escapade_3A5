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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class HotelDetailsFXMLController implements Initializable {

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
    private TextField searchHotel;
    @FXML
    private Button back;
    @FXML
    private TableView<Hotel> tableHotel;
    @FXML
    private TableView<Hotel> table2;
    @FXML
    private TableColumn<Hotel, ImageView> image;
    @FXML
    private TableColumn<Hotel, Destination> pays;
    @FXML
    private TableColumn<Hotel, String> nom;
    @FXML
    private TableColumn<Hotel, String> nbChambnonRes;
    @FXML
    private TableColumn<Hotel, String> matriculefisc;
    @FXML
    private TableColumn<Hotel, String> description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO
    }    
    
 public void Afficher() throws SQLException {

        HotelService hotelService = new HotelService();

        ObservableList<Hotel> listeHotel = FXCollections.
                observableArrayList(Hotel.generateImageViews(hotelService.afficher()));

        tableHotel.setItems(listeHotel);

        /* add column to the tableview and set its items */
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
        matriculefisc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
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
        nbChambnonRes.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hotel, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hotel, String> param) {
               int i= param.getValue().getMaxChambre()-param.getValue().getNbChambreTotal();
                return new ReadOnlyObjectWrapper(i);
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
     @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelBackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);

    }

//    @FXML
//    private void searchHotel(ActionEvent event) {
//    }
//    
//    void searchHot() {
//        Hotel h = new Hotel();
//          nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        image.setCellValueFactory(new PropertyValueFactory<>("imgHotel"));
//        matriculefisc.setCellValueFactory(new PropertyValueFactory<>("matriculeFiscale"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//      
//         table2.setItems(dataEvent);
//        FilteredList<Hotel> filteredData = new FilteredList<>(dataEvent, b -> true);
//        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate((Hotel adm) -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (adm.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches first name.
//                } else if (adm.getMatriculeFiscale().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                    } else if (adm.getImg().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;
//                   } else if (adm.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true;  
//                } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//        SortedList<Hotel> sortedData = new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(table2.comparatorProperty());
//        table2.setItems(sortedData);
//    }
    
}
