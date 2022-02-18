/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionAgenceLocationMT.services;

import escapade.utils.DataSource;
import gestionAgenceLocationMT.entities.AgenceDeLocation;
import gestionUserReclamation.entities.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bechir ben youssef
 */
public class AgenceDeLocationService implements IService<AgenceDeLocation>{
  
    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public AgenceDeLocationService() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void ajouter(AgenceDeLocation entity) {
        String req = "INSERT INTO `agencedelocation` (`libelle`,`adresse`,`email`,`numTel`) VALUES (?,?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, entity.getLibelle());
            pst.setString(2, entity.getAdresse());
            pst.setString(3, entity.getEmail());
            pst.setInt(4, entity.getNumTel());
            pst.executeUpdate();
            System.out.println("agence de location ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(AgenceDeLocation entity) {
        
        try {
            PreparedStatement pre = conn.prepareStatement("Delete from agencedelocation where id=? ;");
            pre.setInt(1, entity.getId());
            if (pre.executeUpdate() != 0) {
                System.out.println("user Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    @Override
    public void modifier(AgenceDeLocation entity) {
         String req;

        req = "UPDATE `agencedelocation` SET `libelle`=?,`adresse`=?,`email`=?,`numTel`=? WHERE id =?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, entity.getLibelle());
            ps.setString(2, entity.getAdresse());
            ps.setString(3, entity.getEmail());
            ps.setInt(4, entity.getNumTel());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
            System.out.println("agence de location modifié");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<AgenceDeLocation> afficher() {
        List<AgenceDeLocation> agenceDeLocations = new ArrayList<>();

        String req = "SELECT * from `agencedelocation`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet al = pst.executeQuery();

            while (al.next()) {
                AgenceDeLocation agenceDeLocation = new AgenceDeLocation();
                agenceDeLocation.setId(al.getInt("id"));
                agenceDeLocation.setLibelle(al.getString("libelle"));
                agenceDeLocation.setEmail(al.getString("email"));
                agenceDeLocation.setAdresse(al.getString("adresse"));
                agenceDeLocation.setNumTel(al.getInt("numtel"));
                agenceDeLocations.add(agenceDeLocation);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return agenceDeLocations;
    }
}
