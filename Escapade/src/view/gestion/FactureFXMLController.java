/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionPromoFactureReservation.services.PromotionService;
import gestionUserReclamation.entities.Utilisateur;
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
//import static view.gestion.PromotionFXMLController.p;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class FactureFXMLController implements Initializable {

    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    private Button Home;
    @FXML
    private TableView<Facture> ListF;
    @FXML
    private TableColumn<Facture, Float> PrixT;
    @FXML
    private TableColumn<Facture, Date> date;
    @FXML
    private TableColumn<Facture, Utilisateur> idC;
    @FXML
    private TableColumn<Facture, Float> PrixF;
    @FXML
    private TableColumn<Facture, Promotion> Taux;
    @FXML
    private Button AfficheF;

    public static Facture f;
    @FXML
    private Button btnOverview;
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
    private VBox pnItems;
    @FXML
    private Button Modif;
    @FXML
    private Button SupFac;
    @FXML
    private Button AjouterF;
    @FXML
    private Button RechercheF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

  
    @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {

        if (ListF.getSelectionModel().getSelectedItem() != null) {

            Facture facture = ListF.getSelectionModel().getSelectedItem();
            f = facture;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FactureModifierFXML.fxml"));
            Parent root = loader.load();
            FactureModifierFXMLController FM = loader.getController();
            FM.setFacture(f);
               try {
            
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
              try {
         
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une facture");
            alert.show();
        }
    }

    @FXML
    private void SupFac(ActionEvent event) throws SQLException {
        
         if (ListF.getSelectionModel().getSelectedItem() != null) {
             
            try {
                int id=ListF.getSelectionModel().getSelectedItem().getIdF();
                System.out.println(id);
                FactureService factureService = new FactureService();
                factureService.supprimerId(id);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Supression avec succes");
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

    @FXML
    public void Afficher() throws SQLException {
        FactureService factureService = new FactureService();

        /* add column to the tableview and set its items */
        ObservableList<Facture> facture = FXCollections.observableArrayList(factureService.afficher());
        ListF.setItems(facture);

        PrixT.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Facture, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixTotal());
            }
        });
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Facture, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate());
            }
        });

        idC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Utilisateur>, ObservableValue<Utilisateur>>() {
            @Override
            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Facture, Utilisateur> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient());
            }
        });

        PrixF.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Facture, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixFinal());
            }
        });

        Taux.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Promotion>, ObservableValue<Promotion>>() {
            @Override
            public ObservableValue<Promotion> call(TableColumn.CellDataFeatures<Facture, Promotion> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPromotion());
            }
        });

    }
    @FXML
    private void AjouterF(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FactureAjouterFXML.fxml"));
        Parent root = loader.load();
        // AjoutP.getScene().setRoot(root);

        FactureAjouterFXMLController fa = loader.getController();
 
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
          try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void RechercheF(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("RechercheFFXML.fxml"));
        Parent root = loader.load();
        RechercheF.getScene().setRoot(root);
    }

}
