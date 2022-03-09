/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionPromoFactureReservation.services.PromotionService;
import gestionUserReclamation.entities.Utilisateur;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
//import static view.gestion.PromotionFXMLController.p;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class FactureFXMLController implements Initializable {

    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    private Button Home;
    @FXML
    private TableView<Facture> ListF;
    @FXML
    private TableColumn<Facture, Float> PrixT;
    @FXML
    private TableColumn<Facture, Date> date;
    @FXML
    private TableColumn<Facture, Utilisateur> idC;
    @FXML
    private TableColumn<Facture, Float> PrixF;
    @FXML
    private TableColumn<Facture, Promotion> Taux;
    @FXML
    private Button AfficheF;

    public static Facture f;
    @FXML
    private Button btnOverview;
    @FXML
    private ImageView home;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox pnItems;
    @FXML
    private Button Modif;
    @FXML
    private Button SupFac;
    @FXML
    private Button AjouterF;
    @FXML
    private Button RechercheF;
    @FXML
    private Button imprimerfacture;

    ObservableList<Facture> obl = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            tableInit();
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("MenuFXML.fxml"));
        Parent root = loader.load();
        Menu.getScene().setRoot(root);
    }

    @FXML
    private void Facture(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("FactureFXML.fxml"));
        Parent root = loader.load();
        Facture.getScene().setRoot(root);
    }

    @FXML
    private void Promotion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("PromotionFXML.fxml"));
        Parent root = loader.load();
        Promotion.getScene().setRoot(root);
    }

    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

  
    @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {

        if (ListF.getSelectionModel().getSelectedItem() != null) {

            Facture facture = ListF.getSelectionModel().getSelectedItem();
            f = facture;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FactureModifierFXML.fxml"));
            Parent root = loader.load();
            FactureModifierFXMLController FM = loader.getController();
            FM.setFacture(f);
               try {
            
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
              try {
         
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une facture");
            alert.show();
        }
    }

    @FXML
    private void SupFac(ActionEvent event) throws SQLException {
        
         if (ListF.getSelectionModel().getSelectedItem() != null) {
             
            try {
                int id=ListF.getSelectionModel().getSelectedItem().getIdF();
                System.out.println(id);
                FactureService factureService = new FactureService();
                factureService.supprimerId(id);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Supression avec succes");
                alert.show();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(PromotionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
         
              Afficher();   
           
        
         } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
 }
    public void tableInit() {

        PrixT.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        idC.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        PrixF.setCellValueFactory(new PropertyValueFactory<>("PrixFinal"));
        Taux.setCellValueFactory(new PropertyValueFactory<>("idPromotion"));
        ObservableList<String> data = FXCollections.observableArrayList("prixT", "Date", "idC");
       //triBox.setItems(data);

    }

    @FXML
    public void Afficher() throws SQLException {
         obl = FXCollections.observableArrayList();
      FactureService factureService = new FactureService();
//        for (Facture facture : factureService.afficher()) {
//            obl.add(facture);
//            // code block to be executed
//        }
//        ListF.setItems(obl);
//        

        /* add column to the tableview and set its items */
        ObservableList<Facture> facture = FXCollections.observableArrayList(factureService.afficher());
       ListF.setItems(facture);
      
   

        PrixT.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Facture, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixTotal());
            }
        });
        
        date.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Facture, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate());
            }
        });
        

        idC.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Utilisateur>, ObservableValue<Utilisateur>>() {
            @Override
            public ObservableValue<Utilisateur> call(TableColumn.CellDataFeatures<Facture, Utilisateur> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getClient().getNom());
            }
        });

        PrixF.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Facture, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrixFinal());
            }
        });

        Taux.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Facture, Promotion>, ObservableValue<Promotion>>() {
            @Override
            public ObservableValue<Promotion> call(TableColumn.CellDataFeatures<Facture, Promotion> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPromotion().getTaux());
            }
        });

    }
    @FXML
    private void AjouterF(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FactureAjouterFXML.fxml"));
        Parent root = loader.load();
        // AjoutP.getScene().setRoot(root);

        FactureAjouterFXMLController fa = loader.getController();
 
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
          try {
            // PrixT.setCellValueFactory(new PropertyValueFactory<>("PrixTotal"));
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void RechercheF(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("RechercheFFXML.fxml"));
        Parent root = loader.load();
        RechercheF.getScene().setRoot(root);
    }

     @FXML
    private void imprimerfacture(ActionEvent event) throws DocumentException, SQLException, ClassNotFoundException {
        
               try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escapade", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from facture");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Table_des_factures.pdf"));
                       
                        my_pdf_report.open();  
                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Listes des factures"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(6);
                             
                       //create a cell object
                       PdfPCell table_cell;
                                          table_cell=new PdfPCell(new Phrase("idF"));
                                       table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                                         table_cell=new PdfPCell(new Phrase("prixTotal"));
                                       table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date"));
                                      table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("idClient"));
                                       table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                               
                                      table_cell=new PdfPCell(new Phrase("prixFinal"));
                                       table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                                        table_cell=new PdfPCell(new Phrase("idPromotion"));
                                       table_cell.setBackgroundColor(BaseColor.BLUE);
                                       my_report_table.addCell(table_cell);
                                       
                                       
                                       
                                      while(rs.next()){
                                         // int d=rs.getInt("idF");
                                          String d=rs.getString("idF");
                                       table_cell=new PdfPCell(new Phrase(d));
                                       my_report_table.addCell(table_cell);
                                          String de=rs.getString("prixTotal");
                                       table_cell=new PdfPCell(new Phrase(de));
                                       my_report_table.addCell(table_cell);
                                       String idRdv= rs.getString("date");
                                       table_cell=new PdfPCell(new Phrase(idRdv));
                                       my_report_table.addCell(table_cell);
                                       String type=rs.getString("idClient");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String ds=rs.getString("prixFinal");
                                       table_cell=new PdfPCell(new Phrase(ds));
                                       my_report_table.addCell(table_cell);
                                        String dn=rs.getString("idPromotion");
                                       table_cell=new PdfPCell(new Phrase(dn));
                                       my_report_table.addCell(table_cell);
                                       
                                       
                                               
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "imprimer avec succés");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
    }


    

}
