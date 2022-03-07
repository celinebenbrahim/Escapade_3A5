/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author mahdi
 */
public class ProfilFormController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private DatePicker tfDateNaissance;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfNumTel;
    
    private Utilisateur user;
    @FXML
    private Button boutonModifier;
    @FXML
    private Label labelMDP;

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
    

    /**
     * Initializes the controller class.
     */
    
    public void Profil() {
        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfEmail.setText(user.getEmail());
        //LocalDate d=user.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date=Instant.ofEpochMilli(user.getDateNaissance().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        tfDateNaissance.setValue(date);
        tfVille.setText(user.getVille());
        tfNumTel.setText(Integer.toString(this.user.getNumTel()));
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void modifier(ActionEvent event) throws ParseException {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String email = tfEmail.getText();
        String ville = tfVille.getText();
        //String image = tfImage.getText();
        LocalDate localDate = tfDateNaissance.getValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNaissance = simpleDateFormat.parse(localDate.toString());
        int numTel = Integer.parseInt(tfNumTel.getText());
        Utilisateur user = new Utilisateur(this.user.getId(),nom, prenom, email, dateNaissance, numTel, ville);
        UtilisateurService us = new UtilisateurService();
        us.modifier(user);
     
        this.user=user;
    }

    @FXML
    private void changerMdp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            UtilisateurService us = new UtilisateurService();
            loader.setLocation(getClass().getResource("ChangeMdpForm.fxml"));
            Parent root = loader.load();
            //tebaath el utilisateur
            ChangeMdpFormController controller=loader.getController();
            controller.setU(us.getUserByEmail(tfEmail.getText()));
            
            labelMDP.getScene().setRoot(root);
        
    }
    
}
