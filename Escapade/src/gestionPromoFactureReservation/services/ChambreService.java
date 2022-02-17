/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;

import escapade.utils.DataSource;
import gestionHotelDestination.entities.IService;
import gestionPromoFactureReservation.entities.Chambre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Meryem
 */
public class ChambreService implements IService<Chambre> {
     private Connection conn;
    private PreparedStatement pst;

    public ChambreService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void supprimerId(int id) {
         String req = "delete from chambre where id=" + id;
        try {
            PreparedStatement pst = conn.prepareStatement(req);
            pst.executeUpdate(req);
            System.out.println("Chambre supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
    }
    

    @Override
    public void ajouter(Chambre ch) {

String req = "insert into chambre  (num,type,vueSurMer,description,prixNuitée,status,idHotel) values(?,?,?,?,?,?,)";


  
        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ch.getNum());
            pst.setString(2, ch.get);
            pst.executeUpdate();
            
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                ch.setId(generatedKeys.getInt(1));
            }
            System.out.println("Destination ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Chambre entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Chambre entity, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Chambre> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
