/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static entites.Role.ADMIN;
import static entites.Role.CLIENT;
import entites.Utilisateur;
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
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ConxFXMLController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button boutonConnexion;
    @FXML
    private TextField tfEmail;
    @FXML
    private CheckBox ckShow;
    @FXML
    private PasswordField tfMotDePasseInvisible;
    @FXML
    private TextField tfMotDePasseVisible;
    @FXML
    private Label labelMdpOublié;
    @FXML
    private Label label;
    @FXML
    private Button boutonInscri;

    /**
     * Initializes the controller class.
     */
    void emailValidator(TextField tf,Label label){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
         tf.textProperty().addListener((observable, oldValue, newValue) -> {
Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(tfEmail.getText()).matches()) {
            label.setText("Veuillez insérer une adresse mail valide");
            label.setTextFill(Color.web("#F00000"));

        }
                 else{
            label.setText("");
            
        }
        });
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //TextField t=new TextField("ahla");
        emailValidator(tfEmail,label);
        
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Back.getScene().setRoot(root);
    }

    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        String email = tfEmail.getText();
        //String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String mdp = tfMotDePasseInvisible.getText();
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = new Utilisateur();
        //Pattern pattern = Pattern.compile(regex);
//        if (!pattern.matcher(email).matches()) {
//            label.setText("Veuillez insérer une adresse mail valide");
//            label.setTextFill(Color.web("#F00000"));
//
//        } else {
            if (us.authentification(email, mdp)) {
                if (us.getUserByEmail(email).getRole() == CLIENT) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Gui/ProfilForm.fxml"));
                    Parent root = loader.load();
                    ProfilFormController controller = loader.getController();
                    controller.setUser(us.getUserByEmail(email));
                    controller.Profil();
                    boutonConnexion.getScene().setRoot(root);

                } else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/Gui/DashboardAdminForm.fxml"));
                    Parent root = loader.load();
                    boutonConnexion.getScene().setRoot(root);

                }

            } else {
                label.setText("le mot de passe ou l'email entré est incorrecte");
                label.setTextFill(Color.web("#F00000"));

            }

        }

   // }

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
        loader.setLocation(getClass().getResource("/Gui/RestCodeForm.fxml"));
        Parent root = loader.load();
        labelMdpOublié.getScene().setRoot(root);
    }

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Gui/InscriptionForm.fxml"));
        Parent root = loader.load();
        boutonInscri.getScene().setRoot(root);
    }

}
