/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.HotelService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class CountHFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button Menu;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSignout1;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableView<Integer> tableHotel;
    @FXML
    private TableColumn<Integer, Integer> etoile;
    @FXML
    private TableColumn<Integer, Integer> hotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*  try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(HotelBackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        // TODO
    }    
 /*public void Afficher() throws SQLException {

        HotelService hotelService = new HotelService();

       int a1=hotelService.countHotel(1);
       int a2=hotelService.countHotel(2);
       int a3=hotelService.countHotel(3);
       int a4=hotelService.countHotel(4);
       int a5=hotelService.countHotel(5);
       ObservableList<Integer> list=null ;
       list.add(a5); list.add(a4); list.add(a3); list.add(a2); list.add(a1);
       etoile.setCellFactory(new PropertyValueFactory<Integer,Integer>(1+""));
       

   
       
    }*/
    @FXML
    private void Home(ActionEvent event) {
    }

    
}
