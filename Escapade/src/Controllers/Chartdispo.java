package Controllers;
        
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import services.ServiceAgenceLoc;
import services.ServiceMDT;

/**
 * FXML Controller class
 *
 * @author KattaX
 */
public class Chartdispo implements Initializable {
   @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private AnchorPane content;
    @FXML
    private PieChart piechart;
   ServiceMDT m = new ServiceMDT ();
    ServiceAgenceLoc a = new ServiceAgenceLoc();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> data;
        try {
            data = FXCollections.observableArrayList(
                    new PieChart.Data("Disponible", m.countdispo()),
                    new PieChart.Data("Indiponible", m.countindispo()));
                    final PieChart chart = new PieChart(data);
        piechart.setData(data);
        piechart.setTitle("availability");
         XYChart.Series series1 = new XYChart.Series();
        XYChart.Series dataSeries1 = new XYChart.Series();
dataSeries1.setName("Commentaire par agence");

dataSeries1.getData().add(new XYChart.Data("Agence 1", a.countcommentbyid(1)));
dataSeries1.getData().add(new XYChart.Data("Agence 2"  ,a.countcommentbyid(2)));
dataSeries1.getData().add(new XYChart.Data("Agence 3"  , a.countcommentbyid(3)));
barchart.getData().add(dataSeries1);


        } catch (SQLException ex) {
            Logger.getLogger(Chartdispo.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
        // TODO
    }    
    
}
