/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionAgenceLocationMT.services;

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
public class ServiceMDT {
    private Connection con = DataSource.getInstance().getCnx();
    private Statement ste;
    
    public ServiceMDT() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    /*  ajout MDT */
    public void ajouterMDT(MoyenDeTrans mdt) throws SQLException {
        String req1 = "INSERT INTO `moyendetransport`(`libelle`, `type`, `capacite`, `description`, `status`, `tarifJournee`, `idAgence`, `idDestination`) "
                + "VALUES ('" + mdt.getLibelle()+ "', '" + mdt.getType() + "','" + mdt.getCapacite()+ "','"+mdt.getDescription()+"','"+mdt.getStatus()+"','"+mdt.getTarifJ()+"','"+mdt.getIdAgence()+"','"+mdt.getIdDestination()+"');";
        ste.executeUpdate(req1);
        System.out.println("MDT ajouté");
    }
    
    /*  Modif RecAutre */
    public void modifierMDT(MoyenDeTrans mdt) throws SQLException {
        String req1 = "UPDATE `moyendetransport` "
                + "SET `libelle`=?, type=?, capacite=?, description=?, status=?, tarifJournee=?, idAgence=?, idDestination=? where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setString(1, mdt.getLibelle());
        preparedStatement.setString(2, mdt.getType());
        preparedStatement.setInt(3, mdt.getCapacite());
        preparedStatement.setString(4, mdt.getDescription());
        preparedStatement.setString(5, mdt.getStatus().toString());// 0 ou 1
        preparedStatement.setFloat(6, mdt.getTarifJ());
        preparedStatement.setInt(7, mdt.getIdAgence());
        preparedStatement.setInt(8, mdt.getIdDestination());
        preparedStatement.setInt(9, mdt.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("MDT modifie");
        } else {
            System.out.println("MDT non modifie");
        }
    }
      public void suprimerMDT(MoyenDeTrans mdt) throws SQLException {
        String req1 = "DELETE FROM `moyendetransport` where id=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, mdt.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("MDT supprime");
        } else {
            System.out.println("MDT non supprime");
        }
    }
    public List<MoyenDeTrans> afficherTout() {
        List<MoyenDeTrans> list = new ArrayList<>();
        try {
            String req ="SELECT * FROM `moyendetransport`";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                MoyenDeTrans mdt = new MoyenDeTrans();
                mdt.setId(rs.getInt(1));
                mdt.setLibelle(rs.getString("libelle"));
                mdt.setType(rs.getString("type"));
                mdt.setCapacite(rs.getInt("capacite"));
                mdt.setDescription(rs.getString("description"));
                mdt.setStatus(Status.valueOf(rs.getString("status")));
                mdt.setTarifJ(rs.getFloat("tarifJournee"));
                mdt.setIdAgence(rs.getInt("idAgence"));
                mdt.setIdDestination(rs.getInt("idDestination"));
                
                list.add(mdt);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMDT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public List<MoyenDeTrans> afficherparprix() {
        List<MoyenDeTrans> list = new ArrayList<>();
        try {
            String req ="SELECT * FROM `moyendetransport` ORDER BY `tarifJournee` ASC"; 
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                MoyenDeTrans mdt = new MoyenDeTrans();
                mdt.setId(rs.getInt(1));
                mdt.setLibelle(rs.getString("libelle"));
                mdt.setType(rs.getString("type"));
                mdt.setCapacite(rs.getInt("capacite"));
                mdt.setDescription(rs.getString("description"));
                mdt.setStatus(Status.valueOf(rs.getString("status")));
                mdt.setTarifJ(rs.getFloat("tarifJournee"));
                mdt.setIdAgence(rs.getInt("idAgence"));
                mdt.setIdDestination(rs.getInt("idDestination"));
                
                list.add(mdt);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMDT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public int countdispo() throws SQLException {


         int nb = 0;

        String req1 = "SELECT count(*) FROM `moyendetransport` WHERE `status`=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, "disponible");

        ResultSet result = preparedStatement.executeQuery();

        if (result.first()) {
            System.out.println(result);
            nb = result.getInt(1);
        }

        return nb;
    }
      public int countindispo() throws SQLException {


         int nb = 0;

        String req1 = "SELECT count(*) FROM `moyendetransport` WHERE `status`=?";
        PreparedStatement preparedStatement = con.prepareStatement(req1, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        preparedStatement.setString(1, "indisponible");

        ResultSet result = preparedStatement.executeQuery();

        if (result.first()) {
            System.out.println(result);
            nb = result.getInt(1);
        }

        return nb;
    }
}
