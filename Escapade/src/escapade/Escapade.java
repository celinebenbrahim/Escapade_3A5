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
import gestionPromoFactureReservation.services.DestinationService;
import gestionPromoFactureReservation.entities.Destination;
import gestionPromoFactureReservation.services.BilletService;
import gestionPromoFactureReservation.entities.Billet;
import gestionPromoFactureReservation.services.HotelService;
import gestionPromoFactureReservation.entities.Hotel;
import gestionPromoFactureReservation.entities.TypeBillet;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.services.UtilisateurService;
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
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here

        //Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateAller = format.parse("2022-02-18");
        Date dateRetour = format.parse("2022-02-19");

        //DESTINATION
        DestinationService destinationService = new DestinationService();
        Destination d = new Destination("Hong Kong", "Delta de la Rivière des Perles");
        Destination d1 = new Destination("Japon", "Tokyo");
        Destination d2 = new Destination("Brésil", "São Paulo");

        destinationService.ajouter(d);
//        System.out.println(destinationService.afficher());
//        destinationService.ajouter(d1);
//        System.out.println(destinationService.afficher());
//        destinationService.ajouter(d2);
//        System.out.println(destinationService.afficher());
//        destinationService.supprimer(d2);
//        System.out.println(destinationService.afficher());
//        destinationService.modifier(d1, 9);
//        System.out.println(destinationService.afficher());
        ///////////HOTEL///////////

       Hotel h = new Hotel("123", "laico", "hammamet", 5, "bon hotel", 500, 150, d);
        Hotel h1 = new Hotel("456", "mavenpick", "hammamet", 5, "bon hotel", 500,200, d);
        //Hotel h2 = new Hotel("789", "Radison", "hammamet", 5, "bon hotel", 500, 6);
        HotelService hotelService = new HotelService();
        hotelService.ajouter(h);
      System.out.println(hotelService.afficher());
    hotelService.ajouter(h1);
        System.out.println(hotelService.afficher());
//        hotelService.ajouter(h2);
//        System.out.println(hotelService.afficher());
       hotelService.supprimer(h);
//        System.out.println(hotelService.afficher());
        hotelService.modifier(h1, 1);
        System.out.println(hotelService.afficher());

        //Utilisateur
//        Utilisateur c1 = new Utilisateur("chennaoui", "celine", "celine@", dateAller, 55989026, "nabeul", "cycy", "221017", Role.CLIENT);
//        UtilisateurService userService = new UtilisateurService();
//        userService.ajouter(c1);
//
//        //BILLET
//        BilletService billetService = new BilletService();
//        Billet b = new Billet(dateAller, dateRetour, TypeBillet.StandBy, 10.2, "Tunisair", c1);
//        Billet b1 = new Billet(dateAller, dateRetour, TypeBillet.openEtRetourModifiable, 10.2, "NouvelAir", c1);
//        Billet b2 = new Billet(dateAller, dateRetour, TypeBillet.prepaid, 10.2, "Qatar", c1);
//        billetService.ajouter(b);
//        System.out.println(billetService.afficher());
//        billetService.ajouter(b1);
//        System.out.println(billetService.afficher());
//        billetService.ajouter(b2);
//        System.out.println(billetService.afficher());
//        billetService.supprimer(b2);
////        System.out.println(billetService.afficher());
////        billetService.modifier(b2, 9);
//        System.out.println(billetService.afficher());

    }

}
