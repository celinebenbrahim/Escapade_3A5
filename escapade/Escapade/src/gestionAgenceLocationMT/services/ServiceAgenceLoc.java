/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionAgenceLocationMT.services;

import gestionAgenceLocationMT.entities.AgenceLoc;
import gestionAgenceLocationMT.entities.MoyenDeTrans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import escapade.utils.DataSource;
import gestionAgenceLocationMT.entities.Status;

/**
 *
 * @author KattaX
 */
public class ServiceAgenceLoc {
    private Connection con = DataSource.getInstance().getCnx();
    private Statement ste;
    
    public ServiceAgenceLoc() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    /*  ajout MDT */
    public void ajouterAgence(AgenceLoc ag) throws SQLException {
        String req1 = "INSERT INTO `agencedelocation`(`libelle`, `adresse`, `email`, `numTel`) "
                + "VALUES ('" + ag.getLibelle()+ "', '" + ag.getAdresse()+ "','" + ag.getEmail()+"','" + ag.getNumTel()+"');";
        ste.executeUpdate(req1);
        System.out.println("AG ajouté");
    }
    public int countp() throws SQLException {


         int nb = 0;

        String req1 = "SELECT count(*) FROM `agencedelocation`";
        PreparedStatement preparedStatement = con.prepareStatement(req1, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet result = preparedStatement.executeQuery();

        if (result.first()) {
            System.out.println(result);
            nb = result.getInt(1);
        }

        return nb;
    }
    
    /*  Modif Agence */
    public void modifierAgence(AgenceLoc ag) throws SQLException {
        String req1 = "UPDATE `agencedelocation` "
                + "SET `libelle`=?, adresse=?,email=?, numTel=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setString(1, ag.getLibelle());
        preparedStatement.setString(2, ag.getAdresse());
        preparedStatement.setString(3, ag.getEmail());
        preparedStatement.setInt(4, ag.getNumTel());
           preparedStatement.setInt(5, ag.getId());
        

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("agence modifie");
        } else {
            System.out.println("agence non modifie");
        }
    }
      public void suprimerMDT(AgenceLoc mdt) throws SQLException {
        String req1 = "DELETE FROM `agencedelocation` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, mdt.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("ag supprime");
        } else {
            System.out.println("ag non supprime");
        }
    }
     public ArrayList<AgenceLoc> afficherTout() throws SQLException {
        ArrayList<AgenceLoc> list = new ArrayList<>();
       
            String req ="SELECT * FROM `agencedelocation`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                AgenceLoc ag = new AgenceLoc();
                ag.setId(rs.getInt(1));
                ag.setLibelle(rs.getString("libelle"));
                ag.setAdresse(rs.getString("adresse"));
                ag.setEmail(rs.getString("email"));
                ag.setNumTel(rs.getInt("numTel"));
               
                
                list.add(ag);
            }
            
       
        return list;
    }
        public int countcommentbyid(int id) throws SQLException {


         int nb = 0;

        String req1 = "SELECT count(*) FROM `commentaire` WHERE `agencecomment_id`=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setInt(1, id);

        ResultSet result = preparedStatement.executeQuery();

        if (result.first()) {
            System.out.println(result);
            nb = result.getInt(1);
        }

        return nb;
    }
}
