/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;


//import com.itextpdf.text.Image;
import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
//import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;

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
    @FXML
    private ImageView checkTaux;
    @FXML
    private ImageView checkDD;
    @FXML
    private ImageView checkDF;
     
    String erreur;

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
        
        if(testSaisie()){
        int year = dateD.getValue().getYear()-1900;
        int month = dateD.getValue().getMonthValue()-1;
        int day = dateD.getValue().getDayOfMonth();
        Date dateD=new Date(year, month, day);
          
 
        int yearF = dateF.getValue().getYear()-1900;
        int monthF = dateF.getValue().getMonthValue()-1;
        int dayF = dateF.getValue().getDayOfMonth();
        Date dateF=new Date(yearF, monthF, dayF) ;
            
      
        
     //   PromotionService promotionService = new PromotionService();
         Promotion p = new Promotion(Float.parseFloat(Taux.getText()),dateD,dateF);
      
          try {
           

     if (Float.parseFloat(Taux.getText())<=0 || Float.parseFloat(Taux.getText()) >= 100) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Echec de l'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Le taux doit être entre 0 et 100");
            alert.show();
     }
      else  if (dateF.compareTo(dateD) < 0)  {
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Echec de l'ajout");
        alert.setHeaderText(null);
        alert.setContentText("Attention ! Date invalide !");
        alert.showAndWait();
        }
         
           else{
           PromotionService promotionService = new PromotionService();
             promotionService.ajouter(p);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Success");
             alert.setContentText("Promotion ajouté avec succées!");
             alert.show();
            ((Node)event.getSource()).getScene().getWindow().hide();
         } 
          }catch (SQLException ex) {
                        Logger.getLogger(PromotionAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
          
   
        }}
    
     private Boolean testTaux() {

        String nomReg = "^[0-9]*";

        Pattern pat = Pattern.compile(nomReg);
        if (Taux.getText() == null) {
            return false;
        }

        if (pat.matcher(Taux.getText()).matches() == false || Taux.getText().length()==0) {
            checkTaux.setImage(new Image("/view/ressources/images/ErreurcheckMark.png"));
            erreur = erreur + ("Veuillez verifier votre nom\n");
            return false;       

        } else {
            checkTaux.setImage(new Image("/view/ressources/images/checkMark.png"));
        }
        return true;

    }
     
 
     
     
      private Boolean testSaisie() {
        erreur = "";
       
       
        if (!testTaux()) {
            erreur = erreur + ("Veuillez verifier votre taux \n");
        }
        
         
       

        return   testTaux()    ;
    }
    
}
