/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;
import gestionHotelDestination.entities.IService;
import escapade.utils.DataSource;
import gestionPromoFactureReservation.entities.Hotel;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Meryem
 */
public class HotelService implements IService<Hotel>{
    
      private Connection conn;
    private PreparedStatement pst ;
    
    public HotelService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) {

      String req = "delete from hotel where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    @Override
    public void ajouter(Hotel h) {

       
    String req = "insert into hotel  ( matriculeFiscale,nom,adresse,nbrEtoile,description,nbChambreTotal,idDestination)"
            + " values(?,?,?,?,?,?,6)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
             pst.setString(3, h.getAdresse());
            pst.setInt(4, h.getNbrEtoile());
            pst.setString(5, h.getDescription());
             pst.setInt(6, h.getNbChambreTotal());
        
            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                h.setId(generatedKeys.getInt(1));
            }
            System.out.println("Hotel ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Hotel h) {
 String req = "delete from hotel where id = " + h.getId();
        try {
            pst = conn.prepareStatement(req);
            //  pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Hotel h,int id) {

 String req = "update `hotel` SET matriculeFiscale=? ,nom=?,adresse=?,"
         + "nbrEtoile=?,description=?,nbChambreTotal=?,idDestination=6 where id="+id;
        try {
            pst = conn.prepareStatement(req);
          pst.setString(1, h.getMatriculeFiscale());
            pst.setString(2, h.getNom());
             pst.setString(3, h.getAdresse());
            pst.setInt(4, h.getNbrEtoile());
            pst.setString(5, h.getDescription());
             pst.setInt(6, h.getNbChambreTotal());
            pst.executeUpdate();
            pst.close();
            System.out.println("Hotel ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Hotel> afficher() {

  List<Hotel> LHotel = new ArrayList<>();
        String req = " select * from `hotel`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setMatriculeFiscale(rs.getString(2));
                h.setNom(rs.getString(3));
                h.setAdresse(rs.getString(4));
                h.setNbrEtoile(rs.getInt(5));
                h.setDescription(rs.getString(6));
                h.setNbChambreTotal(rs.getInt(7));
                h.setIdDestination(rs.getInt(8));
             
                LHotel.add(h);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LHotel;

    }

    
    
}
