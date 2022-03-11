/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.MesTransportController.modif;
import services.ServiceMDT;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.Status;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class TransportUpdateController implements Initializable {
    ServiceMDT RS = new ServiceMDT();

    @FXML
    private AnchorPane container;
    @FXML
    private TextField txt_sujet;
    @FXML
    private ComboBox<String> cbb_type;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;
    @FXML
    private ComboBox cbb_stt;
    @FXML
    private TextField txt_tr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cbb_type.getItems().add("volswagen");
        cbb_type.getItems().add("audi");
        cbb_type.getItems().add("Renault");
   cbb_stt.getItems().add("Disponible");
        cbb_stt.getItems().add("Indisponible");
                txt_sujet.setText( MesTransportController.modif.getLibelle());
                txt_contenu.setText( MesTransportController.modif.getDescription());
                                txt_tr.setText(String.valueOf(MesTransportController.modif.getTarifJ()));

                
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        MesTransportController.modif.setDescription(txt_contenu.getText());
        MesTransportController.modif.setLibelle(txt_sujet.getText());
                MesTransportController.modif.setTarifJ(Float.parseFloat(txt_tr.getText())); 

                MesTransportController.modif.setStatus(Status.valueOf((String) cbb_stt.getSelectionModel().getSelectedItem()));    

        MesTransportController.modif.setType((String) cbb_type.getSelectionModel().getSelectedItem());
        RS.modifierMDT(modif);
        
        
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesTransport.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesTransport.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);
    }
    
}
