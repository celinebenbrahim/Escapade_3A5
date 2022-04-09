/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class RestCodeFormController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfCode;
    @FXML
    private Button boutonEnvoyer;
    @FXML
    private Button boutonValider;
    @FXML
    private Label label;
    @FXML
    private Label label2;
    
    private int code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Random rand = new Random();
         code = rand.nextInt(999999);
         System.out.println(code);
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
            loader.setLocation(getClass().getResource("ChangeMdpForm.fxml"));
            Parent root = loader.load();
            //tebaath el utilisateur
            ChangeMdpFormController controller=loader.getController();
            controller.setU(us.getUserByEmail(tfEmail.getText()));
            
            boutonValider.getScene().setRoot(root);
        } else {
            label2.setText("Code invalide!");
            label2.setTextFill(Color.web("#F00000"));
        }

    }

    @FXML
    private void checkNumberOnly(KeyEvent event) {
    }

}
