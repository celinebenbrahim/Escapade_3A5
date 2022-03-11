/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import services.ServiceAgenceLoc;
import entities.AgenceLoc;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class MesAgencesController implements Initializable {
    ServiceAgenceLoc RS=new ServiceAgenceLoc();
    
  public static AgenceLoc modif = new AgenceLoc();

    @FXML
    private AnchorPane content;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_deco;
    @FXML
    private Button btn_tri;
    @FXML
    private TableView<AgenceLoc> tableview;
    @FXML
    private Button btn_add1;
    @FXML
    private Button btn_deco1;
    
    @FXML
    private TableColumn<AgenceLoc, String> Titre;
    @FXML
    private TableColumn<AgenceLoc, String> description;
    @FXML
    private TableColumn<AgenceLoc, String> date;
    @FXML
    private TableColumn<AgenceLoc, String> categorie;
    @FXML
    private TableColumn<AgenceLoc, String> sender;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tableview.getItems().setAll(RS.afficherTout());
        } catch (SQLException ex) {
            Logger.getLogger(MesAgencesController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
                //combo.setItems(list);
         Titre.setCellValueFactory(new PropertyValueFactory<AgenceLoc, String>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<AgenceLoc, String>("adresse"));
        categorie.setCellValueFactory(new PropertyValueFactory<AgenceLoc, String>("email"));
        date.setCellValueFactory(new PropertyValueFactory<AgenceLoc, String>("numTel"));
                
  

        try {
            tableview.getItems().setAll(RS.afficherTout());
            
            
            // TODO
            
            
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(MesAgencesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
       
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/AgenceForm.fxml"));
       content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }


    @FXML
    private void modifier(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/AgenceUpdate.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
        modif = tableview.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        AgenceLoc f1 = (AgenceLoc) tableview.getSelectionModel().getSelectedItem();
   int selectedIndex = tableview.getSelectionModel().getSelectedIndex();
        System.out.println("++++++"+f1);
        RS.suprimerMDT(f1);
       String title = "Supprimé";
        String message = "L'agence est supprimé";
        Notification notification = Notifications.ERROR;
           TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndWait();

   
       tableview.getItems().remove(selectedIndex);
    }
    
}
