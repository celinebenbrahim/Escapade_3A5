/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import entites.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfVille;
    @FXML
    private TextField tfNumTel;
    @FXML
    private PasswordField tfMdp;
    @FXML
    private Button boutonInscription;
    @FXML
    private DatePicker tfDateNaissance;
    @FXML
    private JFXTextField tfImage;

    /**
     * Initializes the controller class.
     */
    public void validateInt(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tf.setText(newValue.replaceAll("[^\\d]", ""));
            }
            System.out.println("textfield changed from " + oldValue + " to " + newValue);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        validateInt(tfNumTel);

    }

    @FXML
    private void Back(ActionEvent event) {
    }

    @FXML
    private void inscription(ActionEvent event) throws ParseException, IOException {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String email = tfEmail.getText();
        String ville = tfVille.getText();
        String image = tfImage.getText();
        LocalDate localDate = tfDateNaissance.getValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNaissance = simpleDateFormat.parse(localDate.toString());
        int numTel = Integer.parseInt(tfNumTel.getText());
        String mdp = tfMdp.getText();
        Utilisateur user = new Utilisateur(nom, prenom, email, dateNaissance, numTel, ville, mdp, image);
        UtilisateurService us = new UtilisateurService();
        us.ajouter(user);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConxFXML.fxml"));
        Parent root = loader.load();
        boutonInscription.getScene().setRoot(root);
    }

    @FXML
    private void upload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String imageFile = "";
        File f = fc.showOpenDialog(null);

        if (f != null) {
            imageFile = f.getAbsolutePath();
            String newfile = imageFile.replace("C:\\Users\\mahdi\\Documents\\NetBeansProjects\\JavaApplication6\\src\\view\\ressources\\images\\", "");
            tfImage.setText(newfile);
        }
    }

}
