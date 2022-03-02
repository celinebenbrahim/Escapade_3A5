/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.sql.*;
import escapade.utils.DataSource;
import gestionPromoFactureReservation.entities.Facture;
import gestionPromoFactureReservation.entities.Iservice;
import gestionPromoFactureReservation.entities.Promotion;
import gestionUserReclamation.entities.Utilisateur;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class FactureService implements Iservice<Facture> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public FactureService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Facture f) {
        String req = "insert into `facture`  (`prixTotal`,`idClient`,`prixFinal`,`idpromotion`) values(?,?,?,?)";
        try {

            pst = conn.prepareStatement(req, pst.RETURN_GENERATED_KEYS);
            pst.setFloat(1, f.getPrixTotal());
            pst.setInt(2, f.getClient().getId());
            pst.setFloat(3, f.getPrixFinal());
            pst.setInt(4, f.getPromotion().getId());
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {

                f.setId(generatedKeys.getInt(1));
            }

            pst.executeUpdate();
           
            System.out.println("Facture ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Facture f) {
        String req = "delete from facture where id = " + f.getId();

        try {

            pst = conn.prepareStatement(req);
            //  pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Facture supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Facture f, int id) {
        String req = "update `facture` SET prixTotal=?,date=?,Client=?,prixFinal=?,promotion=? where id=" + id;

        try {
            pst = conn.prepareStatement(req);
            pst.setFloat(1, f.getPrixTotal());
            pst.setDate(2, new java.sql.Date(f.getDate().getTime()));
            pst.setInt(3, f.getClient().getId());
            pst.setFloat(4, f.getPrixFinal());
            pst.setInt(5, f.getPromotion().getId());
            pst.executeUpdate();
            pst.close();
            System.out.println("Facture modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerId(int id) {
        String req = "delete from Facture where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Facture supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    public void rechercher(java.util.Date date) {
        List<Facture> result = afficher().stream().filter(line -> date.equals(line.getDate())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
    }

    public boolean chercher(int id) throws SQLException {
        String req = "select * from facture";
        List<Integer> list = new ArrayList<>();

        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Facture.class.getName()).log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }

    @Override
    public List<Facture> afficher() {
        List<Facture> factures = new ArrayList<>();
        String req = "SELECT `prixTotal`, `date`, `prixFinal`,`nom`, `prenom`, "
                + "`email`, `numTel`, `ville` ,`taux` FROM `facture`  INNER JOIN `utilisateur`"
                + " ON facture.idclient = utilisateur.id LEFT JOIN `promotion` on facture.idPromotion ="
                + " promotion.id";
        try {
            pst = conn.prepareStatement(req);
            ResultSet fs = pst.executeQuery();

            while (fs.next()) {
                Facture f = new Facture();
               // f.setId(fs.getInt("id"));
                f.setPrixTotal((fs.getFloat(1)));
                f.setDate(fs.getDate(2));
                f.setPrixFinal(fs.getFloat(3));
                Utilisateur u= new Utilisateur(fs.getString(4),fs.getString(5),fs.getString(6),fs.getInt(7),fs.getString(8));
                f.setClient(u);
                Promotion p= new Promotion(fs.getFloat(9)); 
                f.setPromotion(p);
                factures.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return factures;

    }

      public float CalculerPrixF(Facture f)
    {
        String req = "SELECT `prixTotal`  FROM `facture` where id="+f.getId();
        float prix=0,taux=-5;
        
        try {
            pst = conn.prepareStatement(req);
            ResultSet fs = pst.executeQuery();
            
            //fs.next();
              
               prix=fs.getFloat(1);
               //taux=fs.getFloat(2);
              
              
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prix*(1-taux);
        
    }
  /*  public List<Utilisateur> recherche(String valeur,String colonne,String tri){
        List<Facture> factures = new ArrayList<>();

        //String req = "SELECT * FROM utilisateur WHERE CONCAT(id, nom, prenom, email, dateDeNaissance, numTel, ville, login, role) LIKE '%"+input+"%'";
        //String req = "SELECT * FROM utilisateur WHERE "+colonne+" LIKE '%"+valeur+"%' order by "+colonne+" "+tri+" ";
        String req = "SELECT * FROM `facture` WHERE "+colonne+" LIKE ? order by ? ";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1,valeur);
            pst.setString(2,tri);
            ResultSet us = pst.executeQuery();

            while (us.next()) {
                Facture f = new Facture();
                f.setId(us.getInt("id"));
                f.setNom(us.getString(2));
                f.setPrenom(us.getString(3));
                f.setEmail(us.getString(4));
                f.setDateNaissance(us.getDate(5));
                user.setNumTel(us.getInt(6));
                user.setVille(us.getString(7));
                user.setLogin(us.getString(8));
                user.setMdp(us.getString(9));
                user.setRole(Role.valueOf(us.getString(10)));

                utilisateurs.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return utilisateurs;
        
}*/  
        
  
}
