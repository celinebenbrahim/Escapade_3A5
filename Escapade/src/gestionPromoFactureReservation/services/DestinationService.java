/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;

import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionPromoFactureReservation.entities.Destination;
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
public class DestinationService implements IService<Destination> {

    private Connection conn;
    private PreparedStatement pst;

    public DestinationService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) {
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
    public void ajouter(Destination d) {

        String req = "insert into destination  (pays,ville) values(?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, d.getPays());
            pst.setString(2, d.getVille());
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
    public void supprimer(Destination d) {

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
    public void modifier(Destination d, int id) {

        String req = "update `destination` SET pays=?,ville=? where id="+id;
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, d.getPays());
            pst.setString(2, d.getVille());
            pst.executeUpdate();
            pst.close();
            System.out.println("Destination ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Destination> afficher() {

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
                LDestination.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LDestination;
    }
    
     @Override
    public List<Destination> tri() {

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
                LDestination.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LDestination;
    }
    
      @Override
    public void rechercher(String pays )
            
    {
        List<Destination> result = afficher().stream().
                filter(line -> pays.equals(line.getPays())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);
    }

}
