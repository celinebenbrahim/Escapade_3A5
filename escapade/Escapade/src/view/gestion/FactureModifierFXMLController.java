/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionPromoFactureReservation.services.PromotionService;
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import static view.gestion.PromotionModifierFXMLController.promo;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class FactureModifierFXMLController implements Initializable {

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
    private Button ModifierFacture;
    @FXML
    private TextField PrixT;
    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox<Utilisateur> idC;
    @FXML
    private ChoiceBox<Promotion> idPromo;
    public static Facture fac;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    public void setFacture(Facture f) {

        fac = f;
        this.PrixT.setText(fac.getPrixTotal() + "");
        this.date.setUserData(date);
        this.idC.getSelectionModel().selectFirst();

        this.idPromo.getSelectionModel().selectFirst();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListerUtilisateur();
        ListerPromotion();

        ModifierFacture.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FactureService factureService = new FactureService();

                Date dateF = convertToDateViaSqlDate(date.getValue());

                java.sql.Date Today = new java.sql.Date(Calendar.getInstance().getTime().getTime());

                Utilisateur u = idC.getSelectionModel().getSelectedItem();

                Promotion p = idPromo.getSelectionModel().getSelectedItem();

                float PrixTotal = Float.parseFloat(PrixT.getText());

                Facture f = new Facture(fac.getIdF(), PrixTotal, dateF, u, 0, p);
                System.out.println(fac.getIdF());
                try {
                    if (Float.parseFloat(PrixT.getText()) <= 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de la modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Le prix doit être positive");
                        alert.show();

                    } else {
                        factureService.modifier(f, fac.getIdF());
                        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                        alert0.setTitle("information Dialog");
                        alert0.setHeaderText(null);
                        alert0.setContentText("Votre modification est enregistrée avec succes ");
                        alert0.show();
                        ((Node) event.getSource()).getScene().getWindow().hide();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(FactureModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void ListerUtilisateur() {

        UtilisateurService utilisateurService = new UtilisateurService();
        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
        try {
            String req = " select `id`,`nom`,`prenom` from `utilisateur` ";
            PreparedStatement pst = utilisateurService.conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Utilisateur u = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        idC.setItems(null);
        idC.setItems(list);
    }

    private void ListerPromotion() {
        PromotionService destinationService = new PromotionService();
        ObservableList<Promotion> list = FXCollections.observableArrayList();
        try {
            String req = " select id,taux from `promotion` order by taux DESC";
            PreparedStatement pst = destinationService.conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Promotion p = new Promotion(rs.getInt(1), rs.getFloat(2));
                list.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        idPromo.setItems(null);
        idPromo.setItems(list);
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("FactureFXML.fxml"));
        Parent root = loader.load();
        Annuler.getScene().setRoot(root);
    }

}
