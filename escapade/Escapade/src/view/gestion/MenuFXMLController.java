/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class MenuFXMLController implements Initializable {

    @FXML
    private Button Home;
       @FXML
    private Button Chambre;
    @FXML
    private ImageView home;
    @FXML
    private Button Destination;
    @FXML
    private Button Menu;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button Guide;
    @FXML
    private Button SiteT;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void Destination(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(); 
        loader.setLocation(getClass().getResource("Destination_BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }
    @FXML
    private void Hotel(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HotelBackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }
     @FXML
    private void Chambre(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
        Parent root = loader.load();
        Chambre.getScene().setRoot(root);
    }

    @FXML
    private void Guide(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GuideController.fxml"));
        Parent root = loader.load();
        Guide.getScene().setRoot(root);
    }

    @FXML
    private void SiteT(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SiteController.fxml"));
        Parent root = loader.load();
        SiteT.getScene().setRoot(root);
    }
    
    
}
