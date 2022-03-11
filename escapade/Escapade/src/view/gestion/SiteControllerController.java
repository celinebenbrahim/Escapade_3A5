
package view.gestion;


import gestionGuideSiteTouristique.entities.Guide;
import gestionGuideSiteTouristique.entities.SiteTouristique;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import gestionGuideSiteTouristique.services.ServiceSiteTouristique;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 
 */
public class SiteControllerController implements Initializable {
public static int  nba = 0 , nbm=0 , nbam=0  ;
    @FXML
    private ImageView home;
    @FXML
    private TextField typeT;
    @FXML
    private ComboBox libelleT;
    @FXML
    private TextField adresseT;
    @FXML
    private TextField descriptionT;
    @FXML
    private TextField prixT;
    @FXML
    private ComboBox<?> idST;
    @FXML
    private TableView<SiteTouristique> tbS;
    @FXML
    private TableColumn<String,SiteTouristique> tC;
    @FXML
    private TableColumn<String ,SiteTouristique> lC;
    @FXML
    private TableColumn<String ,SiteTouristique> aC;
    @FXML
    private TableColumn<String ,SiteTouristique> DesC;
    @FXML
    private TableColumn<Integer ,SiteTouristique> pC;
    @FXML
    private TableColumn<Integer ,SiteTouristique > idC;
    @FXML
    private TextField idt;
    @FXML
    private TextField r;
    @FXML
    private Button b;
    @FXML
    private Button Home;
   
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button Menu;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSignout1;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Ancien","Moderne","Ancien/Moderne");
         libelleT.setItems(list);
         populateTable() ;
         recherch() ; 

        // TODO
    }    

        public void populateTable(){
            ServiceSiteTouristique ser= new ServiceSiteTouristique();
        
        List<SiteTouristique> li =ser.ListClasse();
        ObservableList<SiteTouristique> data = FXCollections.observableArrayList(li);
                 
          
        tC.setCellValueFactory(
                new PropertyValueFactory<>("type"));
 
       
        lC.setCellValueFactory(
                new PropertyValueFactory<>("libelle"));
       aC.setCellValueFactory(  
                new PropertyValueFactory<>("adresse")) ; 
        
        DesC.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        
        pC.setCellValueFactory(
                new PropertyValueFactory<>("prix"));
        
        //idC.setCellValueFactory(
               //new PropertyValueFactory<>("id"));
        
        
        
        
        tbS.setItems(data);
    }
        
    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceSiteTouristique sc =new ServiceSiteTouristique();
        
       List<SiteTouristique> li =sc.ListClasse(); 
          SiteTouristique C= new SiteTouristique( typeT.getText(),libelleT.getSelectionModel().getSelectedItem().toString(),adresseT.getText() , descriptionT.getText() , Integer.parseInt( prixT.getText()) );
              
        
              
               
           
              
        sc.ajouterc(C);
        populateTable() ; 
    }

    @FXML
    private void Getselected(MouseEvent event) {
        SiteTouristique c = new SiteTouristique(); 
   c=tbS.getSelectionModel().getSelectedItem();  
   typeT.setText(c.getType()); 
   libelleT.setValue(c.getLibelle()); 
   adresseT.setText(c.getAdresse());
   descriptionT.setText(c.getDescription());
   prixT.setText(String.valueOf(c.getPrix())); 
   idt.setText(String.valueOf(c.getId())); 
    }

    @FXML
    private void Supprimer(ActionEvent event) {
      ServiceSiteTouristique sc=new ServiceSiteTouristique();
        SiteTouristique c =new SiteTouristique();
      
        c.setId(Integer.parseInt( idt.getText()));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
             Notifications notificationBuilder = Notifications.create()
                                                     .title("Site touristique Supprimeé")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
            populateTable();
            
        }
        else{
            alert.close(); }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
       ServiceSiteTouristique sc = new ServiceSiteTouristique(); 
             
          
        SiteTouristique c = new SiteTouristique(Integer.parseInt( idt.getText()),typeT.getText(),  libelleT.getSelectionModel().getSelectedItem().toString(), adresseT.getText() , descriptionT.getText(),Integer.parseInt( prixT.getText()));
        

        sc.modifierc(c);
         Notifications notificationBuilder = Notifications.create()
                                                     .title("Site touristique modifié")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
        populateTable();
    }
    
    
    @FXML
    void recherch() {          
        tC.setCellValueFactory(new PropertyValueFactory<String,SiteTouristique>("type"));
        lC.setCellValueFactory(new PropertyValueFactory<String,SiteTouristique>("libelle"));
        aC.setCellValueFactory(new PropertyValueFactory<String,SiteTouristique>("adresse"));
        DesC.setCellValueFactory(new PropertyValueFactory<String,SiteTouristique>("description"));
        pC.setCellValueFactory(new PropertyValueFactory<Integer,SiteTouristique>("pix"));
        ServiceSiteTouristique ser= new ServiceSiteTouristique();
        
        List<SiteTouristique> li =ser.ListClasse();
        ObservableList<SiteTouristique> data = FXCollections.observableArrayList(li);
        tbS.setItems(data);
        FilteredList<SiteTouristique> filteredData = new FilteredList<>(data, b -> true);  
 r.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getDescription()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<SiteTouristique> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tbS.comparatorProperty());  
  tbS.setItems(sortedData);      
    }   
       
    @FXML
    private void back(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Admin.fxml"));
        Parent root = loader.load();
        b.getScene().setRoot(root);
    }
    
    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void guide_b(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("GuideController.fxml"));
        Parent root = loader.load();
        btnOrders.getScene().setRoot(root);
    }

    @FXML
    private void Site_b(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("SiteController.fxml"));
        Parent root = loader.load();
        btnSignout1.getScene().setRoot(root);
    }
   
    
     @FXML
     public void calcul(ActionEvent event) throws IOException{
        ServiceSiteTouristique ser= new ServiceSiteTouristique();
        
        List<SiteTouristique> li =ser.ListClasse(); 
        int i = 0; 
        
        for ( i=0 ; i<li.size();i++){
        if (li.get(i).getLibelle().equals("Moderne"))
        
        {nbm=nbm+1;}  ;
        if (li.get(i).getLibelle().equals("Ancien")){nba=nba+1 ; } 
        if (li.get(i).getLibelle().equals("Ancien/Moderne")){nbam=nbam+1 ; }  }
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Sitestat.fxml"));
         Parent root = loader.load();
        typeT.getScene().setRoot(root);
        
        
        
        
        
                
        
  }
    
}
