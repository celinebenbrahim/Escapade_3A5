/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.services.DestinationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.HotelService;
import gestionHotelDestination.services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class HotelAjouterFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button AjoutHotel;
    @FXML
    private TextField matricule;
    @FXML
    private TextField nom;
    @FXML
    private ListView imgHotel;
    @FXML
    private Button insérer;
    @FXML
    private TextField nbEtoiles;
    @FXML
    private TextArea description;
    @FXML
    private TextField nbChambres;
    @FXML
    private ComboBox <Destination> destination;
    @FXML
    private Button annuler;
    File selectedfile;
    
    String path_img;
 
    Upload u = new Upload();

    /**
     * Initializes the controller class.
     */
    
    @FXML
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListerDestination();
          insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {

                    Upload u = new Upload();
                    try {
                        u.upload(selectedfile);
                    } catch (IOException ex) {
                        Logger.getLogger(DestinationAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    imgHotel.getItems().add(selectedfile.getName());
               
                    path_img = selectedfile.getAbsolutePath();
                    
                 
                  
                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });
          
           AjoutHotel.setOnAction(new EventHandler<ActionEvent>()  {

            @Override
            public void handle(ActionEvent event) {
                Destination d =destination.getSelectionModel().getSelectedItem();
                 if (!nbEtoiles.getText().equalsIgnoreCase("") && 
                   !description.getText().equalsIgnoreCase("") &&
                          !nom.getText().equalsIgnoreCase("") &&
                         Float.parseFloat(nbChambres.getText()) > 0 &&
                           Float.parseFloat(nbEtoiles.getText()) > 0 &&
                            Float.parseFloat(nbEtoiles.getText()) < 6 &&
                          !nbChambres.getText().equalsIgnoreCase("") &&
                         !matricule.getText().equalsIgnoreCase("") 
                         ) {
               int nb=Integer.parseInt(nbEtoiles.getText());
               int ch=Integer.parseInt(nbChambres.getText());
                Hotel h = new Hotel(matricule.getText(), nom.getText(),nb ,
                        description.getText(),ch,d,selectedfile.getName());
                System.out.println(h);
                HotelService hotelService = new HotelService();
                try {
                    hotelService.ajouter(h);
                } catch (SQLException ex) {
                    Logger.getLogger(HotelAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                   alert0.setHeaderText(null);
                   alert0.setContentText("Ajout avec succes ");
                   alert0.show();
                   ((Node) event.getSource()).getScene().getWindow().hide();
            }
            else {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de la modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                        alert.showAndWait();
               
           }}});

    }    

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("MenuFXML.fxml"));
        Parent root = loader.load();
        Menu.getScene().setRoot(root);
    }
    @FXML
    private void Facture(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("FactureFXML.fxml"));
        Parent root = loader.load();
        Facture.getScene().setRoot(root);
    }
     @FXML
    private void Promotion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("PromotionFXML.fxml"));
        Parent root = loader.load();
        Promotion.getScene().setRoot(root);
    }
    
    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();   
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }
    
    private void ListerDestination()
    {
          DestinationService destinationService = new DestinationService();
          ObservableList<Destination> list=FXCollections.observableArrayList();
          try{
              String req = " select id,pays,ville from `destination` order by pays DESC";
             PreparedStatement   pst = destinationService.conn.prepareStatement(req);
              ResultSet rs = pst.executeQuery();
               while (rs.next()) {
                Destination d = new Destination(rs.getInt(1),rs.getString(2),rs.getString(3));
             
                list.add(d);
            }
               
             
          }
          catch(Exception ex)
          {
             ex.printStackTrace();
          }
            destination.setItems(null);
               destination.setItems(list);
    }
    

}
