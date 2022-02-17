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
import java.sql.*;
import java.io.*;
import gestionPromoFactureReservation.services.DestinationService;
import gestionPromoFactureReservation.entities.Destination;
import gestionPromoFactureReservation.services.BilletService;
import gestionPromoFactureReservation.entities.Billet;
import gestionPromoFactureReservation.services.HotelService;
import gestionPromoFactureReservation.entities.Hotel;

/**
 *
 * @author Meryem
 */
public class Escapade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        DestinationService ds = new DestinationService();
        HotelService hs = new HotelService();
        BilletService bs = new BilletService();

        Billet b = new Billet(null, null, 1, 10.2, "Tunisair", 1);
        Billet b1 = new Billet(null, null, 1, 10.2, "NouvelAir", 1);
        Billet b2 = new Billet(null, null, 1, 10.2, "Qatar", 1);

//        Destination d = new Destination("Hong Kong", "Delta de la Rivière des Perles");
//        Destination d1 = new Destination("Japon", "Tokyo");
//        Destination d2 = new Destination("Brésil", "São Paulo");
//        
//        Hotel h = new Hotel("123", "laico", "hammamet", 5, "bon hotel", 500, 6);
//        Hotel h1 = new Hotel("456", "mavenpick", "hammamet", 5, "bon hotel", 500, 6);
//        Hotel h2 = new Hotel("789", "Radison", "hammamet", 5, "bon hotel", 500, 6);
///////////hotel
//        hs.ajouter(h);
//        System.out.println(hs.afficher());
//        hs.ajouter(h1);
//        System.out.println(hs.afficher());
//        hs.ajouter(h2);
//        System.out.println(hs.afficher());
//        hs.supprimer(h2);
//        System.out.println(hs.afficher());
//        hs.modifier(h1, 1);
//        System.out.println(hs.afficher());
//        System.out.println(ds.afficher());
///////////destination
//        ds.ajouter(d);
//        System.out.println(ds.afficher());
//        ds.ajouter(d1);
//        System.out.println(ds.afficher());
//        ds.ajouter(d2);
//        System.out.println(ds.afficher());
//        ds.supprimer(d2);
//        System.out.println(ds.afficher());
//        ds.modifier(d1, 9);
//        System.out.println(ds.afficher());
////////////////billet
        bs.ajouter(b);
        System.out.println(bs.afficher());
        bs.ajouter(b1);
        System.out.println(bs.afficher());
        bs.ajouter(b2);
        System.out.println(bs.afficher());

        bs.supprimer(b2);
        System.out.println(bs.afficher());
        bs.modifier(b2, 9);
        System.out.println(bs.afficher());

    }

}
