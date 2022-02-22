/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionUserReclamation.services;

import escapade.utils.DataSource;
import gestionHotelDestination.entities.IService;
import gestionUserReclamation.entities.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mahdi
 */
public class ReclamationService implements IService<Reclamation> {

    private Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public ReclamationService() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public void ajouter(Reclamation r) {
        String req = "INSERT INTO `reclamation` (`description`,`date`,`idClient`) VALUES (?,?,?)";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, r.getDescription());
            //pst.setDate(2, new java.sql.Date(r.getDate().getTime()));
            pst.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pst.setInt(3, r.getIdClient());
            pst.executeUpdate();
            System.out.println("Reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Reclamation r) {
        try {
            PreparedStatement pre = conn.prepareStatement("Delete from reclamation where id=? ;");
            pre.setInt(1, r.getId());
            if (pre.executeUpdate() != 0) {
                System.out.println("reclamation Deleted");

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

  
    public void modifier(Reclamation r) {
        String req;

        req = "UPDATE `reclamation` SET `description`=? WHERE id =?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, r.getDescription());
            ps.setInt(2, r.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> reclamations = new ArrayList<>();

        String req = "SELECT * from `reclamation`";

        try {
            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setDate(rs.getDate(2));
                r.setIdClient(rs.getInt(3));
                r.setDescription(rs.getString(4));
                reclamations.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reclamations;
    }

    @Override
    public void supprimerId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reclamation entity, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
