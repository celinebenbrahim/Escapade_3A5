/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import services.ServiceAgenceLoc;
import entities.AgenceLoc;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServiceMDT;
import utils.Status;

/**
 * FXML Controller class
 *
 * @author KattaX
 */
public class AgenceFormController implements Initializable {

    @FXML
    private AnchorPane container;
    @FXML
    private TextField txt_sujet;
    @FXML
    private TextField txt_adresse;
    @FXML
    private TextField txt_num;
    @FXML
    private Label lb_error;
    @FXML
    private TextField txt_email;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

        btn_ok.setOnAction(e -> {
            lb_error.setText("");
            if (txt_sujet.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le libelle de l'agence !");
            } else if (txt_num.getText().length()!=8) {
                lb_error.setText("vous devez saisir un numéro valide !");
               
            } else if (txt_adresse.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir l'adresse !");
            } else if (txt_email.getText().contains("@")== false) {
                lb_error.setText("email invalide sans @");
            } else {
                ServiceAgenceLoc RS = new ServiceAgenceLoc();
                AgenceLoc r = new AgenceLoc();
                r.setLibelle(txt_sujet.getText());
                r.setAdresse(txt_adresse.getText());
                r.setEmail(txt_email.getText());
                r.setNumTel(Integer.parseInt(txt_num.getText()));
               
              


                try {
                    RS.ajouterAgence(r);
                     String title = "Ajouté";
        String message = "L'agence est ajouté";
        Notification notification = Notifications.SUCCESS;
           TrayNotification tray = new TrayNotification(title, message, notification);
        tray.showAndWait();
                } catch (SQLException ex) {
                    Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                   // MailReclamation.sendMail(user.getEmail(), r);
                } catch (Exception ex) {
                    Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesAgences.fxml"));
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
                newLoadedPane = FXMLLoader.load(getClass().getResource("/GUI/MesAgences.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(TransportFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
        });
    }

        // TODO
    }    
    

