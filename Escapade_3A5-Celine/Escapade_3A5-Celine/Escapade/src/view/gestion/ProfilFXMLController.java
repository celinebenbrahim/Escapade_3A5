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
/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    @FXML
    private Button btnSignout;
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
    @FXML
    private Button boutonModifier;
    @FXML
    private Label labelMDP;
    
     private Utilisateur user;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public void setUser(Utilisateur user) {
        this.user = user;
    }
    
    

//   private Utilisateur user;    
//    public void setUser(Utilisateur user) {
//        this.user = user;
//    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Home(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Acceuil4FXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void Profil(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProfilFXML.fxml"));
        Parent root = loader.load();
        Profil.getScene().setRoot(root);
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReclamationFXML.fxml"));
        Parent root = loader.load();
        Reclamation.getScene().setRoot(root);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReservationFXML.fxml"));
        Parent root = loader.load();
        Reservation.getScene().setRoot(root);
        
    }

    public void Profil() {
        UtilisateurService us=new UtilisateurService();
        Utilisateur user=us.getUserById(id);
        
        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfEmail.setText(user.getEmail());
        //LocalDate d=user.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date=Instant.ofEpochMilli(user.getDateNaissance().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        tfDateNaissance.setValue(date);
        tfVille.setText(user.getVille());
        tfNumTel.setText(Integer.toString(user.getNumTel()));
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
        Utilisateur user = new Utilisateur(id,nom, prenom, email, dateNaissance, numTel, ville);
        UtilisateurService us = new UtilisateurService();
        us.modifier(user);
     
       
    }

    @FXML
    private void changerMdp(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
            UtilisateurService us = new UtilisateurService();
            loader.setLocation(getClass().getResource("ChangeMdpFXML.fxml"));
            Parent root = loader.load();
            //tebaath el utilisateur
            ChangeMdpFXMLController controller=loader.getController();
            controller.setU(us.getUserByEmail(tfEmail.getText()));
            
            labelMDP.getScene().setRoot(root);
        
    }

   
    
}
