/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionUserReclamation.entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FactureFrontFXMLController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button Next;
    @FXML
    private Label ListFacture;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }
    
     public void setEvent(Facture f)
    {
        ListFacture.setText("Nom client : " +f.getClient()+" \n Date :\n "+f.getDate()+" \n Prix Total : "+f.getPrixTotal()+"\n Promotion : "+f.getPromotion()+"\n Prix final" +f.getPrixFinal());
    }
    
     
    
    
}
