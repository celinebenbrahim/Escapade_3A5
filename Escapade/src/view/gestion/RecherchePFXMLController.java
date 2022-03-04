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
import java.text.SimpleDateFormat;
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
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RecherchePFXMLController implements Initializable {

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
    private TextField recherche;
    @FXML
    private TableView<Promotion> tablePromo;
    @FXML
    private TableColumn<Promotion, Float> taux;
    @FXML
    private TableColumn<Promotion, Date> dateD;
    @FXML
    private TableColumn<Promotion, Date> dateF;

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
    private void recherche(ActionEvent event) {
        
        PromotionService promotionService = new PromotionService();

         float Taux =Float.parseFloat(recherche.getText());
         
        /* Date dateDebut = recherche.get;
         Date dateFin = recherche.getText();*/

          ObservableList<Promotion> listePromo = FXCollections.observableArrayList(promotionService.FindByTaux(Taux));
         System.out.println(listePromo);
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

   
    
}
