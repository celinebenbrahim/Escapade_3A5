/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.ChambreService;
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
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ChambreDetailsFXMLController implements Initializable {

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
    private TableView<Chambre> table;
    @FXML
    private TableColumn<Chambre, String> num;
    @FXML
    private TableColumn<Chambre, ImageView> img;
    @FXML
    private TableColumn<Chambre, String> prix;
    @FXML
    private TableColumn<Chambre, String> status;
    @FXML
    private Button back;
    @FXML
    private TableColumn<Chambre, String> type;
    @FXML
    private TableColumn<Chambre, String> vsm;
    @FXML
    private TableColumn<Chambre, String> description;
    @FXML
    private TableColumn<Chambre, Hotel> hotel;

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
        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }
    
    
    
    public void Afficher() throws SQLException {

        ChambreService chambreService = new  ChambreService();

        ObservableList<Chambre> liste = FXCollections.
                observableArrayList(Chambre.generateImageViews(chambreService.afficher()));

        table.setItems(liste);

        /* add column to the tableview and set its items */
        num.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNum());
            }
        });
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixNuit());
            }
        });

        img.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Chambre, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        status.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getStatus());
            }
        });
          vsm.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getVueSurMer());
            }
        });
          
            type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getType());
            }
        });
            
              hotel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, Hotel>, ObservableValue<Hotel>>() {
            
            @Override
            public ObservableValue<Hotel> call(TableColumn.CellDataFeatures<Chambre, Hotel> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getHotel().getNom());
            }
        });
              
              description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Chambre, String>, ObservableValue<String>>() {
            
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Chambre, String> param) {
              
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
              
              
    }

    
}
