/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.services;
import gestionPromoFactureReservation.entities.Hotel;
import escapade.utils.DataSource;
import gestionHotelDestination.entities.IService;
import gestionPromoFactureReservation.entities.Chambre;
import gestionPromoFactureReservation.entities.Status;
import gestionPromoFactureReservation.entities.Type;
import gestionPromoFactureReservation.entities.VueSurMer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

        String req = "insert into chambre  (num,type,vueSurMer,description,prixNuit,status,idHotel) values(?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, ch.getNum());
            pst.setString(2, ch.getType().toString());
            pst.setString(3, ch.getVueSurMer().toString());
            pst.setString(4, ch.getDescription());
            pst.setDouble(5, ch.getPrixNuitée());
            pst.setString(6, ch.getStatus().toString());
            pst.setInt(7, ch.getHotel().getId());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                ch.setId(generatedKeys.getInt(1));
            }
            System.out.println("Chambre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Chambre ch) {

        String req = "delete from chambre where id = " + ch.getId();
        try {
            pst = conn.prepareStatement(req);

            pst.executeUpdate();
            System.out.println("Chambre supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Chambre ch, int id) {

        String req = "update `chambre` SET num=?,type=?,vueSurMer=?,description=?,prixNuit=?,status=?,idHotel=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, ch.getId());
            pst.setInt(2, ch.getNum());
            pst.setString(3, ch.getType().toString());
            pst.setString(4, ch.getVueSurMer().toString());
            pst.setString(5, ch.getDescription());
            pst.setDouble(6, ch.getPrixNuitée());
            pst.setString(7, ch.getStatus().toString());
            pst.setInt(8, ch.getHotel().getId());
            pst.executeUpdate();
            pst.close();
            System.out.println("Chambre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Chambre> afficher() {
        List<Chambre> LChambre = new ArrayList<>();
        String req = " select * from `chambre`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Chambre ch = new Chambre();
                ch.setId(rs.getInt("id"));
                ch.setNum(rs.getInt(2));
                ch.setType(Type.valueOf(rs.getString(3)));
                ch.setVueSurMer(VueSurMer.valueOf(rs.getString(4)));
                ch.setDescription(rs.getString(5));
                ch.setPrixNuitée(rs.getDouble(6));
                ch.setStatus(Status.valueOf(rs.getString(7)));
                ch.setHotel((Hotel) rs.getObject(8));
                LChambre.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }

}
