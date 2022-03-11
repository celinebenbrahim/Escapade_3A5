/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.services;

import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Destination;
import gestionHotelDestination.entities.Hotel;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Meryem
 */
public class HotelService implements IService<Hotel> {

    private Connection conn;
    private PreparedStatement pst;

    public HotelService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) {

        String req = "delete from hotel where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void ajouter(Hotel h) {

        String req = "insert into hotel  ( matriculeFiscale,nom,adresse,nbrEtoile,description,"
                + "nbChambreTotal,idDestination,maxChambre)"
                + " values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
            pst.setString(3, h.getAdresse());
            pst.setInt(4, h.getNbrEtoile());
            pst.setString(5, h.getDescription());
            pst.setInt(6, h.getNbChambreTotal());
            pst.setInt(7, h.getDestination().getId());
            pst.setInt(8, h.getMaxChambre());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                h.setId(generatedKeys.getInt(1));
            }
            System.out.println("Hotel ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Hotel h) {
        String req = "delete from hotel where id = " + h.getId();
        try {
            pst = conn.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Hotel h, int id) {

        String req = "update `hotel` SET matriculeFiscale=? ,nom=?,adresse=?,"
                + "nbrEtoile=?,description=?,nbChambreTotal=?,idDestination=? ,maxChambre=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
            pst.setString(3, h.getAdresse());
            pst.setInt(4, h.getNbrEtoile());
            pst.setString(5, h.getDescription());
            pst.setInt(6, h.getNbChambreTotal());
            pst.setInt(7, h.getDestination().getId());
            pst.setInt(8, h.getMaxChambre());
            pst.executeUpdate();
            pst.close();
            System.out.println("Hotel ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Hotel> afficher() {

        List<Hotel> LHotel = new ArrayList<>();
        String req = " select * from `hotel`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select pays,ville from `destination` where id=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();
                Destination d = new Destination();
                if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                d.setPays(rss.getString(1));
                d.setVille(rss.getString(2));
                }
                h.setId(rs.getInt("id"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setAdresse(rs.getString(4));
                h.setNbrEtoile(rs.getInt(5));
                h.setDescription(rs.getString(6));
                h.setNbChambreTotal(rs.getInt(7));
                h.setDestination(d);
                h.setMaxChambre(rs.getInt(9));
                LHotel.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LHotel;

    }
    @Override
     public List<Hotel> tri() {

        List<Hotel> LHotel = new ArrayList<>();
        String req = " select * from `hotel` order by nbrEtoile asc";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select pays,ville from `destination` where id=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();
                Destination d = new Destination();
                if (rss.next())
                {
                InputStream stream = rss.getBinaryStream(1);
                d.setPays(rss.getString(1));
                d.setVille(rss.getString(2));
                }
                h.setId(rs.getInt("id"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setAdresse(rs.getString(4));
                h.setNbrEtoile(rs.getInt(5));
                h.setDescription(rs.getString(6));
                h.setNbChambreTotal(rs.getInt(7));
                h.setDestination(d);
                h.setMaxChambre(rs.getInt(9));
                LHotel.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LHotel;

    }

    @Override
    public void rechercher(String nom) {
               List<Hotel> result = afficher().stream().
                filter(line -> nom.equals(line.getNom())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);
    }
}
