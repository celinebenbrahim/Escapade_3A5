/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionUserReclamation.services;
import escapade.utils.DataSource;
import gestionUserReclamation.entities.Blocked;
import gestionUserReclamation.entities.ReservationChambre;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.entities.IService;
import gestionUserReclamation.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AH-INFO
 */
public class ReservationChambreService implements IService<ReservationChambre>{
    
    public Connection conn;
    public PreparedStatement pst;
    public Statement ste;

    public ReservationChambreService() {
          conn = DataSource.getInstance().getCnx();
    }
    
    
     @Override
    public boolean ajouter(ReservationChambre rCh) {
      
            String req = "INSERT INTO `reservation_chambre` (`idClient`,`idChambre`,`dateArrivee`,`dateAller`) VALUES (?,?,?,?)";
            
            try {
                pst = conn.prepareStatement(req);
                pst.setInt(1, rCh.getIdClient());
                pst.setInt(2, rCh.getIdChambre());
                pst.setDate(3, new java.sql.Date(rCh.getDateAller().getTime()));
                pst.setDate(4, new java.sql.Date(rCh.getDateRetour().getTime()));
               
                pst.executeUpdate();
                System.out.println("Reservation chambre ajoutée");
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
       
        return false;

    }

    @Override
    public boolean supprimer(ReservationChambre rCh) {
        try {
            PreparedStatement pre = conn.prepareStatement("Delete from ReservationChambre where id=? ;");
            pre.setInt(1, rCh.getId());
            if (pre.executeUpdate() != 0) {
                System.out.println("Reservation chambre Deleted");
                return true;

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return false;
    }

    @Override
    public boolean modifier(ReservationChambre rCh) {
        String req;

        req = "UPDATE `ReservationChambre` SET `idClient`=?,`idChambre`=?,`dateAller`=?,`dateRetour`=?  WHERE id =?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, rCh.getIdClient());
          
            ps.setInt(2, rCh.getIdChambre());
            ps.setDate(3, new java.sql.Date(rCh.getDateAller().getTime()));
            ps.setDate(4, new java.sql.Date(rCh.getDateRetour().getTime()));
           
            ps.executeUpdate();
            System.out.println("Reservation Chambre modifié");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ReservationChambre> afficher() {
        
        List<ReservationChambre> reservationChambre = new ArrayList<>();

        String req = "SELECT * from `ReservationChambre` ";

        try {
            pst = conn.prepareStatement(req);
            ResultSet us = pst.executeQuery();

            while (us.next()) {
                ReservationChambre rCh = new ReservationChambre();
                rCh.setIdClient(us.getInt(2));
                rCh.setIdChambre(us.getInt(3));
                rCh.setDateAller(us.getDate(4));
                rCh.setDateRetour(us.getDate(5));
              

                reservationChambre.add(rCh);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return reservationChambre;
    }

  
    

}
