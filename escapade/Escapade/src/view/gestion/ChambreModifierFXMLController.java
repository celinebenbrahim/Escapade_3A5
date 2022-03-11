/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;

import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.entities.Status;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.VueSurMer;
import gestionHotelDestination.services.ChambreService;
import gestionHotelDestination.services.HotelService;
import gestionHotelDestination.services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import static view.gestion.HotelModifierFXMLController.hotel;

/**
 * FXML Controller class
 *
 * @author AH-INFO
 */
public class ChambreModifierFXMLController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private ImageView home;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button Menu;
    @FXML
    private Button Facture;
    @FXML
    private Button Promotion;
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
    private TextField num;
    @FXML
    private ListView img;
    @FXML
    private Button insérer;
    @FXML
    private TextArea description;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<Status> statut;
    @FXML
    private Button annuler;
    private ComboBox<Hotel> hotel;
    @FXML
    private ComboBox<VueSurMer> vsm;
    @FXML
    private ComboBox<Type> type;
    File selectedfile;

    String path_img;

    Upload u = new Upload();
    public static Chambre chambre;
    @FXML
    private Button Modifier;
    private int idChambre;
    @FXML
    private Button back;

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    /**
     * Initializes the controller class.
     */
    public void setChambre() {
        //System.out.println(idChambre);
        ChambreService cs = new ChambreService();
        chambre = cs.getChambreById(idChambre);

        this.img.setAccessibleText(chambre.getImg());
//        this.vsm.getSelectionModel().selectFirst();
//        this.type.getSelectionModel().selectFirst();
//        this.hotel.getSelectionModel().selectFirst();
//        this.statut.getSelectionModel().selectFirst();
        this.prix.setText(chambre.getPrixNuit() + "");
        this.num.setText(chambre.getNum() + "");
        this.description.setText(chambre.getDescription());
        statut.getItems().add(Status.disponible);
        statut.getItems().add(Status.indisponible);
        statut.getSelectionModel().select(chambre.getStatus());
        type.getItems().add(Type.simple);
        type.getItems().add(Type.doublee);

        type.getSelectionModel().select(chambre.getType());
        vsm.getItems().add(VueSurMer.yes);
        vsm.getItems().add(VueSurMer.no);
        vsm.getSelectionModel().select(chambre.getVueSurMer());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("aba");

        //  ListerHotel();
        System.out.println("aba");

        insérer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
                );
                selectedfile = fc.showOpenDialog(null);
                if (selectedfile != null) {

                    Upload u = new Upload();
                    try {
                        u.upload(selectedfile);
                    } catch (IOException ex) {
                        Logger.getLogger(ChambreModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    img.getItems().add(selectedfile.getName());

                    path_img = selectedfile.getAbsolutePath();

                } else {
                    System.out.println("Fichier erroné");
                }

            }

        });

//        Modifier.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//
//                ChambreService chambreService = new ChambreService();
//                //  Hotel h = hotel.getSelectionModel().getSelectedItem();
//
//                VueSurMer vssmm = vsm.getSelectionModel().getSelectedItem();
//                Type typee = type.getSelectionModel().getSelectedItem();
//                Status statu = statut.getSelectionModel().getSelectedItem();
//                int prixx = Integer.parseInt(prix.getText());
//                int numero = Integer.parseInt(num.getText());
//
//                Chambre ch = new Chambre(numero, typee, vssmm, description.getText(),
//                        prixx, statu, selectedfile.getName());
//
//                System.out.println(ch);
//
//                try {
//
//                    chambreService.modifier(ch,idChambre);
//
//                } catch (SQLException ex) {
//                    Logger.getLogger(ChambreModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//                alert0.setTitle("information Dialog");
//                alert0.setHeaderText(null);
//                alert0.setContentText("Votre modification est enregistrée avec succes ");
//                alert0.show();
//                ((Node) event.getSource()).getScene().getWindow().hide();
//
//            }
//        });
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);

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

    private void ListerHotel() {

        HotelService hotelService = new HotelService();
        ObservableList<Hotel> list = FXCollections.observableArrayList();

        try {

            String req = " select idHotel,matriculeFiscale,`nom`, `nbrEtoile`, `description`, "
                    + "`nbChambreTotal`, `maxChambre`, `imgHotel` ,`pays`, `ville` FROM `hotel` JOIN `destination`"
                    + " ON hotel.idDestination = destination.id ";

            PreparedStatement pst = hotelService.conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Hotel h = new Hotel();
                h.setIdHotel(rs.getInt("idHotel"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setNbrEtoile(rs.getInt(4));
                h.setDescription(rs.getString(5));
                h.setNbChambreTotal(rs.getInt(6));
                h.setMaxChambre(rs.getInt(7));
                h.setImg(rs.getString(8));
                Destination d = new Destination(rs.getString(9), rs.getString(10));
                h.setDestination(d);
                list.add(h);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        hotel.setItems(null);
        hotel.setItems(list);

//        statut.getItems().add(Status.disponible);
//        statut.getItems().add(Status.indisponible);
//        type.getItems().add(Type.simple);
//        type.getItems().add(Type.doublee);
//        vsm.getItems().add(VueSurMer.yes);
//        vsm.getItems().add(VueSurMer.no);
    }

    @FXML
    private void modifierChambre(ActionEvent event) throws IOException {
  

                ChambreService chambreService = new ChambreService();
                //  Hotel h = hotel.getSelectionModel().getSelectedItem();

                VueSurMer vssmm = vsm.getSelectionModel().getSelectedItem();
                Type typee = type.getSelectionModel().getSelectedItem();
                Status statu = statut.getSelectionModel().getSelectedItem();
                
                double prixx = Double.parseDouble(prix.getText());
                int numero = Integer.parseInt(num.getText());
                
                System.out.println(vssmm+""+typee+""+""+statu+""+prix+""+numero+"");
                Chambre ch = new Chambre(numero, typee, vssmm, description.getText(),
                        prixx, statu, selectedfile.getName());

                System.out.println(ch);

                try {
System.out.println("222222");
                    chambreService.modifier(ch,idChambre);
                     FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
                        Parent root = loader.load();
                        Modifier.getScene().setRoot(root);

                } catch (SQLException ex) {
                    Logger.getLogger(ChambreModifierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }

//                Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
//                alert0.setTitle("information Dialog");
//                alert0.setHeaderText(null);
//                alert0.setContentText("Votre modification est enregistrée avec succes ");
//                alert0.show();
//                ((Node) event.getSource()).getScene().getWindow().hide();

            }

     @FXML
    private void back(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChambreBackFXML.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    }
       
    

}
