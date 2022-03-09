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

    public Connection conn;
    
    public PreparedStatement pst;

    public HotelService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) throws SQLException{

        String req = "delete from hotel where idHotel=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void ajouter(Hotel h) throws SQLException{

        String req = "insert into hotel  ( matriculeFiscale,nom,nbrEtoile,description,"
                + "nbChambreTotal,idDestination,maxChambre,imgHotel)"
                + " values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
            pst.setInt(3, h.getNbrEtoile());
            pst.setString(4, h.getDescription());
            pst.setInt(5, h.getNbChambreTotal());
            pst.setInt(6, h.getDestination().getId());
            pst.setInt(7, h.getMaxChambre());
            pst.setString(8, h.getImg());
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                h.setIdHotel(generatedKeys.getInt(1));
            }
            System.out.println("Hotel ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Hotel h) throws SQLException{
        String req = "delete from hotel where idHotel = " + h.getIdHotel();
        try {
            pst = conn.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Hotel h, int id) throws SQLException{

        String req = "update `hotel` SET matriculeFiscale=? ,nom=?,"
                + "nbrEtoile=?,description=?,nbChambreTotal=?,idDestination=? ,maxChambre=?,imgHotel=? where idHotel=" + id;
        try {
        pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
             pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
            pst.setInt(3, h.getNbrEtoile());
            pst.setString(4, h.getDescription());
            pst.setInt(5, h.getNbChambreTotal());
            pst.setInt(6, h.getDestination().getId());
            pst.setInt(7, h.getMaxChambre());
            pst.setString(8, h.getImg());
            pst.executeUpdate();
            
             ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                h.setIdHotel(generatedKeys.getInt(1));
            }
            pst.close();
            System.out.println("Hotel modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Hotel> afficher()throws SQLException {
                 
          List<Hotel> hotels = new ArrayList<>();
        String req = " select idHotel,matriculeFiscale,`nom`, `nbrEtoile`, `description`, "
                + "`nbChambreTotal`, `maxChambre`, `imgHotel` ,`pays`, `ville` FROM `hotel` JOIN `destination`"
                + " ON hotel.idDestination = destination.id ";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Hotel h = new Hotel();
                 h.setIdHotel(rs.getInt("idHotel"));
              h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
            
                h.setNbrEtoile(rs.getInt(4));
                h.setDescription(rs.getString(5));
                h.setNbChambreTotal(rs.getInt(6));
                h.setMaxChambre(rs.getInt(7));
                h.setImg(rs.getString(8));
                Destination d= new Destination(rs.getString(9),rs.getString(10));
                h.setDestination(d);
               
                hotels.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return hotels;

    }
    
    public int countHotel(int nb)
    {
       int i=0;
         String req = " select count(*) FROM `hotel` where nbrEtoile="+nb;    
          try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
              
                i=rs.getInt(1);
               
             
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return i;
         
    }
    
    
    public List<Hotel> afficherHotel(int id)throws SQLException {
                 
          List<Hotel> hotels = new ArrayList<>();
        String req = " select idHotel,matriculeFiscale,`nom`, `nbrEtoile`, `description`, "
                + "`nbChambreTotal`, `maxChambre`, `imgHotel`  FROM `hotel` where idDestination="+id; 
              
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Hotel h = new Hotel();
                h.setIdHotel(rs.getInt("idHotel"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setNbrEtoile(rs.getInt(4));
                h.setDescription(rs.getString(5));
                h.setNbChambreTotal(rs.getInt(6));
                h.setMaxChambre(rs.getInt(7));
                h.setImg(rs.getString(8));
                Destination d= new Destination(rs.getString(9),rs.getString(10));
                h.setDestination(d);
                hotels.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return hotels;

    }
    @Override
     public List<Hotel> tri()throws SQLException {

        List<Hotel> hotels = new ArrayList<>();
        String req = "SELECT  `matriculeFiscale`, `nom`, `nbrEtoile`, `description`, "
                + "`nbChambreTotal`, `maxChambre`, `imgHotel` ,`pays`, `ville` FROM `hotel`  INNER JOIN `destination`"
                + " ON hotel.idDestination = destination.id order by nbrEtoile asc ";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Hotel h = new Hotel();
         
                h.setMatriculeFiscale(rs.getString(1));
                h.setNom(rs.getString(2));
                h.setNbrEtoile(rs.getInt(3));
                h.setDescription(rs.getString(4));
                h.setNbChambreTotal(rs.getInt(5));
                h.setMaxChambre(rs.getInt(6));
                h.setImg(rs.getString(7));
                Destination d= new Destination(rs.getString(8),rs.getString(9));
                h.setDestination(d);
                hotels.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return hotels;


    }

    @Override
    public void rechercher(String nom)throws SQLException {
               List<Hotel> result = afficher().stream().
                filter(line -> nom.equals(line.getNom())).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);
    }
    
     public List<Hotel> findHotel(String matriculeFiscale, String nom ,int nbrEtoile ) throws SQLException {

        String req = "SELECT * FROM Hotel WHERE matriculeFiscale= ? or nom=? or nbrEtoile=? ";
       
        List<Hotel> hotel = new ArrayList<>();

       pst = conn.prepareStatement(req);
        pst.setString(1, matriculeFiscale);
        pst.setString(2, nom);
       pst.setInt(3, nbrEtoile);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            pst = conn.prepareStatement(req);
           pst.setString(1, matriculeFiscale);
        pst.setString(2, nom);
       pst.setInt(3, nbrEtoile);
       Hotel h = new Hotel();
             h.setIdHotel(rs.getInt("idHotel"));
               h.setMatriculeFiscale(rs.getString(1));
                h.setNom(rs.getString(2));
                h.setNbrEtoile(rs.getInt(3));
                hotel.add(h);

        }
        return hotel;
    }
}
