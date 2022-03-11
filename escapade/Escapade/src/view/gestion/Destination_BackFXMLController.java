/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.services.DestinationService;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class Destination_BackFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
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
    @FXML
    private Button ajouterDest;
    @FXML
    private Button updateDest;
    @FXML
    private Button deleteDest;
    @FXML
    private Button showDest;
    @FXML
    private TableColumn<Destination, String> pays;
    @FXML
    private TableColumn<Destination, String> ville;
    @FXML
    private TableColumn<Destination, ImageView> imgDest;
    @FXML
    private TableView<Destination> tableDest;

    @FXML
    private Button rechercherD;
    public static Destination dest;
    @FXML
    private Button pdf;
    @FXML
    private Button pdfpdf;
    @FXML
    private Button exportXl;
    @FXML
    private Button destination;
    @FXML
    private Button hotel;
    @FXML
    private Button chambre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addMapButton();
        showDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    Afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        deleteDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableDest.getSelectionModel().getSelectedItem() != null) {
                    try {
                        int id = tableDest.getSelectionModel().getSelectedItem().getId();
                        DestinationService destinationService = new DestinationService();
                        destinationService.supprimerId(id);
                    } catch (SQLException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        Afficher();
                    } catch (SQLException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner une destination");
                    alert.show();
                }
            }
        });

        updateDest.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableDest.getSelectionModel().getSelectedItem() != null) {

                    try {

                        Destination d = tableDest.getSelectionModel().getSelectedItem();
                        System.out.println("1");
                        d.setId(tableDest.getSelectionModel().getSelectedItem().getId());
                        System.out.println("1");
                        dest = d;
                        System.out.println("1");
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("DestinationModifierFXML.fxml"));
                        Parent root = loader.load();

                        DestinationModifierFXMLController dc = loader.getController();
                        dc.setDestination(d);
                        try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.showAndWait();
                        try {
                            Afficher();
                        } catch (SQLException ex) {
                            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous devez selectionner une destination");
                    alert.show();
                }
            }

        });

    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }

    public void Afficher() throws SQLException {

        DestinationService destinationService = new DestinationService();

        ObservableList<Destination> listeDest = FXCollections.
                observableArrayList(Destination.generateImageViews(destinationService.afficher()));

        tableDest.setItems(listeDest);

        /* add column to the tableview and set its items */
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPays());
            }
        });
        ville.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Destination, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille());
            }
        });

        imgDest.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Destination, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Destination, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });

    }

    @FXML
    private void rechercherD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("DestinationSearchFXML.fxml"));
        Parent root = loader.load();
        rechercherD.getScene().setRoot(root);
    }

    @FXML
    private void AjoutDest(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DestinationAjouterFXML.fxml"));
        Parent root = loader.load();
        // ajouterDest.getScene().setRoot(root);
        DestinationAjouterFXMLController dc = loader.getController();

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.showAndWait();
        try {
            Afficher();
        } catch (SQLException ex) {
            Logger.getLogger(Destination_BackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void pdfev(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) pdf.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/view/gestion/Chart.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    
    
    @FXML
    private void pdfpdf(ActionEvent event) throws DocumentException, SQLException, ClassNotFoundException, BadElementException, IOException{
        
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/escapade", "root", "");
            PreparedStatement pt = con.prepareStatement("select * from destination");
            ResultSet rs = pt.executeQuery();

            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();

            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("TableDesDestinations.pdf"));

            my_pdf_report.open();
            my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("C:\image.png");
//                            my_pdf_report.add(img);
            my_pdf_report.add(new Paragraph("Listes des destinations \n "));

            my_pdf_report.addCreationDate();

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(3);

            //create a cell object
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("PAYS"));
            table_cell.setBackgroundColor(BaseColor.PINK);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("VILLE"));
            table_cell.setBackgroundColor(BaseColor.PINK);
            my_report_table.addCell(table_cell);

            table_cell = new PdfPCell(new Phrase("IMAGE"));
            table_cell.setBackgroundColor(BaseColor.PINK);
            my_report_table.addCell(table_cell);

            while (rs.next()) {

                String id = rs.getString("pays");
                table_cell = new PdfPCell(new Phrase(id));
                my_report_table.addCell(table_cell);

                String dd = rs.getString("ville");
                table_cell = new PdfPCell(new Phrase(dd));
                my_report_table.addCell(table_cell);

               String df = rs.getString("img");
               Image img = Image.getInstance("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\"+rs.getString("img"));
          //  my_pdf_report.add(img);
              //  table_cell = new PdfPCell(new Phrase(df));
                my_report_table.addCell(img);

            }
            /* Attach report table to PDF */

            my_pdf_report.add(my_report_table);
            System.out.println(my_pdf_report);
            my_pdf_report.close();
            JOptionPane.showMessageDialog(null, "imprimé avec succès");

            /* Close all DB related objects */
            rs.close();
            pt.close();
            con.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    
    private void addMapButton() {
        TableColumn<Destination, Void> colBtn = new TableColumn("         Map          ");

        Callback<TableColumn<Destination, Void>, TableCell<Destination, Void>> cellFactory = new Callback<TableColumn<Destination, Void>, TableCell<Destination, Void>>() {
            @Override
            public TableCell<Destination, Void> call(final TableColumn<Destination, Void> param) {
                final TableCell<Destination, Void> cell = new TableCell<Destination, Void>() {

                    private final Button btn = new Button("  Map  ");

                    {

                        btn.setOnAction((ActionEvent event) -> {

                            Destination data = getTableView().getItems().get(getIndex());
                            try {
                                Desktop.getDesktop().browse(
                                        new URL("https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q="+ data.getVille()+"+(Escapade)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed").toURI());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                          
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableDest.getColumns().add(colBtn);

    }

    @FXML
    private void exportXl(ActionEvent event) {
       
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Destinations");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableDest.getColumns().size()-2; j++) {
            row.createCell(j).setCellValue(tableDest.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableDest.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableDest.getColumns().size()-2; j++) {
                if (tableDest.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(tableDest.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("Destination.xls");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("here");
        } catch (FileNotFoundException ex) {
            System.out.println("here ex1");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("here ex2");
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void destination(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Destination_BackFXML.fxml"));
        Parent root = loader.load();
        destination.getScene().setRoot(root);
    }

    @FXML
    private void hotel(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("HotelBackFXML.fxml"));
        Parent root = loader.load();
        hotel.getScene().setRoot(root);
    }

    @FXML
    private void chambre(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
        Parent root = loader.load();
        chambre.getScene().setRoot(root);
    }

}
