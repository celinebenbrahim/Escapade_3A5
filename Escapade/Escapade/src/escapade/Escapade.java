/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escapade;

import gestionAgenceLocationMT.entities.AgenceDeLocation;
import gestionAgenceLocationMT.services.AgenceDeLocationService;

/**
 *
 * @author Meryem
 */
public class Escapade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AgenceDeLocation a1=new AgenceDeLocation(2,"bachir", "kk", "qcsd",11);
        AgenceDeLocationService s=new AgenceDeLocationService();
        //s.ajouter(a1);
        System.out.println(s.afficher());
        //s.modifier(a1);
        
    }
    
}
