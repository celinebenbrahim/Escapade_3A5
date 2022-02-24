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
        String req = "insert into `facture`  (`prixTotal`,`date`,`idClient`,`prixFinal`,`idpromotion`) values(?,?,?,?,?)";
        try {

            pst = conn.prepareStatement(req, ste.RETURN_GENERATED_KEYS);
            pst.setFloat(1, f.getPrixTotal());
            pst.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pst.setInt(3, f.getClient().getId());
            pst.setFloat(4, f.getPrixFinal());
            pst.setInt(5, f.getPromotion().getId());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {

                f.setId(generatedKeys.getInt(1));
            }

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
    public void modifier(Facture f,int id) {
        String req = "update `facture` SET prixTotal=?,date=?,Client=?,prixFinal=?,promotion=? where id="+id;

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
    public List<Facture> afficher() {
        List<Facture> Factures = new ArrayList<>();
        String req = " select * from `facture`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select prenom,nom from `Utilisateur` where id=" + rs.getInt(4);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Facture f = new Facture();
                Utilisateur u=new Utilisateur();
                if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                u.setNom(rss.getString(1));
                u.setPrenom(rss.getString(2));
                }
                String req2 = " select taux from `promotion` where id=" + rs.getInt(6);
                PreparedStatement pst2 = conn.prepareStatement(req2);
                ResultSet rss1 = pst2.executeQuery();
                Promotion p= new Promotion();
                 if (rss1.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                p.setTaux(rss1.getFloat(1));
                }
                f.setId(rs.getInt("id"));
                f.setPrixTotal((rs.getFloat(2)));
                f.setDate(rs.getDate(3));
               // f.setClient((Utilisateur)rs.getObject(4));
               f.setClient(u);
                f.setPrixFinal(rs.getFloat(5));
                f.setPromotion(p);
                Factures.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
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

public List<Facture> Tri() {
        List<Facture> Factures = new ArrayList<>();
        String req = " select * from `facture` order by date desc";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select prenom,nom from `Utilisateur` where id=" + rs.getInt(4);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Facture f = new Facture();
                Utilisateur u=new Utilisateur();
                if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                u.setNom(rss.getString(1));
                u.setPrenom(rss.getString(2));
                }
                String req2 = " select taux from `promotion` where id=" + rs.getInt(6);
                PreparedStatement pst2 = conn.prepareStatement(req2);
                ResultSet rss1 = pst2.executeQuery();
                Promotion p= new Promotion();
                 if (rss1.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                p.setTaux(rss1.getFloat(1));
                }
                f.setId(rs.getInt("id"));
                f.setPrixTotal((rs.getFloat(2)));
                f.setDate(rs.getDate(3));
               // f.setClient((Utilisateur)rs.getObject(4));
               f.setClient(u);
                f.setPrixFinal(rs.getFloat(5));
                f.setPromotion(p);
                Factures.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
    }
   
    public void rechercher(java.util.Date date) {
        List<Facture> result = afficher().stream().filter(line -> date.equals(line.getDate())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);}

   
}


