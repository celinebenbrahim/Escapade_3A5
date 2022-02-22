/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escapade;

import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Promotion;
import gestionPromoFactureReservation.services.PromotionService;
import gestionPromoFactureReservation.services.FactureService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //Facture
//       
        Date date=format.parse("2022-02-17");
      FactureService fs = new FactureService();
        Facture f = new Facture(100,date, 1, 500,2);
      Facture f1 = new Facture(200, date, 1, 1000,1);
      // fs.ajouter(f);
     // fs.ajouter(f1);
       System.out.println(fs.afficher());
        fs.supprimerId(5);
//       fs.supprimer(f);
     // fs.modifier(f, 5);

        //Promotion
//        Date dateD = format.parse("2022-02-18");
//        Date dateF = format.parse("2022-02-19");
//        PromotionService ps = new PromotionService();
//        Promotion p = new Promotion(10, dateD,dateF);
//        Promotion p1 = new Promotion(20,dateD , dateF);
//        ps.ajouter(p);
//       // ps.ajouter(p1);
//        System.out.println(ps.afficher());
//        ps.modifier(p, 1);
//        ps.supprimer(p1);
//        ps.supprimerId(8);
    }

}
