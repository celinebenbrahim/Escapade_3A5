/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.VueSurMer;
import gestionHotelDestination.services.ChambreService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ChambreFXMLController implements Initializable {
    private Button Back;
    @FXML
    private Button Home;
    @FXML
    private Button Hotel;
    @FXML
    private Button MoyT;
    @FXML
    private Button Chambre;
    @FXML
    private Button btnSignout;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private DatePicker checkIn;
    @FXML
    private DatePicker checkOut;
    @FXML
    private Button afficher;
    @FXML
    private ComboBox<VueSurMer> VuSurMer;
   
    @FXML
    private Label Vue;
    @FXML
    private ComboBox<Type> type;
        private int idHotel;

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
     private List<Chambre> listeChambre = new ArrayList<>();
    ChambreService cs= new ChambreService();


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().add(Type.simple);
        type.getItems().add(Type.doublee);
        VuSurMer.getItems().add(VueSurMer.yes);
        VuSurMer.getItems().add(VueSurMer.no);
        
    } 

    @FXML
    private void Home(ActionEvent event) {
    }

    @FXML
    private void Hotel(ActionEvent event) {
    }

    @FXML
    private void MoyT(ActionEvent event) {
    }

    @FXML
    private void Chambre(ActionEvent event) {
    }

    @FXML
    private void afficher(ActionEvent event) throws ParseException, SQLException {
         LocalDate localDate1 = checkIn.getValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate2 = checkOut.getValue();
        Date dateCheckIn = simpleDateFormat.parse(localDate1.toString());
        Date datecheckOut = simpleDateFormat.parse(localDate2.toString());
        Type typee = type.getSelectionModel().getSelectedItem();
        VueSurMer vssmm = VuSurMer.getSelectionModel().getSelectedItem();
        
        try {
            // TODO
            //System.out.println("listeChambre"+id);
            listeChambre = cs.rechercheChambre(dateCheckIn,idHotel,typee,vssmm);
            System.out.println("chambre"+listeChambre);

            int column = 0;
            int row = 1;
            grid.getChildren().clear();
            if(listeChambre.size()==0){
                
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information Dialog");
            alert.setContentText("Pas de chambre disponible dans cette date");
            alert.show();
            }
            for (int i = 0; i < listeChambre.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/gestion/ChambreItemsFXML.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                ChambreItemsFXMLController itemController = fxmlLoader.getController();
                itemController.setData(listeChambre.get(i));
                itemController.setIdHotel(idHotel);
                itemController.setDateAller(dateCheckIn);
                itemController.setDateRetour(datecheckOut);

                System.out.println(listeChambre.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } // Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        catch (IOException ex) {
            //Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

