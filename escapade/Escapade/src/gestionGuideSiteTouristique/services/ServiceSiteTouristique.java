/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionGuideSiteTouristique.services;



import gestionGuideSiteTouristique.entities.SiteTouristique;
import gestionGuideSiteTouristique.entities.Guide;

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

public class ServiceSiteTouristique {
      Connection cnx;
    private PreparedStatement ste ;

    public ServiceSiteTouristique() {
    cnx =DataSource.getInstance().getCnx();        
    }

public void ajouterc(SiteTouristique g) {
       String req ="INSERT INTO sitetourstique (type, libelle, adresse,description, prix ) VALUES (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            
            
            ste.setString(1,g.getType());
            ste.setString(2,g.getLibelle());
            ste.setString(3,g.getAdresse());
            ste.setString(4,g.getDescription());
            ste.setInt(5,g.getPrix());
            ste.executeUpdate();
            System.out.println("Site touristique ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
      }
    
    }

     public List<SiteTouristique> ListClasse() {
        List<SiteTouristique> Mylist = new ArrayList<>();
        try {
            String requete = "select * from sitetourstique";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet e = pst.executeQuery();
            while (e.next()) {
                SiteTouristique pre = new SiteTouristique();
              
            pre.setId(e.getInt("id"));
            pre.setType(e.getString("type"));
            pre.setLibelle(e.getString("libelle"));
            pre.setAdresse(e.getString("adresse"));
            pre.setDescription(e.getString("description"));
            pre.setPrix((e.getInt("prix")));
            
                Mylist.add(pre);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
     
     
      public void supprimerc (SiteTouristique c ) {
    String requete = "DELETE FROM sitetourstique where id =?";
           try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Site touristique Supprimée !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void modifierc (SiteTouristique c) {
    
        try {
            String requete = "update sitetourstique set type=? ,libelle =?,adresse=? ,description =? , prix =?  where ? = id";
            PreparedStatement pre = cnx.prepareStatement(requete);
            
            pre.setString(1,c.getType());
            pre.setString(2, c.getLibelle());
            pre.setString(3, c.getAdresse());
            pre.setString(4, c.getDescription());
            pre.setInt(5, c.getPrix());
            pre.setInt(6,c.getId());
            

            pre.executeUpdate();
            System.out.println("Site touristique  Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }












}


