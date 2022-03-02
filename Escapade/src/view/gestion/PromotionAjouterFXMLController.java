/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class PromotionAjouterFXMLController implements Initializable {

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
    private TextField Taux;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
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
    private Button AjoutPromo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    

    @FXML
    private void AjoutPromo(ActionEvent event) throws SQLException {
        
        
        int year = dateD.getValue().getYear()-1900;
        int month = dateD.getValue().getMonthValue();
        int day = dateD.getValue().getDayOfMonth();
        Date dateD=new Date(year, month, day);
          
            
            
        int yearF = dateF.getValue().getYear()-1900;
        int monthF = dateF.getValue().getMonthValue();
         int dayF = dateF.getValue().getDayOfYear();
        Date dateF=new Date(year, month, day) ;
            
      
        
        PromotionService promotionService = new PromotionService();
         Promotion p = new Promotion(Float.parseFloat(Taux.getText()),dateD,dateF);
          try {
         promotionService.ajouter(p); 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Success");
         alert.setContentText("Promotion ajouté avec succées!");
         alert.show();
         } catch (SQLException ex) {
                        Logger.getLogger(PromotionAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
           if (dateF.compareTo(dateF) < 0) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec de l'ajout");
        alert.setHeaderText(null);
        alert.setContentText("Attention ! Date invalide !");
        alert.showAndWait();
        }
    }
    
}
