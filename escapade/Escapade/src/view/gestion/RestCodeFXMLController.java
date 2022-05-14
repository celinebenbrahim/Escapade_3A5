/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RestCodeFXMLController implements Initializable {

    private Button Reclamation;
    private Button Reservation;
    @FXML
    private Button boutonValider;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCode;
    @FXML
    private Button boutonEnvoyer;
    @FXML
    private Label label;
    @FXML
    private Label label2;
    
    
      private int code;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //         Random rand = new Random();
//         code = rand.nextInt(999999);
//         System.out.println(code);
    }    

   

    private void Reclamation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReclamationFXML.fxml"));
        Parent root = loader.load();
        Reclamation.getScene().setRoot(root);
    }

    private void Reservation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReservationFXML.fxml"));
        Parent root = loader.load();
        Reservation.getScene().setRoot(root);
        
    }

     @FXML
    private void envoyerCode(ActionEvent event) {
        String email = tfEmail.getText();
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(email).matches()) {
            label.setText("Veuillez insérer une adresse mail valide");
            label.setTextFill(Color.web("#F00000"));

        } else {

            UtilisateurService us = new UtilisateurService();
            if (us.emailExiste(email)) {
                code = us.envoyerCodeVerif(email);
                if (code != -1) {
                    label.setText("code envoyé");
                    label.setTextFill(Color.web("#1CF006"));
                } else {
                    label.setText("erreur:code non envoyé");
                    label.setTextFill(Color.web("#F00000"));

                }

            } else {
                label.setText("L’adresse e-mail que vous avez saisie\nn’est associée à aucun compte");
                label.setTextFill(Color.web("#F00000"));
            }
        }
    }

    @FXML
    private void validerCode(ActionEvent event) throws IOException {
        if (code == Integer.parseInt(tfCode.getText())) {
            FXMLLoader loader = new FXMLLoader();
            UtilisateurService us = new UtilisateurService();
            loader.setLocation(getClass().getResource("ChangeMdpFXML.fxml"));
            Parent root = loader.load();
            //tebaath el utilisateur
            ChangeMdpFXMLController controller=loader.getController();
            controller.setU(us.getUserByEmail(tfEmail.getText()));
            
            boutonValider.getScene().setRoot(root);
        } else {
            label2.setText("Code invalide!");
            label2.setTextFill(Color.web("#F00000"));
        }

    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConxFXML.fxml"));
        Parent root = loader.load();
        Annuler.getScene().setRoot(root);
    }
    
}
