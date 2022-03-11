/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.ChambreService;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import static view.gestion.Destination_BackFXMLController.dest;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ChambreBackFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
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
    private Button ajouter;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    private Button show;
    @FXML
    private TableView<Chambre> table;
    @FXML
    private TableColumn<Chambre, String> num;
    @FXML
    private TableColumn<Chambre,ImageView> img;
    @FXML
    private TableColumn<Chambre, String> prix;
    @FXML
    private TableColumn<Chambre, String> status;
    
    public static Chambre chambre;
    @FXML
    private Button tri;
    @FXML
    private Button details;
    @FXML
    private Button destinationB;
    @FXML
    private Button hotelB;
    @FXML
    private Button chambreB;
    
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
          
        
        delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    try {
                        int id = table.getSelectionModel().getSelectedItem().getId();
                        ChambreService chambreService = new ChambreService();
                        chambreService.supprimerId(id);
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

        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                if (table.getSelectionModel().getSelectedItem() != null) {

                   // try {
                        int id=table.getSelectionModel().getSelectedItem().getId();
                        System.out.println(id);

//                        Chambre ch = table.getSelectionModel().getSelectedItem().;
//                        System.out.println("1");
//                        ch.setId(table.getSelectionModel().getSelectedItem().getId());
//                        System.out.println("1");
//                        chambre = ch;
//                        System.out.println("1");
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ChambreModifierFXML.fxml"));
                        
                    try {
                        Parent root = loader.load();
                        ChambreModifierFXMLController dc = loader.getController();
                        dc.setIdChambre(id);
                        dc.setChambre();
                        update.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ChambreBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                       
                        
                
//                        try {
//                            Afficher();
//                        } catch (SQLException ex) {
//                            Logger.getLogger(ChambreBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        Stage stage = new Stage();
//                        Scene scene = new Scene(root);
//                        stage.setScene(scene);
//                        stage.showAndWait();
//                        try {
//                            Afficher();
//                        } catch (SQLException ex) {
//                            Logger.getLogger(ChambreBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    } catch (IOException ex) {
//                        Logger.getLogger(ChambreBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner une chambre");
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
    }
    
      



    @FXML
    private void Ajouter(ActionEvent event) throws IOException{
        
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChambreAjouterFXML.fxml"));
        Parent root = loader.load();
        // ajouterDest.getScene().setRoot(root);
        ChambreAjouterFXMLController dc = loader.getController();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(ChambreBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void AfficherTri() throws SQLException {

       ChambreService chambreService = new  ChambreService();

        ObservableList<Chambre> liste = FXCollections.
                observableArrayList(Chambre.generateImageViews(chambreService.tri()));

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
    }


    @FXML
    private void trier(ActionEvent event) throws SQLException {
        AfficherTri() ;  
    }

    @FXML
    private void details(ActionEvent event) throws IOException {
     
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChambreDetailsFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    
    }

   
    @FXML
    private void destinationB(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Destination_BackFXML.fxml"));
        Parent root = loader.load();
        destinationB.getScene().setRoot(root);
    }

    @FXML
    private void hotelB(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelBackFXML.fxml"));
        Parent root = loader.load();
        hotelB.getScene().setRoot(root);
    }

    @FXML
    private void chambreB(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
        Parent root = loader.load();
        chambreB.getScene().setRoot(root);
    }

    
}
