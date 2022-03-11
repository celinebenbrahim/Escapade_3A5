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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class DestinationSearchFXMLController implements Initializable {

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
    private TableColumn<Destination, String> pays;
    @FXML
    private TableColumn<Destination, String> ville;
    @FXML
    private TableColumn<Destination, ImageView> imgDest;
    @FXML
    private TableView<Destination> tableDest;

    public static Destination dest;
    @FXML
    private TextField searchDestination;
    @FXML
    private Button back;

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
    private void searchDestination(ActionEvent event) throws SQLException {

        DestinationService destinationService = new DestinationService();

        String Pays = searchDestination.getText();
        String Ville = searchDestination.getText();
        
       

        ObservableList<Destination> listeDest = FXCollections.observableArrayList(Destination.generateImageViews(destinationService.findDestination( Pays, Ville)));
       // System.out.println(listeDest);
        tableDest.setItems(listeDest);

        /* add column to the tableview and set its items */
        
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPays());
            }
        });
        
        ville.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille());
            }
        });

      imgDest.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Destination, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });

    }
 @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Destination_BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

}
