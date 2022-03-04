/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.services;

import gestionHotelDestination.entities.Hotel;
import escapade.utils.DataSource;
import gestionHotelDestination.entities.IService;
import gestionHotelDestination.entities.Billet;
import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Status;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.VueSurMer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            pst.setDouble(5, ch.getPrixNuit());
            pst.setString(6, ch.getStatus().toString());
            pst.setInt(7, ch.getHotel().getIdHotel());
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

            pst.setInt(1, ch.getNum());
            pst.setString(2, ch.getType().toString());
            pst.setString(3, ch.getVueSurMer().toString());
            pst.setString(4, ch.getDescription());
            pst.setDouble(5, ch.getPrixNuit());
            pst.setString(6, ch.getStatus().toString());
            pst.setInt(7, ch.getHotel().getIdHotel());
            pst.executeUpdate();
            pst.close();
            System.out.println("Chambre modifiée");
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
                String req1 = " select matriculeFiscale,nom,adresse,nbrEtoile"
                        + " from `hotel` where id=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();
                if (rss.next()) {
                    InputStream stream = rss.getBinaryStream(1);
                 
                    h.setMatriculeFiscale(rss.getString(1));
                    h.setNom(rss.getString(2));
                    h.setNbrEtoile(rss.getInt(4));
                }
                Chambre ch = new Chambre();
                ch.setId(rs.getInt("id"));
                ch.setNum(rs.getInt(2));
                ch.setType(Type.valueOf(rs.getString(3)));
                ch.setVueSurMer(VueSurMer.valueOf(rs.getString(4)));
                ch.setDescription(rs.getString(5));
                ch.setPrixNuit(rs.getDouble(6));
                ch.setStatus(Status.valueOf(rs.getString(7)));
                ch.setHotel(h);
                LChambre.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }

    @Override
    public List<Chambre> tri() {
        List<Chambre> LChambre = new ArrayList<>();
        String req = "select * from `chambre` order by prixNuit ASC";
        try {

            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select matriculeFiscale,nom,adresse,nbrEtoile"
                        + " from `hotel` where id=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();

                if (rss.next()) {
                    InputStream stream = rss.getBinaryStream(1);
                   
                    h.setMatriculeFiscale(rss.getString(1));
                    h.setNom(rss.getString(2));
                    h.setNbrEtoile(rss.getInt(4));
                }
                Chambre ch = new Chambre();
                ch.setId(rs.getInt("id"));
                ch.setNum(rs.getInt(2));
                ch.setType(Type.valueOf(rs.getString(3)));
                ch.setVueSurMer(VueSurMer.valueOf(rs.getString(4)));
                ch.setDescription(rs.getString(5));
                ch.setPrixNuit(rs.getDouble(6));
                ch.setStatus(Status.valueOf(rs.getString(7)));
                ch.setHotel(h);
                LChambre.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }

    @Override
    public void rechercher(String status) {
        List<Chambre> result = afficher().stream().
                filter(line -> status.equals(line.getStatus().toString())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
    }

    public void updateStatus(Chambre ch) {
        
        

        String req = "select status from `chambre` where id=" + ch.getId();       
        String req1 = "update `chambre` SET status=? where id=" + ch.getId();
      
        try {
     
            pst = conn.prepareStatement(req);
        
            PreparedStatement pst1 = conn.prepareStatement(req1);
            
            ResultSet rs = pst.executeQuery();
        
            rs.next();
         
            if (Status.valueOf(rs.getString(1)).equals(Status.disponible)) {
                  
                pst1.setString(1, Status.indisponible.toString());
            
            } 
            else
            {
                
                pst1.setString(1, Status.disponible.toString());

            }
            pst1.executeUpdate();
            pst1.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
