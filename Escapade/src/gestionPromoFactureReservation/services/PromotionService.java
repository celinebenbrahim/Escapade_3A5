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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.sql.SQLException;

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
    public void ajouter(Promotion p) throws SQLException {
        String req = "insert into `promotion`  (`taux`,`dateDebut`,`dateFin`) values(?,?,?)";
        try {

            pst = conn.prepareStatement(req, ste.RETURN_GENERATED_KEYS);
            pst.setFloat(1, p.getTaux());
            pst.setDate(2, new java.sql.Date(p.getDateDebut().getTime()));
            pst.setDate(3, new java.sql.Date(p.getDateFin().getTime()));
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
    public void supprimer(Promotion p) throws SQLException {
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
    public void modifier(Promotion p,int id)throws SQLException {
        String req = "update `promotion` SET taux=?,dateDebut=?,dateFin=? where id="+id ;
        try {
            pst = conn.prepareStatement(req);
            pst.setFloat(1, p.getTaux());
            pst.setDate(2, new java.sql.Date(p.getDateDebut().getTime()));
            pst.setDate(3, new java.sql.Date(p.getDateFin().getTime()));
            pst.executeUpdate();
            pst.close();
            System.out.println("personne ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Promotion> afficher() throws SQLException {
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
    public void supprimerId(int id) throws SQLException{
        String req = "delete from promotion where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("promotion supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
public List<Promotion> Tri() {
        List<Promotion> Promotions = new ArrayList<>();
        String req = " select * from `promotion`order by taux ASC";

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

    public void rechercher(float taux) throws SQLException{
List<Promotion> result = afficher().stream().
        filter(line -> taux==line.getTaux()).collect(Collectors.toList());
                    System.out.println("----------");
                    result.forEach(System.out::println);
    }

//taatik list kemla 
 /*public boolean chercher(int id) throws SQLException {
        String req="select * from promotion";
        List<Integer> list = new ArrayList<>();
        
        try {
            ste=conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Promotion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        list.forEach(System.out::println);
        return list.contains(id);
    }*/
    
     public Promotion FindById(int id) throws SQLException {
        String req = "select * from promotion where id = ?";
        Promotion promotion = null;
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                promotion = new Promotion(rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getDate(4));
                System.out.println(promotion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promotion;
    }

          public Promotion FindByTaux(float taux)  {
        String req = "select * from promotion where taux = ?";
        Promotion promotion = null;
        try {
            pst = conn.prepareStatement(req);
            pst.setFloat(1, taux);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                promotion = new Promotion(rs.getInt(1), rs.getFloat(2), rs.getDate(3), rs.getDate(4));
                System.out.println(promotion);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return promotion;
    }
          
      
       
      
       
    
  }