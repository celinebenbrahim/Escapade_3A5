/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ChangeMdpFXMLController implements Initializable {

    @FXML
    private PasswordField tfNewMdp;
    @FXML
    private PasswordField tfConfirmMdp;
    @FXML
    private Button boutonValider;
    @FXML
    private Label erreur;
    
    
     private Utilisateur u;
    @FXML
    private Button Back;
     
    public void setU(Utilisateur u) {
        this.u = u;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


      @FXML
    private void modifierMotDePasse(ActionEvent event) throws IOException {
        String newMdp = tfNewMdp.getText();
        String confirmMdp = tfConfirmMdp.getText();
        if (newMdp.equals(confirmMdp)) {
            UtilisateurService us = new UtilisateurService();
            u.setMdp(newMdp);
            us.modifierMotDePasse(u);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ConxFXML.fxml"));
            Parent root = loader.load();
            boutonValider.getScene().setRoot(root);
        
        }
        else{
            erreur.setText("Les mots de passe ne correspondent pas!");
            erreur.setTextFill(Color.web("#F00000"));
            
        }

    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        UtilisateurService us = new UtilisateurService();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilFXML.fxml"));
        Parent root = loader.load();
        ProfilFXMLController controller = loader.getController();
        controller.Profil();
        Back.getScene().setRoot(root);
    }

    
}
