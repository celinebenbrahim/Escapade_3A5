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

        //Facture
        FactureService fs=new FactureService();
        Facture f=new Facture(100,null,1,500);
        Facture f1=new Facture(200,null,1,1000);
        fs.ajouter(f1);
        System.out.println( fs.afficher());
        fs.supprimer(f);
        fs.modifier(f,1);
        
        
        
        
        //Promotion
         PromotionService ps=new PromotionService();
         Promotion p=new Promotion(10,null,null);
         Promotion p1=new Promotion(20,null,null);
         ps.ajouter(p);
         ps.ajouter(p1);
         System.out.println( ps.afficher());
        ps.modifier(p,1);
        ps.supprimer(p1);
    }

}
