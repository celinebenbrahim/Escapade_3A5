/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Status;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.VueSurMer;
import gestionHotelDestination.services.ChambreService;
import gestionHotelDestination.services.HotelService;
import gestionHotelDestination.services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import static view.gestion.HotelBackFXMLController.hotel;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ChambreAjouterFXMLController implements Initializable {

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
    private Button insérer;
    @FXML
    private Button annuler;
    @FXML
    private TextArea description;
    @FXML
    private Button Ajout;
    @FXML
    private TextField num;
    @FXML
    private ListView img;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<Status> statut;
    @FXML
    private ComboBox<Hotel> hotel;
    @FXML
    private ComboBox<VueSurMer> vsm;
    @FXML
    private ComboBox<Type> type;
       File selectedfile;
    
    String path_img;
 
    Upload u = new Upload();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     statut.getItems().add(Status.disponible);
        statut.getItems().add(Status.indisponible);
        type.getItems().add(Type.simple);
        type.getItems().add(Type.doublee);
        vsm.getItems().add(VueSurMer.yes);
        vsm.getItems().add(VueSurMer.no);
        ListerHotel();
        
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
                        Logger.getLogger(ChambreAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img.getItems().add(selectedfile.getName());

                    path_img = selectedfile.getAbsolutePath();

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });

        Ajout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                Hotel h = hotel.getSelectionModel().getSelectedItem();
                VueSurMer vssmm = vsm.getSelectionModel().getSelectedItem();
                Type typee = type.getSelectionModel().getSelectedItem();
                Status statu = statut.getSelectionModel().getSelectedItem();
                int prixx = Integer.parseInt(prix.getText());
                int numero = Integer.parseInt(num.getText());
                
                Chambre ch = new Chambre(numero, typee, vssmm, description.getText(),
                        prixx, statu, h, selectedfile.getName());
                System.out.println(ch);
                ChambreService chambreService = new ChambreService();
                try {
                    chambreService.ajouter(ch);
                } catch (SQLException ex) {
                    Logger.getLogger(ChambreAjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                alert0.setTitle("information Dialog");
                alert0.setHeaderText(null);
                alert0.setContentText("Ajout avec succes ");
                alert0.show();
                ((Node) event.getSource()).getScene().getWindow().hide();

            }
        });
    }    

    @FXML
    private void Home(ActionEvent event) {
    }

    @FXML
    private void Menu(ActionEvent event) {
    }

    @FXML
    private void Facture(ActionEvent event) {
    }

    @FXML
    private void Promotion(ActionEvent event) {
    }
      private void ListerHotel() {

        HotelService hotelService = new HotelService();
        ObservableList<Hotel> list = FXCollections.observableArrayList();

        try {

            String req = " select idHotel,matriculeFiscale,`nom`, `nbrEtoile`, `description`, "
                    + "`nbChambreTotal`, `maxChambre`, `imgHotel` ,`pays`, `ville` FROM `hotel` JOIN `destination`"
                    + " ON hotel.idDestination = destination.id ";
            
            PreparedStatement pst = hotelService.conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {

                Hotel h = new Hotel();
                h.setIdHotel(rs.getInt("idHotel"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setNbrEtoile(rs.getInt(4));
                h.setDescription(rs.getString(5));
                h.setNbChambreTotal(rs.getInt(6));
                h.setMaxChambre(rs.getInt(7));
                h.setImg(rs.getString(8));
                Destination d = new Destination(rs.getString(9), rs.getString(10));
                h.setDestination(d);
                list.add(h);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
     
        hotel.setItems(null);
        hotel.setItems(list);
    }
    
}
