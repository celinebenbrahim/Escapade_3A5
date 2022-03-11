/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionGuideSiteTouristique.services;

import Entities.Guide;
import escapade.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 */
public class ServiceGuide {
      Connection cnx;
    private PreparedStatement ste ;

    public ServiceGuide() {
    cnx =DataSource.getInstance().getCnx();        
    }
    
    public void ajouterc(Guide g) {
       String req ="INSERT INTO guide (nom, pernom, nationnalite,email, langue ) VALUES (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            
            
            ste.setString(1,g.getNom());
            ste.setString(2,g.getPernom());
            ste.setString(3,g.getNationnalite());
            ste.setString(4,g.getEmail());
            ste.setString(5,g.getLangue());
            ste.executeUpdate();
            System.out.println("Guide ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
      }
    }
    
     public List<Guide> ListClasse() {
        List<Guide> Mylist = new ArrayList<>();
        try {
            String requete = "select * from guide";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                Guide pre = new Guide();
              
            pre.setId(e.getInt("id"));
            pre.setNom(e.getString("nom"));
            pre.setPernom(e.getString("pernom"));
            pre.setNationnalite(e.getString("nationnalite"));
            pre.setEmail(e.getString("email"));
            pre.setLangue(e.getString("langue"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
      public void supprimerc (Guide c ) {
    String requete = "DELETE FROM guide where id =?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Guide Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (Guide c) {
    
        try {
            String requete = "update guide set nom=? ,pernom =?,nationnalite=? ,email =? , langue =?  where ? = id";
            PreparedStatement pre = cnx.prepareStatement(requete);
            
            pre.setString(1,c.getNom());
            pre.setString(2, c.getPernom());
            pre.setString(3, c.getNationnalite());
            pre.setString(4, c.getEmail());
            pre.setString(5, c.getLangue());
            pre.setInt(6,c.getId());
            

            pre.executeUpdate();
            System.out.println("Guide Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public List<Guide> ListClasse1( ) {
        List<Guide> Mylist = new ArrayList<>();
        try {
            String requete = "select * from guide order by nom ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
             ResultSet e = pst.executeQuery();
            while (e.next()) {
                Guide pre = new Guide();
              
            pre.setId(e.getInt("id"));
            pre.setNom(e.getString("nom"));
            pre.setPernom(e.getString("pernom"));
            pre.setNationnalite(e.getString("nationnalite"));
            pre.setEmail(e.getString("email"));
            pre.setLangue(e.getString("langue"));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    
}
