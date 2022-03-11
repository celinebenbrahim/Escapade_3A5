package services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.Commentaire;
import entities.AgenceLoc;
import entities.User;
//import Utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author Chaima
 */
public class CommentaireService {
        private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;
    
    User user =new User();
    
    
    public CommentaireService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
   
    
    public void AddComment(Commentaire c) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `commentaire` ( `user_id`, `contenu`, `Agencecomment_id`,`date`) VALUES ( ?, ?, ?, ?);");

              ps.setInt(1, c.getUser().getId());
        ps.setString(2, c.getContenu());
        ps.setInt(3, c.getAgence().getId());
       
Date date1 = new Date();
        String date_now = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        ps.setString(4, date_now);
        ps.executeUpdate();
        System.out.println("Commentaire ajoutée !!");

    }
       

     public List GetCommentPerAgence(AgenceLoc a) throws SQLException {
         List<Commentaire> arr = new ArrayList<>();
        String req1 = "SELECT c.user_id,c.contenu,c.date,c.agencecomment_id,u.nom FROM commentaire c, utilisateur u WHERE agencecomment_id=? and c.user_id=u.id";
        PreparedStatement steprep = con.prepareStatement(req1);
        steprep.setInt(1, a.getId());
        ResultSet rs = steprep.executeQuery();
         while (rs.next()) {
            int id = rs.getInt(1);
            int user_id = rs.getInt("c.user_id");
            String contenu = rs.getString("c.contenu");
            String date = rs.getString("c.date");
            int productcomment_id = rs.getInt("c.agencecomment_id");
             String username = rs.getString("u.nom");
        System.out.println("aaa"+username);

            User user=new User();
            AgenceLoc agn=new AgenceLoc();
            Commentaire c = new Commentaire(id,user,agn,contenu,date,username);
            arr.add(c);
         }
return  arr;
     }
     public void UpdateCommentaire(Commentaire c) throws SQLException {
        String req1 = "UPDATE `commentaire` "
                + "SET `contenu` where id=?";
        PreparedStatement ps = con.prepareStatement(req1);
        ps.setString(1, c.getContenu());
        ps.setInt(2, c.getId());
       

        if (ps.executeUpdate() > 0) {
            System.out.println("Commentaire modifier");
        } else {
            System.out.println("Commentaire non modifier");
        }
    }
     
      
       public void DeleteComment(Commentaire c) throws SQLException {
        String req1 = "DELETE FROM `commentaire` where id=? ; ";
        PreparedStatement preparedStatement = con.prepareStatement(req1);
        preparedStatement.setInt(1, c.getId());

        if (preparedStatement.executeUpdate() > 0) {
            System.out.println("Commentaire supprimé");
        } else {
            System.out.println("Commentaire non supprimer");
        }
    }
     
}

