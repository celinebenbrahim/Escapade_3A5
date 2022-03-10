/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class PromotionModifierFXMLController implements Initializable {

    @FXML
    private Button Home;

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
    private Button ModifierPromo;
    @FXML
    private TextField Taux;
    @FXML
    private DatePicker dateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private ImageView home;

    public static Promotion promo;

    /**
     * Initializes the controller class.
     */
    public void setPromo(Promotion p) {

        promo = p;

        this.Taux.setText(promo.getTaux() + "");
        this.dateD.setUserData(dateD);
        this.dateF.setUserData(dateF);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ModifierPromo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                int year = dateD.getValue().getYear() - 1900;
                int month = dateD.getValue().getMonthValue() - 1;
                int day = dateD.getValue().getDayOfMonth();
                Date dateD = new Date(year, month, day);

                int yearF = dateF.getValue().getYear() - 1900;
                int monthF = dateF.getValue().getMonthValue() - 1;
                int dayF = dateF.getValue().getDayOfMonth();
                Date dateF = new Date(yearF, monthF, dayF);

                PromotionService promotionService = new PromotionService();
                Promotion p = new Promotion();
                p.setTaux(Float.parseFloat(Taux.getText()));
                p.setDateDebut(dateD);
                p.setDateFin(dateF);
                
               /* if (Float.parseFloat(Taux.getText()) <= 0 && Float.parseFloat(Taux.getText()) >= 100) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Le taux doit être entre 0 et 100");
                    alert.showAndWait();
                }*/
                 
              
                
                try {
                    if (Float.parseFloat(Taux.getText()) <= 0 || Float.parseFloat(Taux.getText()) >= 100 || dateF.compareTo(dateD) < 0 ) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de la modification");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Le taux doit être entre 0 et 100 et date fin > date début)");
                    alert.showAndWait();}
                else{
                    promotionService.modifier(p, promo.getId());
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification est enregistrée avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(PromotionModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            }

        });

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

}
