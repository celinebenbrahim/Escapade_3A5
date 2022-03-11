/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import static Controllers.MesAgencesController.modif;
import java.io.IOException;
import services.ServiceAgenceLoc;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.Status;

/**
 * FXML Controller class
 *
 * @author KattaX
 */
public class AgenceUpdateController implements Initializable {
    ServiceAgenceLoc R = new ServiceAgenceLoc();

    @FXML
    private AnchorPane container;
    @FXML
    private TextField txt_sujet;
    @FXML
    private TextField txt_adresse;
    @FXML
    private TextField txt_num;
    @FXML
    private TextField txt_email;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         txt_sujet.setText( MesAgencesController.modif.getLibelle());
                txt_adresse.setText( MesAgencesController.modif.getAdresse());
                                txt_email.setText( MesAgencesController.modif.getEmail());
                                                                txt_num.setText(String.valueOf(MesAgencesController.modif.getNumTel()));

        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
            MesAgencesController.modif.setLibelle(txt_sujet.getText());
        MesAgencesController.modif.setAdresse(txt_adresse.getText());
                MesAgencesController.modif.setEmail(txt_email.getText()); 

                MesAgencesController.modif.setNumTel(Integer.parseInt(txt_num.getText()));    

        R.modifierAgence(modif);
        
        
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesAgences.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesAgences.fxml"));
        container.getChildren().clear();
        container.getChildren().add(newLoadedPane);
    }
    
}
