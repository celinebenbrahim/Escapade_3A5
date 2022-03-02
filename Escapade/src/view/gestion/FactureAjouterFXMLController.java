/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionPromoFactureReservation.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class FactureAjouterFXMLController implements Initializable {

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
    private Button AjoutFacture;
    @FXML
    private TextField PrixT;
    @FXML
    private DatePicker date;
   
    @FXML
    private TextField PrixF;
    @FXML
    private Spinner<Class> Client;
    @FXML
    private Spinner<Class> Promo;
   

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
    private void AjoutFacture(ActionEvent event) throws SQLException{
        SpinnerValueFactory<Class> gradesvaluefactory1=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999, 0);
        SpinnerValueFactory<Integer> gradesvaluefactory2=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999, 0);
        
        
        
        this.Client.setValueFactory(gradesvaluefactory1);
        this.Promo.setValueFactory(gradesvaluefactory2);
        
        Client.setEditable(true);
        Promo.setEditable(true);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        FactureService factureService = new FactureService();
         Facture f = new Facture(Double.parseDouble(PrixT.getText()),date,Client.getId().getClass(),Double.parseDouble(PrixF.getText()),Promo.getId().getClass());
          try {
         factureService.ajouter(f); 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Success");
         alert.setContentText("Promotion ajouté avec succées!");
         alert.show();
         } catch (SQLException ex) {
                        Logger.getLogger(PromotionAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
    }
    
}
