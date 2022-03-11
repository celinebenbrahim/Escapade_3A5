/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.gestion;


import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.services.ChambreService;
import gestionHotelDestination.services.HotelService;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.FactureService;
import gestionPromoFactureReservation.services.PromotionService;
import gestionUserReclamation.entities.ReservationChambre;
import gestionUserReclamation.entities.Session;
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.ReservationChambreService;
import gestionUserReclamation.services.UtilisateurService;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FactureFrontFXMLController implements Initializable {

    private Label ListFacture;
    @FXML
    private Button Home;
    @FXML
    private Button Profil;
    @FXML
    private Button Reclamation;
    @FXML
    private Button Reservation;
    @FXML
    private Button btnSignout;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumTel;
    @FXML
    private DatePicker tfDateCheckIn;
    @FXML
    private DatePicker tfDateCheckOut;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfVueSurMer;
    @FXML
    private TextField tfPrixTotale;
    @FXML
    private TextField tfTauxPromo;
    @FXML
    private TextField tfPrixFinale;
    @FXML
    private DatePicker tfDate;
    
    private ReservationChambre reservation;
    private Facture facture;
    @FXML
    private Button confirmer;
    @FXML
    private Button Capture;

    public ReservationChambre getReservation() {
        return reservation;
    }

    public void setReservation(ReservationChambre reservation) {
        this.reservation = reservation;
    }




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }
    

    @FXML
    private void Home(ActionEvent event) {
        System.out.println("res="+reservation);
    }

    @FXML
    private void Profil(ActionEvent event) {
    }

    @FXML
    private void Reclamation(ActionEvent event) {
    }

    @FXML
    private void Reservation(ActionEvent event) {
    }
    
    public void afficher() throws ParseException, SQLException{
        //cordonné user
        UtilisateurService us=new UtilisateurService();
        Utilisateur user=us.getUserById(Session.getIdUser());
        System.out.println("nom user="+user.getNom());
        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfEmail.setText(user.getEmail());
        tfNumTel.setText(Integer.toString(user.getNumTel()));
        //chambre
        Chambre ch = new Chambre();
        ChambreService cs =new ChambreService();
        ch=cs.getChambreById(reservation.getIdChambre());
        tfType.setText(String.valueOf(ch.getType()));
        tfVueSurMer.setText(String.valueOf(ch.getVueSurMer()));
        //System.out.println(ch.getHotel());
//        
//        //reservation
System.out.println(reservation.getDateAller()+" / "+reservation.getDateRetour());
        LocalDate date1=Instant.ofEpochMilli(reservation.getDateAller().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        tfDateCheckIn.setValue(date1);
        LocalDate date2=Instant.ofEpochMilli(reservation.getDateRetour().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        tfDateCheckOut.setValue(date2);
        
        LocalDate todaysDate = LocalDate.now();
        tfDate.setValue(todaysDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(todaysDate.toString());
        
        Facture f=new Facture(date,user);
        FactureService fs=new FactureService();
        float prixTotale=(float) fs.calculerPrixTotale(ch.getPrixNuit(), date1, date2);
        f.setPrixTotal(prixTotale);
        tfPrixTotale.setText(String.valueOf(f.getPrixTotal()));
        
        Promotion p=new Promotion();
        PromotionService ps=new PromotionService();
        p=ps.getPromo(date);
        System.out.println("promo"+p);
        double PrixFinale=fs.calculerPrixFinale(prixTotale, p);
        tfTauxPromo.setText(String.valueOf(p.getTaux()));
        tfPrixFinale.setText(String.valueOf(PrixFinale));
        
        f.setPrixFinal((float) PrixFinale);
        f.setPrixTotal(prixTotale);
        if(p.getTaux()!=0){
          f.setPromotion(p);
            
        }

        facture=f;
        
        
    }
    

    @FXML
    private void confirmer(ActionEvent event) throws SQLException {
         FactureService fs=new FactureService();
         ReservationChambreService rs=new ReservationChambreService();
         System.out.println("facture"+facture);
         System.out.println("reservation"+reservation);
         fs.ajouter(facture);
         rs.ajouter(reservation);
        
    }
     
    
    
  @FXML
    private void Capture(ActionEvent event) {
        try {
            Robot robot = new Robot();
            Rectangle rectangle = new Rectangle(420,90,700,400);
            
            BufferedImage image = robot.createScreenCapture(rectangle);
            Image myImage = SwingFXUtils.toFXImage(image, null);
            ImageIO.write(image, "jpg", new File("facture.jpg"));
            System.out.println("capture");
        } catch (Exception e) {
        }
    }
    
    
}
