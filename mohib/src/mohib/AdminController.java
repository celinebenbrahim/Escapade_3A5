/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mohib;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button g; 
    @FXML
    private Button s;
    @FXML
    private Button b;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GuideController.fxml"));
        Parent root = loader.load();
        g.getScene().setRoot(root);
    }

    @FXML
    private void site(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("SiteController.fxml"));
        Parent root = loader.load();
        s.getScene().setRoot(root);
    }
    
     @FXML
    private void back(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();
        b.getScene().setRoot(root);
    }
}

