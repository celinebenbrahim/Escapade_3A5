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
import gestionPromoFactureReservation.entities.Iservice;
import gestionPromoFactureReservation.entities.Promotion;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class PromotionService implements Iservice<Promotion> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public PromotionService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Promotion p) {
        String req = "insert into `promotion`  (`taux`,`dateDebut`,`dateFin`) values(?,?,?)";
        try {

            pst = conn.prepareStatement(req, ste.RETURN_GENERATED_KEYS);
            pst.setFloat(1, p.getTaux());
            pst.setDate(2, p.getDateDebut());
            pst.setDate(3, p.getDateFin());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {

                p.setId(generatedKeys.getInt(1));
            }

            System.out.println("Promotion ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Promotion p) {
        String req = "delete from promotion where id = " + p.getId();

        try {

            pst = conn.prepareStatement(req);
            //  pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("promotion supprimée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion p,int id) {
        String req = "update `promotion` SET taux=?,dateDebut=?,dateFin=? where id="+id ;
        try {
            pst = conn.prepareStatement(req);
            pst.setFloat(1, p.getTaux());
            pst.setDate(2, p.getDateDebut());
            pst.setDate(3, p.getDateFin());
            pst.executeUpdate();
            pst.close();
            System.out.println("personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Promotion> afficher() {
        List<Promotion> Promotions = new ArrayList<>();
        String req = " select * from `promotion`";

        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));

                p.setTaux(rs.getFloat(2));
                p.setDateDebut(rs.getDate(3));
                p.setDateFin(rs.getDate(3));
                Promotions.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Promotions;
    }

    @Override
    public void supprimerId(int id) {
        String req = "delete from promotion where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("promotion supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
