/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.services.DestinationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class DestinationFXMLController implements Initializable {

    private Button Back;
    private Button Next;
    @FXML
    private Button Home;
    @FXML
    private Button btnSignout;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private List<Destination> listeDestination = new ArrayList<>();
    DestinationService ds = new DestinationService();
    @FXML
    private Button Hotel;
    @FXML
    private Button MoyT;
    @FXML
    private Button Chambre;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        

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
    
    public void afficher(){
        try {
            // TODO
            listeDestination = ds.afficher();
            
            int column = 0;
            int row = 1;
            for (int i = 0; i < listeDestination.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/gestion/DestinationItemsFXML.fxml"));
                AnchorPane anchorpane = fxmlLoader.load();
                DestinationItemsFXMLController itemController = fxmlLoader.getController();
                itemController.setData(listeDestination.get(i));
                itemController.setId(id);
                System.out.println("idCLient3="+id);
                System.out.println(listeDestination.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane, column++, row);
                GridPane.setMargin(anchorpane, new Insets(10));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
           // Logger.getLogger(ShowDestinationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
