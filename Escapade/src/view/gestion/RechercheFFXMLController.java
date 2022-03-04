/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionUserReclamation.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RechercheFFXMLController implements Initializable {

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
    private TextField recherche;
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
    private Button btnOverview;
    @FXML
    private VBox pnItems;
    
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

    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        FactureService factureService = new FactureService();
  float prixTotal =Float.parseFloat(recherche.getText());
   float prixFinal =Float.parseFloat(recherche.getText());
         
       

          ObservableList<Facture> facture = FXCollections.observableArrayList(factureService.findFacture(prixTotal, prixFinal));
         System.out.println(facture);
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

    
}
