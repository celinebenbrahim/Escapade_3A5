/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class SitestatController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private Label a_l;
    @FXML
    private Label m_l;
    @FXML
    private Label ma_l;
     @FXML
    private Button b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        int nba=SiteControllerController.nba , nbm=SiteControllerController.nbm, nbam=SiteControllerController.nbam ; 
        pie.setTitle("Stats");
        ObservableList <PieChart.Data> ol = FXCollections.observableArrayList(
        
        new PieChart.Data("Moderne", nbm),new PieChart.Data("Ancien", nba),new PieChart.Data("Ancien/Moderne", nbam)
                
                
           );
        a_l.setText(""+nba); m_l.setText(""+nbm); ma_l.setText(""+nbam);
        pie.setData(ol);
    }    
    
    @FXML
    private void back(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("SiteController.fxml"));
        Parent root = loader.load();
        b.getScene().setRoot(root);
    }
    
}
