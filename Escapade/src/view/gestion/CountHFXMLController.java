/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
    private TableColumn<?, ?> etoile;
    @FXML
    private TableColumn<?, ?> hotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Home(ActionEvent event) {
    }

    
}
