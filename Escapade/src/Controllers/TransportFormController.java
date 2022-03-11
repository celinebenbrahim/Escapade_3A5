/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.MoyenDeTrans;
import entities.User;
import services.ServiceMDT;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import utils.Status;
//import javax.mail.Session;


/**
 * FXML Controller class
 *
 * @author chaima
 */
public class TransportFormController implements Initializable {
    User user=new User();
    @FXML
    private Label lb_titre;
    @FXML
    private TextField txt_sujet;
    @FXML
    private ComboBox cbb_type;
    @FXML
    private ComboBox cbb_stt;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;
    @FXML
    private AnchorPane container;
    @FXML
    private TextField txt_tr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbb_type.getItems().add("volswagen");
        cbb_type.getItems().add("audi");
        cbb_type.getItems().add("Renault");
        cbb_stt.getItems().add("Disponible");
        cbb_stt.getItems().add("Indisponible");

        btn_ok.setOnAction(e -> {
            lb_error.setText("");
            if (txt_sujet.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le libelle du Moyen de transport !");
            } else if (cbb_type.getSelectionModel().getSelectedIndex() < 0) {
                lb_error.setText("vous devez choisir un type !");
                } else if (cbb_stt.getSelectionModel().getSelectedIndex() < 0) {
                lb_error.setText("vous devez choisir la diponibilité !");
            } else if (txt_contenu.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir la description !");
            } else {
                ServiceMDT RS = new ServiceMDT();
                MoyenDeTrans r = new MoyenDeTrans();
                r.setLibelle(txt_sujet.getText());
                r.setType((String) cbb_type.getValue());
                r.setDescription(txt_contenu.getText());
                r.setTarifJ(Float.parseFloat(txt_tr.getText()));
                r.setStatus(Status.valueOf(cbb_stt.getValue().toString()) );
                r.setIdAgence(1);
                r.setIdDestination(1);


                try {
                    RS.ajouterMDT(r);
                } catch (SQLException ex) {
                    Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                 AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesTransport.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
            }

        });
        btn_back.setOnAction(e -> {
             AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesTransport.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
        });
    }

}
