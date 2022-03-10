/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.services;

import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionHotelDestination.entities.Destination;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 *
 * @author Meryem
 */
public class DestinationService implements IService<Destination> {

    public Connection conn;
    public PreparedStatement pst;

    public DestinationService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) throws SQLException {
        String req = "delete from destination where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Destination supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouter(Destination d) throws SQLException {

        String req = "insert into destination  (pays,ville,img) values(?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, d.getPays());
            pst.setString(2, d.getVille());
            pst.setString(3, d.getImg());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                d.setId(generatedKeys.getInt(1));
            }
            System.out.println("Destination ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Destination d) throws SQLException {

        String req = "delete from destination where id = " + d.getId();
        try {
            pst = conn.prepareStatement(req);
            //  pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Destination supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Destination d, int id) throws SQLException {

        String req = "update `destination` SET pays=?,ville=?,img=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, d.getPays());
            pst.setString(2, d.getVille());
            pst.setString(3, d.getImg());
            pst.executeUpdate();
            pst.close();
            System.out.println("Destination ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Destination> afficher() throws SQLException {

        List<Destination> LDestination = new ArrayList<>();
        String req = " select * from `destination`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Destination d = new Destination();
                d.setId(rs.getInt("id"));
                d.setPays(rs.getString(2));
                d.setVille(rs.getString(3));
                d.setImg(rs.getString(4));
                LDestination.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LDestination;
    }

    public Destination afficherDestination(int id) throws SQLException {

        List<Destination> LDestination = new ArrayList<>();
        Destination d = new Destination();
        String req = " select * from `destination` where id=" + id;
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                d.setId(rs.getInt("id"));
                d.setPays(rs.getString(2));
                d.setVille(rs.getString(3));
                d.setImg(rs.getString(4));
                //  LDestination.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return d;
    }

    @Override
    public List<Destination> tri() throws SQLException {

        List<Destination> LDestination = new ArrayList<>();
        String req = " select * from `destination` order by pays DESC";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Destination d = new Destination();
                d.setId(rs.getInt("id"));
                d.setPays(rs.getString(2));
                d.setVille(rs.getString(3));
                d.setImg(rs.getString(4));
                LDestination.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LDestination;
    }

    @Override
    public void rechercher(String pays) throws SQLException {
        List<Destination> result = afficher().stream().
                filter(line -> pays.equals(line.getPays())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
    }

    public List<Destination> findDestination( String Pays, String Ville) throws SQLException {

        String req = "SELECT * FROM Destination WHERE  pays=? or ville=?  ";

        List<Destination> LDestination = new ArrayList<>();

        pst = conn.prepareStatement(req);

       
        pst.setString(1, Pays);
        pst.setString(2, Ville);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            
            pst = conn.prepareStatement(req);
        
            pst.setString(1, Pays);
            pst.setString(2, Ville);
            Destination d = new Destination();
            d.setId(rs.getInt("id"));
            d.setPays(rs.getString(2));
            d.setVille(rs.getString(3));
            d.setImg(rs.getString(4));
            LDestination.add(d);

        }
        return LDestination;
    }

}
