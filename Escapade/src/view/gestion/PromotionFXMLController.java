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
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Comparator;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class PromotionFXMLController implements Initializable {

    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button AjoutP;
    @FXML
    private Button AfficherP;
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
    private Button SupPromo;
    @FXML
    private TableView<Promotion> tablePromo;
    @FXML
    private TableColumn<Promotion, String> taux;
    @FXML
    private TableColumn<Promotion, String> dateD;
    @FXML
    private TableColumn<Promotion, String> dateF;

    public static Promotion promo;
    @FXML
    private Button Modifier;

    @FXML
    private Button rechercheP;
    @FXML
    private Button PDF;
    @FXML
    private TextField in_search;

    ObservableList<Promotion> obl = FXCollections.observableArrayList();
    //  FilteredList<Promotion> filteredData = new FilteredList<>(obl, b -> true);
    // ObservableList<Promotion> dataList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> triBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            tableInit();
            Afficher();
            search();
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tableInit() {

        taux.setCellValueFactory(new PropertyValueFactory<>("Taux"));
        dateD.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        dateF.setCellValueFactory(new PropertyValueFactory<>("DateFin"));
        ObservableList<String> data = FXCollections.observableArrayList("Taux", "DateDebut", "DateFin");
        triBox.setItems(data);

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

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    @FXML
    private void AjoutP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PromotionAjouterFXML.fxml"));
        // loader.setLocation(getClass().getResource("PromotionAjouterFXML.fxml"));
        Parent root = loader.load();
        // AjoutP.getScene().setRoot(root);

        PromotionAjouterFXMLController pm = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        try {

            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void SupPromo(ActionEvent event) throws SQLException {
        if (tablePromo.getSelectionModel().getSelectedItem() != null) {
            try {
                int id = tablePromo.getSelectionModel().getSelectedItem().getId();
                System.out.println(id);
                PromotionService promotionService = new PromotionService();

                promotionService.supprimerId(id);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("supression avec succes");
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

    @FXML
    public void Afficher() throws SQLException {
        PromotionService promotionService = new PromotionService();
        obl = FXCollections.observableArrayList();
         for (Promotion promo : promotionService.afficher()) {
            obl.add(promo);
            // code block to be executed
        }
        tablePromo.setItems(obl);

        //ObservableList<Promotion> listePromo = FXCollections.observableArrayList(promotionService.afficher());
//        tablePromo.setItems(listePromo);
//
//        taux.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Float>, ObservableValue<Float>>() {
//            @Override
//            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Promotion, Float> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getTaux());
//            }
//        });
//        dateD.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Date>, ObservableValue<Date>>() {
//            @Override
//            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Promotion, Date> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getDateDebut());
//            }
//        });
//        dateF.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Promotion, Date>, ObservableValue<Date>>() {
//            @Override
//            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Promotion, Date> param) {
//                return new ReadOnlyObjectWrapper(param.getValue().getDateFin());
//            }
//        });

    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {

        if (tablePromo.getSelectionModel().getSelectedItem() != null) {
            Promotion p = tablePromo.getSelectionModel().getSelectedItem();
            promo = p;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PromotionModifierFXML.fxml"));
            Parent root = loader.load();
            PromotionModifierFXMLController pm = loader.getController();
            pm.setPromo(p);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            Afficher();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une promotion");
            alert.show();
        }
    }

    @FXML
    private void rechercheP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("RecherchePFXML.fxml"));
        Parent root = loader.load();
        rechercheP.getScene().setRoot(root);

    }

    @FXML
    private void PDF(ActionEvent event) throws DocumentException, SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escapade", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from promotion");
            ResultSet rs = pt.executeQuery();

            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Table_des_promotions.pdf"));

            my_pdf_report.open();
            my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
            my_pdf_report.add(new Paragraph("Listes des promotion"));

            my_pdf_report.addCreationDate();

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(3);

            //create a cell object
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("taux"));
            table_cell.setBackgroundColor(BaseColor.BLUE);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("dateDebut"));
            table_cell.setBackgroundColor(BaseColor.BLUE);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("dateFin"));
            table_cell.setBackgroundColor(BaseColor.BLUE);
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String id = rs.getString("taux");
                table_cell = new PdfPCell(new Phrase(id));
                my_report_table.addCell(table_cell);

                String dd = rs.getString("dateDebut");
                table_cell = new PdfPCell(new Phrase(dd));
                my_report_table.addCell(table_cell);

                String df = rs.getString("dateFin");
                table_cell = new PdfPCell(new Phrase(df));
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

 @FXML
    private void Capture(ActionEvent event) {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(180,0,1000,600);
            BufferedImage image = robot.createScreenCapture(rectangle);
            Image myImage = SwingFXUtils.toFXImage(image, null);
            ImageIO.write(image, "jpg", new File("facture.jpg"));
            System.out.println("capture");
        } catch (Exception e) {
        }
    }
    
     @FXML
    public void search() {

        in_search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<Promotion> filteredData = new FilteredList(obl);
            
            filteredData.setPredicate(promo -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(promo.getTaux()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (String.valueOf(promo.getDateDebut()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches datedenaissance
                } else if (String.valueOf(promo.getDateFin()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches datedenaissance
                } else {
                    return false; // Does not match.
                }
            });
            //obl=filteredData;
            SortedList<Promotion> sortableData = new SortedList<>(filteredData);
            tablePromo.setItems(sortableData);
            sortableData.comparatorProperty().bind(tablePromo.comparatorProperty());
        });

    }

    

}
