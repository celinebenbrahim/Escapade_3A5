/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;

import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionPromoFactureReservation.entities.Billet;
import gestionPromoFactureReservation.entities.Destination;
import gestionPromoFactureReservation.entities.TypeBillet;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.entities.Utilisateur;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Meryem
 */
public class BilletService implements IService<Billet> {

    private Connection conn;
    private PreparedStatement pst;

    public BilletService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) {
        String req = "delete from billet where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Billet supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouter(Billet b) {

        String req = "insert into billet (dateAller,dateRetour,type,prix,compagnieAerienne,idClient)"
                + " values(?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, new java.sql.Date(b.getDateAller().getTime()));
            pst.setDate(2, new java.sql.Date(b.getDateRetour().getTime()));
            pst.setString(3, b.getType().toString());
            pst.setDouble(4, b.getPrix());
            pst.setString(5, b.getCompagnieAerienne());
            pst.setInt(6, b.getClient().getId());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();

            if (generatedKeys.next()) {
                b.setId(generatedKeys.getInt(1));
            }
            System.out.println("Billet ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Billet b) {

        String req = "delete from billet where id = " + b.getId();
        try {
            pst = conn.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("Billet supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Billet b, int id) {

        String req = "update `billet` SET dateAller=?,dateRetour=?,type=?,prix=?,compagnieAerienne=?,idClient=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, new java.sql.Date(b.getDateAller().getTime()));
            pst.setDate(2, new java.sql.Date(b.getDateRetour().getTime()));
            pst.setString(3, b.getType().toString());
            pst.setDouble(4, b.getPrix());
            pst.setString(5, b.getCompagnieAerienne());
            pst.setInt(6, b.getClient().getId());
            pst.executeUpdate();
            pst.close();
            System.out.println("Billet modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Billet> afficher() {

        List<Billet> LBillet = new ArrayList<>();
        String req = " select * from `billet`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

          while (rs.next()) {
                  String req1 = " select * from `utilisateur` where id=" + rs.getInt(7);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
               Utilisateur user= new Utilisateur();
                 if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                user.setId(rss.getInt("id"));
                user.setNom(rss.getString(2));
                user.setPrenom(rss.getString(3));
                user.setEmail(rss.getString(4));
                user.setDateNaissance(rss.getDate(5));
                user.setNumTel(rss.getInt(6));
                user.setVille(rss.getString(7));
                user.setLogin(rss.getString(8));
                user.setMdp(rss.getString(9));
                user.setRole(Role.valueOf(rss.getString(10)));
                }
                Billet b = new Billet();
                b.setId(rs.getInt("id"));
                b.setDateAller(rs.getDate(2));
                b.setDateRetour(rs.getDate(3));
                b.setType(TypeBillet.valueOf(rs.getString(4)));
                b.setPrix(rs.getFloat(5));
                b.setCompagnieAerienne(rs.getString(6));
                b.setClient(user);
                LBillet.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LBillet;
    }

    @Override

    public List<Billet> tri() {

        List<Billet> LBillet = new ArrayList<>();
        String req = " select * from `billet` order by prix DESC";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                  String req1 = " select * from `utilisateur` where id=" + rs.getInt(7);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
               Utilisateur user= new Utilisateur();
               
                 if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                user.setId(rss.getInt("id"));
                user.setNom(rss.getString(2));
                user.setPrenom(rss.getString(3));
                user.setEmail(rss.getString(4));
                user.setDateNaissance(rss.getDate(5));
                user.setNumTel(rss.getInt(6));
                user.setVille(rss.getString(7));
                user.setLogin(rss.getString(8));
                user.setMdp(rss.getString(9));
                user.setRole(Role.valueOf(rss.getString(10)));
                }
                Billet b = new Billet();
                b.setId(rs.getInt("id"));
                b.setDateAller(rs.getDate(2));
                b.setDateRetour(rs.getDate(3));
                b.setType(TypeBillet.valueOf(rs.getString(4)));
                b.setPrix(rs.getFloat(5));
                b.setCompagnieAerienne(rs.getString(6));
                b.setClient(user);
                LBillet.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LBillet;
    }

    @Override
    public void rechercher(String type) {
        List<Billet> result = afficher().stream().
                filter(line -> type.equals(line.getType().toString())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
    }

}
