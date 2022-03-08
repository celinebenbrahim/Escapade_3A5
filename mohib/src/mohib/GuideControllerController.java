/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mohib;

import mohib.*;
import Entities.Guide;
import Service.ServiceGuide;
import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
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
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class

 */
public class GuideControllerController implements Initializable {

    @FXML
    private TextField nomT;
    
    @FXML
    private TextField idt;
    @FXML
    private TextField pernomT;
    @FXML
    private TextField ntt;
    @FXML
    private TextField emailT;
    @FXML
    private TextField lgT;
    @FXML
    private TableView<Guide> tb;
    @FXML
    private TableColumn<String, Guide> nomC;
    @FXML
    private TableColumn<String, Guide> pC;
    @FXML
    private TableColumn<String, Guide> nC;
    @FXML
    private TableColumn<String, Guide> eC;
    @FXML
    private TableColumn<String, Guide> lC;
      @FXML
    private TableColumn<Integer, Guide> idC;
    @FXML
    private ComboBox<?> idST;
    @FXML
    private Button b;
    @FXML
    private TextField r;
           
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        populateTable() ;
        recherch() ; 
    }    
   
    
    
    public void populateTable(){
        ServiceGuide ser= new ServiceGuide();
        
        List<Guide> li =ser.ListClasse();
        ObservableList<Guide> data = FXCollections.observableArrayList(li);
                  

        
          
        nomC.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
        pC.setCellValueFactory(
                new PropertyValueFactory<>("pernom"));
       nC.setCellValueFactory(  
                new PropertyValueFactory<>("nationnalite")) ; 
        
        eC.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        
        lC.setCellValueFactory(
                new PropertyValueFactory<>("langue"));
        
        //idC.setCellValueFactory(
          //     new PropertyValueFactory<>("id"));
        
        
        
        
        tb.setItems(data);
    }
    
    @FXML
    private void Ajouter(ActionEvent event) {
        
        ServiceGuide sc =new ServiceGuide();
        
       List<Guide> li =sc.ListClasse(); 
          Guide C= new Guide( nomT.getText(),pernomT.getText(),ntt.getText() , emailT.getText() ,  lgT.getText()  );
              
        
              
               
           
              
        sc.ajouterc(C);
        Notifications notificationBuilder = Notifications.create()
                                                     .title("Guide ajouteé")
                                                     .graphic(null)
                                                     .hideAfter(javafx.util.Duration.seconds(5) )
                                                      .position(Pos.TOP_LEFT) ;
         notificationBuilder.show(); 
        populateTable() ; 
        
           
          
         
                 
                                                      
  
}
    
    @FXML
    private void Getselected(MouseEvent event) {
        
   Guide c = new Guide() ; 
   c=tb.getSelectionModel().getSelectedItem();  
   nomT.setText(c.getNom()); 
   pernomT.setText(c.getPernom()); 
   ntt.setText(c.getNationnalite());
  
   emailT.setText(c.getEmail());
      lgT.setText(c.getLangue()); 
   idt.setText(String.valueOf(c.getId())); 


   
   
 } 
    @FXML
    private void Supprimer(ActionEvent event) {
        
        
         ServiceGuide sc=new ServiceGuide();
        Guide c =new Guide();
      
        c.setId(Integer.parseInt( idt.getText()));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de Suppression !");
        alert.setContentText("Voulez-Vous Vraiment Supprimer");
        
        Optional<ButtonType> btn = alert.showAndWait();
        if(btn.get() == ButtonType.OK){
            sc.supprimerc(c);
            Notifications notificationBuilder = Notifications.create()
                                                     .title("Guide Supprimeé")
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
        
            ServiceGuide sc = new ServiceGuide(); 
             
          
        Guide c = new Guide(Integer.parseInt( idt.getText()),nomT.getText(),  pernomT.getText(), ntt.getText() , emailT.getText(),lgT.getText());
        

        sc.modifierc(c);
        populateTable();
        
        
         
        }
        
        @FXML
         private void tri() {
             ServiceGuide ser= new ServiceGuide();
        
        List<Guide> li =ser.ListClasse1();
        ObservableList<Guide> data = FXCollections.observableArrayList(li);
                  

        
          
        nomC.setCellValueFactory(
                new PropertyValueFactory<>("nom"));
 
       
        pC.setCellValueFactory(
                new PropertyValueFactory<>("pernom"));
       nC.setCellValueFactory(  
                new PropertyValueFactory<>("nationnalite")) ; 
        
        eC.setCellValueFactory(
                new PropertyValueFactory<>("email"));
        
        lC.setCellValueFactory(
                new PropertyValueFactory<>("langue"));
        
        idC.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        
        
        
        
        tb.setItems(data);
    

    }
         @FXML
    private void printpdf(ActionEvent event) throws FileNotFoundException, Exception {
                File out = new File("tableguide.pdf") ; 
        FileOutputStream fos = new FileOutputStream(out) ;
        PDF pdf = new PDF(fos) ; 
        Page page = new Page(pdf, A4.PORTRAIT)  ; 
        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD) ;
        Font f2 = new Font(pdf, CoreFont.HELVETICA) ;
        Table table = new Table() ; 
        List<List<Cell>> tabledata = new ArrayList<List<Cell>>() ;       
        List<Cell> tablerow = new ArrayList<Cell>() ; 
        
        Cell cell = new Cell(f1,nomC.getText());
        tablerow.add(cell) ; 
        cell = new Cell(f1,pC.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,nC.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,eC.getText());
        tablerow.add(cell) ;
        cell = new Cell(f1,lC.getText());
        tablerow.add(cell) ;
        
      
        
    tabledata.add(tablerow) ; 
    
    
     
    
 Guide co = new Guide() ; 
   co=tb.getSelectionModel().getSelectedItem();  
        Cell nom = new Cell(f2, co.getNom()) ; 
        Cell p = new Cell(f2,co.getPernom()) ;
         
        
        
        Cell n = new Cell(f2,co.getNationnalite()) ; 
        Cell e = new Cell(f2,co.getEmail()) ; 
        Cell l = new Cell(f2,co.getLangue()) ; 
        tablerow = new ArrayList<Cell>() ; 
        tablerow.add(nom) ; 
        tablerow.add(p) ; tablerow.add(n) ; tablerow.add(e) ; tablerow.add(l) ; 
        
    tabledata.add(tablerow) ; 
    table.setData(tabledata);
    table.setPosition(10f, 60f);
    table.setColumnWidth(0, 90f); 
    table.setColumnWidth(1, 90f); 
    table.setColumnWidth(2, 90f); 
    table.setColumnWidth(3, 200f); 
    table.setColumnWidth(4, 90f);  
    
    
    while(true){
    table.drawOn(page) ; 
    if(!table.hasMoreData()){
    table.resetRenderedPagesCount(); 
    break ; 
    
    }
    
    page=new Page(pdf,A4.PORTRAIT) ; 
    
    
    }
    
    pdf.flush();
    fos.close(); 
        System.out.println("saved to "+out.getAbsolutePath());
        
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        
        loader.setLocation(getClass().getResource("Admin.fxml"));
        Parent root = loader.load();
        b.getScene().setRoot(root);
    }

    @FXML
    void recherch() {          
        nomC.setCellValueFactory(new PropertyValueFactory<String,Guide>("nom"));
        pC.setCellValueFactory(new PropertyValueFactory<String,Guide>("pernom"));
        nC.setCellValueFactory(new PropertyValueFactory<String,Guide>("nationnalite"));
        eC.setCellValueFactory(new PropertyValueFactory<String,Guide>("email"));
        lC.setCellValueFactory(new PropertyValueFactory<String,Guide>("langue"));
        ServiceGuide ser= new ServiceGuide();
        
        List<Guide> li =ser.ListClasse();
        ObservableList<Guide> data = FXCollections.observableArrayList(li);
        tb.setItems(data);
        FilteredList<Guide> filteredData = new FilteredList<>(data, b -> true);  
 r.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getPernom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getNationnalite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Guide> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tb.comparatorProperty());  
  tb.setItems(sortedData);      
    }   
        
        
    }

    



    
    
    

