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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class PromotionFXMLController implements Initializable {

    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    @FXML
    private Button Home;
    @FXML
    private Label ListP;
    @FXML
    private ImageView home;
    
    @FXML
    private Button AjoutP;
    @FXML
    private Button AfficherP;
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
    private Button SupPromo;
    @FXML
    private TableView<Promotion> tablePromo;
    @FXML
    private TableColumn<Promotion, Float> taux;
    @FXML
    private TableColumn<Promotion, Date> dateD;
    @FXML
    private TableColumn<Promotion, Date> dateF;
    
    
        public static Promotion p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(PromotionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

   /* @FXML
    private void AfficheP(ActionEvent event) throws SQLException {
        PromotionService promotionService = new PromotionService();
        ListP.setText(promotionService.afficher().toString());

    }*/
     @FXML
    private void AjoutP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PromotionAjouterFXML.fxml"));
        Parent root = loader.load();
        AjoutP.getScene().setRoot(root);
    }

    @FXML
    private void SupPromo(ActionEvent event) throws SQLException {
         if (tablePromo.getSelectionModel().getSelectedItem() != null) {
            try {
                int id = tablePromo.getSelectionModel().getSelectedItem().getId();
                System.out.println(id);
                PromotionService promotionService=new PromotionService();
             
                promotionService.supprimerId(id);
                 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("supression avec succes");
            alert.show();
            } catch (SQLException ex) {
            Logger.getLogger(PromotionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Afficher();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
    }
    public void Afficher() throws SQLException {
        PromotionService promotionService=new PromotionService();

        /* add column to the tableview and set its items */
        ObservableList<Promotion> listePromo = FXCollections.observableArrayList(promotionService.afficher());
        tablePromo.setItems(listePromo);

        taux.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Float>, ObservableValue<Float>>() {
         @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Promotion, Float> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getTaux());
         }
         });
        dateD.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Promotion, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateDebut());
            }
        });
        dateF.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Promotion, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDateFin());
            }
        });
    }
    
     @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {
        
                 
        if (tablePromo.getSelectionModel().getSelectedItem() != null) {
         Promotion promo = tablePromo.getSelectionModel().getSelectedItem();
        p= promo;
               FXMLLoader loader = new FXMLLoader(getClass().getResource("PromotionModifierFXML.fxml"));
        Parent root = loader.load();
        PromotionModifierFXMLController PM = loader.getController();
        
        //ac.setVol(a);
        
        Stage stage= new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        Afficher();
           
                   } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
    }
    
    
}
