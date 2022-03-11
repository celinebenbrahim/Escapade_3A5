/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escapade;

import escapade.utils.DataSource;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import gestionHotelDestination.services.DestinationService;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.services.HotelService;
import gestionHotelDestination.entities.Hotel;
import gestionHotelDestination.entities.Status;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.TypeBillet;
import gestionHotelDestination.entities.VueSurMer;
import gestionHotelDestination.services.ChambreService;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import gestionPromoFactureReservation.services.FactureService;
import static gestionUserReclamation.entities.Blocked.non;
import static gestionUserReclamation.entities.Role.ADMIN;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author Meryem
 */
public class Escapade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {
        // TODO code application logic here

//        //Date
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date dateAller = format.parse("2022-02-18");
//        Date dateRetour = format.parse("2022-02-19");

        //DESTINATION
//        System.out.println("LISTE DESTINATION \n \n \n");
//        DestinationService destinationService = new DestinationService();
//        Destination d = new Destination("Hong Kong", "Delta de la Rivière des Perles");
//        Destination d1 = new Destination("Japon", "Tokyo");
//        Destination d2 = new Destination("Brésil", "São Paulo");
//
//        destinationService.ajouter(d);
//        System.out.println(destinationService.afficher());
//        destinationService.ajouter(d1);
//        System.out.println(destinationService.afficher());
//        destinationService.ajouter(d2);
//        System.out.println(destinationService.afficher());
//        destinationService.supprimer(d2);
//        System.out.println(destinationService.afficher());
//        destinationService.modifier(d1, 9);
//        System.out.println(destinationService.afficher());
//        destinationService.rechercher("Hong Kong");

        ///////////HOTEL///////////
//         System.out.println("\n \n LISTE HOTEL \n \n \n");
//        Hotel h = new Hotel("123", "laico", "hammamet", 1, "bon hotel", 500, 150, d);
//        Hotel h1 = new Hotel("456", "mavenpick", "hammamet", 5, "bon hotel", 500, 200, d);
//        Hotel h2 = new Hotel("456", "mavenpick", "hammamet", 2, "bon hotel", 500, 200, d);
//        Hotel h3 = new Hotel("789", "Radison", "hammamet", 5, "bon hotel", 500, 250, d1);
//        HotelService hotelService = new HotelService();
//        hotelService.ajouter(h);
//        System.out.println(hotelService.afficher());
//        hotelService.ajouter(h1);
//        hotelService.ajouter(h2);
//        System.out.println(hotelService.afficher());
//        hotelService.ajouter(h3);
//        System.out.println(hotelService.afficher());
//        hotelService.supprimer(h);
//        System.out.println(hotelService.afficher());
//       // hotelService.modifier(h1, 1);
//        System.out.println(hotelService.afficher());
//        System.out.println(hotelService.tri());
//        hotelService.rechercher("laico");
//         hotelService.ajouter(h);

//Utilisateur
//System.out.println("\n \n LISTE UTILISATEUR \n \n \n");
       //UtilisateurService us = new UtilisateurService();
        //**********addUser*************
     /*   ChambreService cs=new ChambreService();
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNaissance = simpleDateFormat.parse("2022-09-02");
        System.out.println(cs.rechercheChambre(dateNaissance, 3));*/
        //Utilisateur u1 = new Utilisateur(98,"kk", "hemriti", "nour.boujmil@esprit.tn", dateNaissance, 26596, "tunis", "2000", "kkk", non, ADMIN);
        //us.ajouter(u1);
       //us.supprimer(u1);
        //**********showUser*************
     //   us.modifier(u1);
       // System.out.println(us.afficher());

       // System.out.println(us.authentification("nour.boujmil@esprit.tn","2000"));

         
        //System.out.println(us.recherche("1","id","asc"));
//        String h=Crypt.hash("2000");
//        String b=Crypt.hash("2000");
//        System.out.println(b.equals(h));
//        System.out.println(h);
//        System.out.println(b);
        //us.envoyerCodeVerif("houssem.riahi@esprit.tn");
        //System.out.println(us.getRandomCode());
        //us.modifierMotDePasse(u1, "kkk", "kkk");
        //System.out.println(us.getUser(9));
     //   userService.ajouter(c1);

//        //BILLET
//        System.out.println("\n \n LISTE BILLET \n \n \n");
//        BilletService billetService = new BilletService();
//        Billet b = new Billet(dateAller, dateRetour, TypeBillet.AllerOuRetourSimple, 10.2, "Tunisair", c1);
//        Billet b1 = new Billet(dateAller, dateRetour, TypeBillet.tarifAffaires, 10.2, "NouvelAir", c1);
//        Billet b2 = new Billet(dateAller, dateRetour, TypeBillet.AllerOuRetourSimple, 10.2, "Qatar", c1);
//        billetService.ajouter(b);
//       // System.out.println(billetService.afficher());
//        billetService.ajouter(b1);
//       // System.out.println(billetService.afficher());
//        billetService.ajouter(b2);
//        System.out.println(billetService.afficher());
////        billetService.supprimer(b1);
////        System.out.println(billetService.afficher());
//        billetService.modifier(b2, 9);
////        System.out.println(billetService.afficher());
////        billetService.rechercher("tarifAffaires");

//        //////////////////CHAMBRE/////////////////////////////
//        
//        System.out.println("\n \n LISTE CHAMBRE \n \n \n");
//        ChambreService chambreService = new ChambreService();
//        Chambre ch = new Chambre(20, Type.doublee, VueSurMer.yes, "aa", 180.0, Status.disponible, h1);
//    //  Chambre ch1 = new Chambre(30, Type.doublee, VueSurMer.yes, "aba", 170.0, Status.disponible, h);
//        chambreService.ajouter(ch);
//       // chambreService.ajouter(ch1);
//        System.out.println(chambreService.afficher());
//      //  chambreService.modifier(ch, 1);
//      //  chambreService.supprimer(ch);
//       //System.out.println("recherche dispo");
//      //  chambreService.rechercher("disponible");
//        chambreService.updateStatus(ch);
//        
//               System.out.println("update Status");
//
//        System.out.println(chambreService.afficher());
        
        
        
        
         ////Promotion 
//        Date dateD = format.parse("2022-02-18");
//        Date dateF = format.parse("2022-02-19");
//       PromotionService promotionService = new PromotionService();
////       Promotion p = new Promotion(10, dateD, dateF);
////       promotionService.ajouter(p);
////        Promotion p1 = new Promotion(20, dateD, dateF);
////       promotionService.ajouter(p1);
////        Promotion p2 = new Promotion(5, dateD, dateF);
////       promotionService.ajouter(p2);
////System.out.println(promotionService.afficher());
////        System.out.println(promotionService.Tri());
//        // promotionService.rechercher(10);
//        //promotionService.chercher(3);
//        //promotionService.FindById(52);
//        //promotionService.FindByTaux(25);
//        
//
////        promotionService.modifier(p, 1);
////        promotionService.supprimer(p1);
////       promotionService.supprimerId(8);
//
//        //Facture   
//        Date date = format.parse("2022-02-17");
//    // Utilisateur u = new Utilisateur("chennaoui", "celine", "celine@",date, 55989026, "nabeul", "cycy", "221017", Role.client);
//     //  userService.ajouter(u);
//        Promotion p1 = new Promotion(5, dateD, dateF);
//        promotionService.ajouter(p1);
//        FactureService factureService = new FactureService();
//      //  Facture f = new Facture(100, date, u,0,p1);
//       // factureService.ajouter(f);
//       // Facture f1 = new Facture(200, date, u, 1000, p1);
//      // factureService.ajouter(f1);
//      //  System.out.println(factureService.CalculerPrixF(f));
//      System.out.println(factureService.afficher());
//      factureService.CalculerPrixF();
//       System.out.println(factureService.afficher());
//        // System.out.println(factureService.Tri());
//        //factureService.rechercher(format.parse("2022-02-23"));
//       // factureService.chercher(3);
//     //  factureService.supprimerId(85);
////        fs.supprimer(f);
////        fs.modifier(f, 5);

    }

}
