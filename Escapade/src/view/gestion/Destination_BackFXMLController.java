/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.services.DestinationService;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class Destination_BackFXMLController implements Initializable {

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
    private Button ajouterDest;
    @FXML
    private Button updateDest;
    @FXML
    private Button deleteDest;
    @FXML
    private Button showDest;
    @FXML
    private TableColumn<Destination, String> pays;
    @FXML
    private TableColumn<Destination, String> ville;
    @FXML
    private TableColumn<Destination, ImageView> imgDest;
    @FXML
    private TableView<Destination> tableDest;

    @FXML
    private Button rechercherD;
    public static Destination dest;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        showDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        deleteDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableDest.getSelectionModel().getSelectedItem() != null) {
                    try {
                        int id = tableDest.getSelectionModel().getSelectedItem().getId();
                        DestinationService destinationService = new DestinationService();
                        destinationService.supprimerId(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Afficher();
                    } catch (SQLException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner une destination");
                    alert.show();
                }
            }
        });

        updateDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableDest.getSelectionModel().getSelectedItem() != null) {

                    try {

                        Destination d = tableDest.getSelectionModel().getSelectedItem();
                        System.out.println("1");
                        d.setId(tableDest.getSelectionModel().getSelectedItem().getId());
                        System.out.println("1");
                        dest = d;
                        System.out.println("1");
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("DestinationModifierFXML.fxml"));
                        Parent root = loader.load();

                        DestinationModifierFXMLController dc = loader.getController();
                        dc.setDestination(d);
                        try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.showAndWait();
                        try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner une destination");
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

        imgDest.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Destination, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });

    }

    @FXML
    private void rechercherD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("DestinationSearchFXML.fxml"));
        Parent root = loader.load();
        rechercherD.getScene().setRoot(root);
    }

    @FXML
    private void AjoutDest(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DestinationAjouterFXML.fxml"));
        Parent root = loader.load();
        // ajouterDest.getScene().setRoot(root);
        DestinationAjouterFXMLController dc = loader.getController();

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

    @FXML
    private void pdfev(ActionEvent event) throws IOException {
     Stage primaryStage = (Stage) pdf.getScene().getWindow();
       Parent root = FXMLLoader.load(getClass().getResource("/view/gestion/Chart.fxml"));
       Scene scene = new Scene (root);
       primaryStage.setScene(scene);
       primaryStage.show();
       
    }

}
