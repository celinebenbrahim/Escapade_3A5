/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import static entites.Blocked.non;
import static entites.Role.ADMIN;
import entites.Utilisateur;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import service.Crypt;
import service.UtilisateurService;

/**
 *
 * @author mahdi
 */
public class TestUser {

    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        UtilisateurService us = new UtilisateurService();
        //**********addUser*************

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNaissance = simpleDateFormat.parse("2000-09-02");
        Utilisateur u1 = new Utilisateur("mahdi", "hemriti", "nour.boujmil@esprit.tn", dateNaissance, 26596, "tunis", "2000", "kkk", non, ADMIN);
        //us.ajouter(u1);
        //us.supprimer(u1);
        //**********showUser*************
        //us.modifier(u1);
        //System.out.println(us.afficher());

        System.out.println(us.authentification("nour.boujmil@esprit.tn","2000"));

         
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
                

    }
}
