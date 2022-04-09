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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class FactureService implements Iservice<Facture> {

    public Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public FactureService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Facture f) throws SQLException {
        String req = "insert into `facture`  (`prixTotal`,date,`idClient`,`prixFinal`,`idpromotion`) values(?,?,?,?,?)";
        try {

            pst = conn.prepareStatement(req, pst.RETURN_GENERATED_KEYS);
            pst.setFloat(1, f.getPrixTotal());
            pst.setDate(2, new java.sql.Date(f.getDate().getTime()));
            pst.setInt(3, f.getClient().getId());
            pst.setFloat(4, f.getPrixFinal());
            if(f.getPromotion()!=null){
                pst.setInt(5, f.getPromotion().getId());
            }
            else{
                pst.setNull(5, 0);
            }
            
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {

                f.setIdF(generatedKeys.getInt(1));
            }

            pst.executeUpdate();

            System.out.println("Facture ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        CalculerPrixF();
        
    }

    @Override
    public void supprimer(Facture f) throws SQLException {
        String req = "delete from facture where idF = " + f.getIdF();

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
    public void modifier(Facture f, int id) throws SQLException {
        String req = "update `facture` SET prixTotal=?,date=?,idClient=?,idpromotion=? where idF=" + id;

        try {
            pst = conn.prepareStatement(req);
            pst.setFloat(1, f.getPrixTotal());
            pst.setDate(2, new java.sql.Date(f.getDate().getTime()));
            pst.setInt(3, f.getClient().getId());

            pst.setInt(4, f.getPromotion().getId());

            pst.executeUpdate();
            pst.close();
            System.out.println("Facture modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        CalculerPrixF();
    }

    @Override
    public void supprimerId(int id) throws SQLException {
        String req = "delete from `facture` where idF=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Facture supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void rechercher(java.util.Date date) throws SQLException {
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
    public List<Facture> afficher() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String req = "SELECT `idF`, `prixTotal`, `date`, `prixFinal`,`nom`, `prenom`, "
                + "`email`, `numTel`, `ville` ,`taux` FROM `facture`  INNER JOIN `utilisateur`"
                + " ON facture.idclient = utilisateur.id LEFT JOIN `promotion` on facture.idPromotion ="
                + " promotion.id";
        try {
            pst = conn.prepareStatement(req);
            ResultSet fs = pst.executeQuery();

            while (fs.next()) {
                Facture f = new Facture();
                f.setIdF(fs.getInt("idF"));
                f.setPrixTotal((fs.getFloat(2)));
                f.setDate(fs.getDate(3));
                f.setPrixFinal(fs.getFloat(4));
                Utilisateur u = new Utilisateur(fs.getString(5), fs.getString(6), fs.getString(7), fs.getInt(8), fs.getString(9));
                f.setClient(u);
                Promotion p = new Promotion(fs.getFloat(10));
                f.setPromotion(p);
                factures.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return factures;

    }

    public void CalculerPrixF() throws SQLException {
        
         String req = "SELECT  `idF` , `prixTotal`,`taux` FROM `facture`  INNER JOIN `promotion`"
                 + " on facture.idPromotion =  promotion.id";

    
        try {
            pst = conn.prepareStatement(req);
          
            ResultSet fs = pst.executeQuery();
            while (fs.next()) {
                String req1="Update facture set prixFinal=? where idF="+fs.getInt("idF");
                PreparedStatement pst1 = conn.prepareStatement(req1);
                double prixTotal = fs.getFloat(2);
                double taux= fs.getFloat(3);
                float prixF = (float) ( prixTotal*(100-taux)/100);
                System.out.println(prixF);
                pst1.setFloat(1, prixF);
                 pst1.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      
      
   /*    public Facture getUserById(int id) {
        String req = "SELECT * from `Facture` where idF=?";
        Facture facture = new Facture();
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet fs = pst.executeQuery();

            while (fs.next()) {
                facture.setIdF(id);
                facture.setPrixTotal(fs.getFloat(2));
                 facture.setDate(fs.getDate(3));
               facture.setPrixFinal(fs.getFloat(4));
                Utilisateur u = new Utilisateur(fs.getString(5), fs.getString(6), fs.getString(7), fs.getInt(8), fs.getString(9));
                facture.setClient(u);
                Promotion p = new Promotion(fs.getFloat(10));
                facture.setPromotion(p);
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return facture;

    }*/
    
    
    public double calculerPrixTotale(double prixNuit,LocalDate checkIn,LocalDate CheckOut){

        return ChronoUnit.DAYS.between(checkIn, CheckOut)*prixNuit;
        
    }
     public double calculerPrixFinale(float prixTotale,Promotion p){
        return prixTotale-(prixTotale*p.getTaux()/100);
        
    }

}
