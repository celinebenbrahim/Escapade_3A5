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
import gestionUserReclamation.entities.Utilisateur;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.services.UtilisateurService;
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

        //User
        Date date = format.parse("2022-02-17");
        UtilisateurService utilisateurService  = new UtilisateurService();
        Utilisateur u = new Utilisateur("chennaoui", "celine", "celine@", date, 55989026, "nabeul", "cycy", "221017", Role.CLIENT);
       utilisateurService.ajouter(u);

        ////Promotion 
        Date dateD = format.parse("2022-02-18");
        Date dateF = format.parse("2022-02-19");
        PromotionService promotionService = new PromotionService();
        Promotion p = new Promotion(10, dateD, dateF);
//        promotionService.ajouter(p);
//        Promotion p1 = new Promotion(20, dateD, dateF);
//        ps.ajouter(p1);
//        System.out.println(ps.afficher());
//        ps.modifier(p, 1);
//        ps.supprimer(p1);
//        ps.supprimerId(8);

        //Facture    
        FactureService factureService = new FactureService();
        Facture f = new Facture(100, date, u, 500, p);
          factureService.ajouter(f);
        Facture f1 = new Facture(200, date, u, 1000, p);
        factureService.ajouter(f1);
   System.out.println(factureService.afficher());
//        fs.supprimerId(5);
//        fs.supprimer(f);
//        fs.modifier(f, 5);
//        ;

    }

}
