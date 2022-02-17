/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;

import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionPromoFactureReservation.entities.Billet;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

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

        String req = "insert into billet  (dateAller,dateRetour,type,prix,compagnieAerienne) values(?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1, b.getDateAller());
            pst.setDate(2, b.getDateRetour());
            pst.setInt(3, b.getType());
            pst.setDouble(4, b.getPrix());
            pst.setString(5, b.getCompagnieAerienne());

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
            //  pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Billet supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Billet b, int id) {

        String req = "update `billet` SET dateAller=?,dateRetour=?,type=?,prix=?,compagnieAerienne=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.setDate(1, b.getDateAller());
            pst.setDate(2, b.getDateRetour());
            pst.setInt(3, b.getType());
            pst.setDouble(4, b.getPrix());
            pst.setString(5, b.getCompagnieAerienne());

            pst.executeUpdate();
            pst.close();
            System.out.println("Billet ajoutée");
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
                Billet b = new Billet();

                b.setId(rs.getInt("id"));
                b.setDateAller(rs.getDate(2));
                b.setDateRetour(rs.getDate(3));
                b.setType(rs.getInt(4));
                b.setPrix(rs.getFloat(5));
                b.setCompagnieAerienne(rs.getString(6));
                LBillet.add(b);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LBillet;
    }

}
