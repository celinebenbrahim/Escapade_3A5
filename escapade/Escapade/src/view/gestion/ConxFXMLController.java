/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import static gestionUserReclamation.entities.Role.CLIENT;
import gestionUserReclamation.entities.Session;
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ConxFXMLController implements Initializable {
    
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfMotDePasseInvisible;
    @FXML
    private Button boutonConnexion;
    @FXML
    private Label labelMdpOublié;
    @FXML
    private Label lblErrors;
    @FXML
    private TextField tfMotDePasseVisible;
    @FXML
    private CheckBox ckShow;
    @FXML
    private Button btnSignup;
    @FXML
    private Label label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
  

    

 

    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String mdp = tfMotDePasseInvisible.getText();
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = new Utilisateur();
        Pattern pattern = Pattern.compile(regex);
       if (!pattern.matcher(email).matches()) {
            label.setText("Veuillez insérer une adresse mail valide");
            label.setTextFill(Color.web("#F00000"));

        } else {
            if (us.authentification(email, mdp)) {
                Session session=new Session();
                session.setIdUser(us.getUserByEmail(email).getId());
                if (us.getUserByEmail(email).getRole() == CLIENT) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
                    Parent root = loader.load();
                    boutonConnexion.getScene().setRoot(root);

                } else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("BackFXML.fxml"));
                    Parent root = loader.load();
                   boutonConnexion.getScene().setRoot(root);

                }

            } else {
                label.setText("le mot de passe ou l'email entré est incorrecte");
                label.setTextFill(Color.web("#F00000"));

            }

        }

   }

    @FXML
    private void showMdp(ActionEvent event) {
        if (ckShow.isSelected()) {
            tfMotDePasseVisible.setText(tfMotDePasseInvisible.getText());
            tfMotDePasseVisible.setVisible(true);
            tfMotDePasseInvisible.setVisible(false);
            return;
        }
        tfMotDePasseInvisible.setText(tfMotDePasseVisible.getText());
        tfMotDePasseInvisible.setVisible(true);
        tfMotDePasseVisible.setVisible(false);
    }

    @FXML
    private void motDePasseOublié(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RestCodeFXML.fxml"));
        Parent root = loader.load();
        labelMdpOublié.getScene().setRoot(root);
    }
        @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InscrireFXML.fxml"));
        Parent root = loader.load();
        btnSignup.getScene().setRoot(root);
    }
    

}
