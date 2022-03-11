/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.services.DestinationService;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class DestinationModifierFXMLController implements Initializable {

    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button btnCustomers;
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
    private TextField pays;
    @FXML
    private TextField ville;
    @FXML
    private ListView imgDest;
    
    File selectedfile;

    String path_img;

    Upload u = new Upload();
    
    @FXML
    private Button insérer;

    public static Destination dest;
    @FXML
    private Button updateDest;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    public void setDestination(Destination d) {
        
        dest = d;
        this.pays.setText(dest.getPays());
        this.ville.setText(dest.getVille());
        this.imgDest.setAccessibleText(dest.getImg());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                DestinationService destinationService = new DestinationService();
                Destination d = new Destination();
 if (!pays.getText().equalsIgnoreCase("") && !ville.getText().equalsIgnoreCase("")) {
                d.setPays(pays.getText());
                d.setVille(ville.getText());
                d.setImg(selectedfile.getName());

                try {
                    destinationService.modifier(d, dest.getId());

                } catch (SQLException ex) {
                    Logger.getLogger(DestinationModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Votre modification est enregistrée avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();
 }
                else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Echec de la modification");
                        alert.setHeaderText(null);
                        alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                        alert.showAndWait();
 }
            }

        });

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
                        Logger.getLogger(DestinationModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    imgDest.getItems().add(selectedfile.getName());

                    path_img = selectedfile.getAbsolutePath();

                } else {
                    System.out.println("Fichier erroné");
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
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Destination_BackFXML.fxml"));
        Parent root = loader.load();
        back.getScene().setRoot(root);
    }

}
