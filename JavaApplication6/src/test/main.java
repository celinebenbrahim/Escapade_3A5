/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import entites.Reclamation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.ReclamationService;

/**
 *
 * @author mahdi
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
       
        /*Date aujourdhui = new Date();
        formater = new SimpleDateFormat("dd-MM-yy");
        System.out.println(formater.format(aujourdhui));*/
       //creation d'une variable Date
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = simpleDateFormat.parse("2020-12-10");
//        
        ReclamationService rs = new ReclamationService();
//        Reclamation r = new Reclamation(2,date,"mahahahaha",21);
//        //rs.ajouter(r);
//        rs.supprimer(r);
//        System.out.println(r);
      System.out.println(rs.afficher());
    }

}
