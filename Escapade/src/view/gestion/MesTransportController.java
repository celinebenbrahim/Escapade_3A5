/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import services.ServiceMDT;
import entities.MoyenDeTrans;
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
public class MesTransportController implements Initializable {
    ServiceMDT RS=new ServiceMDT();
    User user=new User();
    
  public static MoyenDeTrans modif = new MoyenDeTrans();

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
    private TableView<MoyenDeTrans> tableview;
    @FXML
    private Button btn_add1;
    @FXML
    private Button btn_deco1;
    
    @FXML
    private TableColumn<MoyenDeTrans, String> Titre;
    @FXML
    private TableColumn<MoyenDeTrans, String> description;
    @FXML
    private TableColumn<MoyenDeTrans, String> date;
    @FXML
    private TableColumn<MoyenDeTrans, String> categorie;
    @FXML
    private TableColumn<MoyenDeTrans, String> sender;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ( user.getRole()=="admin") {
            tableview.getItems().setAll(RS.afficherTout());
   
                //combo.setItems(list);
         Titre.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("description"));
        categorie.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("tarifJ"));
                sender.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("status"));
                
  

                tableview.getItems().setAll(RS.afficherTout());
                
                
                // TODO
        }
        else {
            btn_add.setVisible(false);
            btn_add1.setVisible(false);
            btn_deco1.setVisible(false);
            tableview.getItems().setAll(RS.afficherTout());
   
         Titre.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("description"));
        categorie.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("type"));
        date.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("tarifJ"));
                sender.setCellValueFactory(new PropertyValueFactory<MoyenDeTrans, String>("status"));
  

                tableview.getItems().setAll(RS.afficherTout());
                
                
                // TODO
        }
      
       
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/TransportForm.fxml"));
       content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }

    @FXML
    private void tr(ActionEvent event) throws SQLException {
                    tableview.getItems().setAll(RS.afficherparprix());

    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/TransportUpdate.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
        modif = tableview.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        MoyenDeTrans f1 = (MoyenDeTrans) tableview.getSelectionModel().getSelectedItem();
   int selectedIndex = tableview.getSelectionModel().getSelectedIndex();
        System.out.println("++++++"+f1);
        RS.suprimerMDT(f1);

   
       tableview.getItems().remove(selectedIndex);
    }
    
}
