/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.services;

import gestionHotelDestination.entities.Hotel;
import escapade.utils.DataSource;
import gestionHotelDestination.entities.IService;
import gestionHotelDestination.entities.Chambre;
import gestionHotelDestination.entities.Status;
import gestionHotelDestination.entities.Type;
import gestionHotelDestination.entities.VueSurMer;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
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
    public void supprimerId(int id) throws SQLException {
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
    public void ajouter(Chambre ch) throws SQLException {

        String req = "insert into chambre  (num,type,vueSurMer,description,prixNuit,status,idHotel,img) values(?,?,?,?,?,?,?,?)";

        try {
            pst = conn.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, ch.getNum());
            pst.setString(2, ch.getType().toString());
            pst.setString(3, ch.getVueSurMer().toString());
            pst.setString(4, ch.getDescription());
            pst.setDouble(5, ch.getPrixNuit());
            pst.setString(6, ch.getStatus().toString());
            pst.setInt(7, ch.getHotel().getIdHotel());
             pst.setString(8, ch.getImg());
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
    public void supprimer(Chambre ch) throws SQLException{

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
    public void modifier(Chambre ch, int id) throws SQLException{

        String req = "update `chambre` SET num=?,type=?,vueSurMer=?,description=?,prixNuit=?,status=?,img=? where id=" + id;
        try {
            pst = conn.prepareStatement(req);

            pst.setInt(1, ch.getNum());
            pst.setString(2, ch.getType().toString());
            pst.setString(3, ch.getVueSurMer().toString());
            pst.setString(4, ch.getDescription());
            pst.setDouble(5, ch.getPrixNuit());
            pst.setString(6, ch.getStatus().toString());
            pst.setString(7, ch.getImg());
            pst.executeUpdate();
            pst.close();
            System.out.println("Chambre modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Chambre> afficher() throws SQLException {
        List<Chambre> LChambre = new ArrayList<>();
        String req = " select * from `chambre`";
        try {
            pst = conn.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select matriculeFiscale,nom,nbrEtoile"
                        + " from `hotel` where idHotel=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();
                if (rss.next()) {
                    InputStream stream = rss.getBinaryStream(1);
                 
                    h.setMatriculeFiscale(rss.getString(1));
                    h.setNom(rss.getString(2));
                    h.setNbrEtoile(rss.getInt(3));
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
                ch.setImg(rs.getString(9));
                LChambre.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }

    @Override
    public List<Chambre> tri() throws SQLException{
        List<Chambre> LChambre = new ArrayList<>();
        String req = "select * from `chambre` order by prixNuit ASC";
        try {

            pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select matriculeFiscale,nom,nbrEtoile"
                        + " from `hotel` where idHotel=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();

                if (rss.next()) {
                    InputStream stream = rss.getBinaryStream(1);
                   
                    h.setMatriculeFiscale(rss.getString(1));
                    h.setNom(rss.getString(2));
                    h.setNbrEtoile(rss.getInt(3));
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
                ch.setImg(rs.getString(9));
                LChambre.add(ch);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }

    @Override
    public void rechercher(String status) throws SQLException{
        List<Chambre> result = afficher().stream().
                filter(line -> status.equals(line.getStatus().toString())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);
    }

    public void updateStatus(Chambre ch) throws SQLException {
        
        

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
    
     public Chambre getChambreById(int id) {
        String req = "SELECT * from `Chambre` where id=?";
        //Chambre chambre = new Chambre();
        Chambre ch = new Chambre();
        
          try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String req1 = " select matriculeFiscale,nom,nbrEtoile"
                        + " from `hotel` where idHotel=" + rs.getInt(8);
                PreparedStatement pst1 = conn.prepareStatement(req1);
                ResultSet rss = pst1.executeQuery();
                Hotel h = new Hotel();
                if (rss.next()) {
                    InputStream stream = rss.getBinaryStream(1);
                 
                    h.setMatriculeFiscale(rss.getString(1));
                    h.setNom(rss.getString(2));
                    h.setNbrEtoile(rss.getInt(3));
                }
                
                ch.setId(rs.getInt("id"));
                ch.setNum(rs.getInt(2));
                ch.setType(Type.valueOf(rs.getString(3)));
                ch.setVueSurMer(VueSurMer.valueOf(rs.getString(4)));
                ch.setDescription(rs.getString(5));
                ch.setPrixNuit(rs.getDouble(6));
                ch.setStatus(Status.valueOf(rs.getString(7)));
                ch.setHotel(h);
                ch.setImg(rs.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return ch;
          
          
     }
     
     public List<Chambre> rechercheChambre(Date date,int id,Type type,VueSurMer vue ) throws SQLException {
        List<Chambre> LChambre = new ArrayList<>();
        String req = "SELECT chambre.id, `num`, `Type`, `vueSurMer`, `description`, `prixNuit`, `status`, `idHotel`, `img`, `dateArrivee` , `dateAller` FROM `chambre` LEFT join `reservation_chambre` on chambre.id=reservation_chambre.idChambre WHERE idHotel=? and Type=? and vueSurMer=? and (dateAller <? or dateAller is NUll);";
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            pst.setString(2, String.valueOf(type));
            pst.setString(3, String.valueOf(vue));
            pst.setDate(4, new java.sql.Date(date.getTime()) );
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Chambre ch = new Chambre();
                ch.setId(rs.getInt("id"));
                ch.setNum(rs.getInt(2));
                ch.setType(Type.valueOf(rs.getString(3)));
                ch.setVueSurMer(VueSurMer.valueOf(rs.getString(4)));
                ch.setDescription(rs.getString(5));
                ch.setPrixNuit(rs.getDouble(6));
                ch.setStatus(Status.valueOf(rs.getString(7)));
                Hotel h=new Hotel();
                h.setIdHotel(id);
                ch.setHotel(h);
                ch.setImg(rs.getString(9));
                LChambre.add(ch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return LChambre;

    }
       

}
