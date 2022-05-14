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
import gestionHotelDestination.services.BilletService;
import gestionHotelDestination.entities.Billet;
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
        System.out.println("LISTE DESTINATION \n \n \n");
        DestinationService destinationService = new DestinationService();
        Destination d = new Destination("Hong Kong", "Delta de la Rivière des Perles");
        Destination d1 = new Destination("Japon", "Tokyo");
        Destination d2 = new Destination("Brésil", "São Paulo");

        destinationService.ajouter(d);
        System.out.println(destinationService.afficher());
        destinationService.ajouter(d1);
        System.out.println(destinationService.afficher());
        destinationService.ajouter(d2);
        System.out.println(destinationService.afficher());
        destinationService.supprimer(d2);
        System.out.println(destinationService.afficher());
        destinationService.modifier(d1, 9);
        System.out.println(destinationService.afficher());
        destinationService.rechercher("Hong Kong");

        ///////////HOTEL///////////
         System.out.println("\n \n LISTE HOTEL \n \n \n");
        Hotel h = new Hotel("123", "laico", "hammamet", 1, "bon hotel", 500, 150, d);
        Hotel h1 = new Hotel("456", "mavenpick", "hammamet", 5, "bon hotel", 500, 200, d);
        Hotel h2 = new Hotel("456", "mavenpick", "hammamet", 2, "bon hotel", 500, 200, d);
        Hotel h3 = new Hotel("789", "Radison", "hammamet", 5, "bon hotel", 500, 250, d1);
        HotelService hotelService = new HotelService();
        hotelService.ajouter(h);
        System.out.println(hotelService.afficher());
        hotelService.ajouter(h1);
        hotelService.ajouter(h2);
        System.out.println(hotelService.afficher());
        hotelService.ajouter(h3);
        System.out.println(hotelService.afficher());
        hotelService.supprimer(h);
        System.out.println(hotelService.afficher());
       // hotelService.modifier(h1, 1);
        System.out.println(hotelService.afficher());
        System.out.println(hotelService.tri());
        hotelService.rechercher("laico");
         hotelService.ajouter(h);

//Utilisateur
System.out.println("\n \n LISTE UTILISATEUR \n \n \n");
        Utilisateur c1 = new Utilisateur("daghrour", "myriam", "myriam@", dateAller, 55989026,
                "nabeul", "mimi", "221017", Role.client);
        UtilisateurService userService = new UtilisateurService();
        userService.ajouter(c1);

//        //BILLET
        System.out.println("\n \n LISTE BILLET \n \n \n");
        BilletService billetService = new BilletService();
        Billet b = new Billet(dateAller, dateRetour, TypeBillet.AllerOuRetourSimple, 10.2, "Tunisair", c1);
        Billet b1 = new Billet(dateAller, dateRetour, TypeBillet.tarifAffaires, 10.2, "NouvelAir", c1);
        Billet b2 = new Billet(dateAller, dateRetour, TypeBillet.AllerOuRetourSimple, 10.2, "Qatar", c1);
        billetService.ajouter(b);
       // System.out.println(billetService.afficher());
        billetService.ajouter(b1);
       // System.out.println(billetService.afficher());
        billetService.ajouter(b2);
        System.out.println(billetService.afficher());
//        billetService.supprimer(b1);
//        System.out.println(billetService.afficher());
        billetService.modifier(b2, 9);
//        System.out.println(billetService.afficher());
//        billetService.rechercher("tarifAffaires");

//        //////////////////CHAMBRE/////////////////////////////
//        
        System.out.println("\n \n LISTE CHAMBRE \n \n \n");
        ChambreService chambreService = new ChambreService();
        Chambre ch = new Chambre(20, Type.doublee, VueSurMer.yes, "aa", 180.0, Status.disponible, h1);
    //  Chambre ch1 = new Chambre(30, Type.doublee, VueSurMer.yes, "aba", 170.0, Status.disponible, h);
        chambreService.ajouter(ch);
       // chambreService.ajouter(ch1);
        System.out.println(chambreService.afficher());
      //  chambreService.modifier(ch, 1);
      //  chambreService.supprimer(ch);
       //System.out.println("recherche dispo");
      //  chambreService.rechercher("disponible");
        chambreService.updateStatus(ch);
        
               System.out.println("update Status");

        System.out.println(chambreService.afficher());

    }

}
