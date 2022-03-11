/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionUserReclamation.services;

import escapade.utils.DataSource;
import gestionUserReclamation.entities.Blocked;
import static gestionUserReclamation.entities.Blocked.oui;
import gestionUserReclamation.entities.IService;
import gestionUserReclamation.entities.Role;
import gestionUserReclamation.entities.Utilisateur;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author mahdi
 */
public class UtilisateurService implements IService<Utilisateur> {

    public Connection conn;
    public PreparedStatement pst;
    public Statement ste;
    private int randomCode;

    public int getRandomCode() {
        return randomCode;
    }

    public UtilisateurService() {
        conn = DataSource.getInstance().getCnx();

    }

    @Override
    public boolean ajouter(Utilisateur user) {
        if (emailExiste(user.getEmail())) {
            System.out.println("existe");

        } else {
            String req = "INSERT INTO `utilisateur` (`nom`,`prenom`,`email`,`dateDeNaissance`,`numTel`,`ville`,`mdp`,`image`,`blocked`,`role`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            
            try {
                pst = conn.prepareStatement(req);
                pst.setString(1, user.getNom());
                pst.setString(2, user.getPrenom());
                pst.setString(3, user.getEmail());
                pst.setDate(4, new java.sql.Date(user.getDateNaissance().getTime()));
                pst.setInt(5, user.getNumTel());
                pst.setString(6, user.getVille());
                pst.setString(7, Crypt.hash(user.getMdp()));
                System.out.println("aaaa1");
                pst.setString(8, user.getImage().toString());
                pst.setString(9, "non");
                pst.setString(10, "client");
                System.out.println("aaaa2");
                pst.executeUpdate();
                System.out.println("utilisateur ajoutée");
                return true;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;

    }

    @Override
    public boolean supprimer(Utilisateur user) {
        try {
            PreparedStatement pre = conn.prepareStatement("Delete from utilisateur where id=? ;");
            pre.setInt(1, user.getId());
            if (pre.executeUpdate() != 0) {
                System.out.println("user Deleted");
                return true;

            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return false;
    }

    @Override
    public boolean modifier(Utilisateur user) {
        String req;

        req = "UPDATE `utilisateur` SET `nom`=?,`prenom`=?,`email`=?,`dateDeNaissance`=?,`numTel`=?,`ville`=?  WHERE id =?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, user.getNom());
            System.out.println("kk");
            ps.setString(2, user.getPrenom());
            ps.setString(3, user.getEmail());
            ps.setDate(4, new java.sql.Date(user.getDateNaissance().getTime()));
            ps.setInt(5, user.getNumTel());
            ps.setString(6, user.getVille());
            //ps.setString(7, user.getImage());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
            System.out.println("utilisateur modifié");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        String req = "SELECT * from `Utilisateur` ";

        try {
            pst = conn.prepareStatement(req);
            ResultSet us = pst.executeQuery();

            while (us.next()) {
                Utilisateur user = new Utilisateur();
                user.setNom(us.getString(2));
                user.setPrenom(us.getString(3));
                user.setEmail(us.getString(4));
                user.setDateNaissance(us.getDate(5));
                user.setNumTel(us.getInt(6));
                user.setVille(us.getString(7));
                user.setMdp(us.getString(8));
                user.setImage(us.getString(9));
                user.setBlocked(Blocked.valueOf(us.getString(10)));
                user.setRole(Role.valueOf(us.getString(11)));

                utilisateurs.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return utilisateurs;
    }
//
//    public List<Utilisateur> recherche(String valeur, String colonne, String tri) {
//        List<Utilisateur> utilisateurs = new ArrayList<>();
//
//        //String req = "SELECT * FROM `utilisateur` WHERE CONCAT(`id`, `nom`, `prenom`, `email`, `dateDeNaissance`, `numTel`, `ville`, `login`, `role`) LIKE '%"+input+"%'";
//        //String req = "SELECT * FROM `utilisateur` WHERE "+colonne+" LIKE '%"+valeur+"%' order by "+colonne+" "+tri+" ";
//        String req = "SELECT * FROM `utilisateur` WHERE " + colonne + " LIKE ? order by ? ";
//
//        try {
//            pst = conn.prepareStatement(req);
//            pst.setString(1, valeur);
//            pst.setString(2, tri);
//            ResultSet us = pst.executeQuery();
//
//            while (us.next()) {
//                Utilisateur user = new Utilisateur();
//                user.setId(us.getInt("id"));
//                user.setNom(us.getString(2));
//                user.setPrenom(us.getString(3));
//                user.setEmail(us.getString(4));
//                user.setDateNaissance(us.getDate(5));
//                user.setNumTel(us.getInt(6));
//                user.setVille(us.getString(7));
//                user.setMdp(us.getString(8));
//                user.setRole(Role.valueOf(us.getString(10)));
//
//                utilisateurs.add(user);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return utilisateurs;
//
//    }

    public boolean emailExiste(String email) {

        String req = "SELECT `email` FROM `utilisateur` WHERE email=?";

        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, email);
            ResultSet us = pst.executeQuery();
            if (us.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public boolean authentification(String email, String mdp) {
        String req = "SELECT * FROM `utilisateur` WHERE email=?";
        Utilisateur user = new Utilisateur();
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, email);
            //pst.setString(2, Crypt.hash(mdp));
            ResultSet us = pst.executeQuery();

            if (us.next()&& Crypt.verifyHash(mdp, us.getString(8))) {
                    user.setNom(us.getString(2));
                    user.setPrenom(us.getString(3));
                    user.setEmail(us.getString(4));
                    user.setDateNaissance(us.getDate(5));
                    user.setNumTel(us.getInt(6));
                    user.setVille(us.getString(7));
                    user.setMdp(us.getString(8));
                    user.setImage(us.getString(9));
                    user.setBlocked(Blocked.valueOf(us.getString(10)));
                    user.setRole(Role.valueOf(us.getString(11)));

                    return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public int envoyerCodeVerif(String email) {
        Random rand = new Random();
        randomCode = rand.nextInt(999999);
        String subject = "Reseting Code";
        String message = "Your reset code is " + randomCode + "";

        try {
            JavaMailUtil.sendMail(email, subject, message);
            return randomCode;
        } catch (MessagingException ex) {
            Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }


    public boolean blockUtilisateur(Utilisateur user) {

        String req = "SELECT `blocked` FROM `utilisateur` WHERE email=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, user.getEmail());
            ResultSet us = pst.executeQuery();

            if ((us.next()) && (us.getString(1).equals("non"))) {
                String req2 = "UPDATE `utilisateur` SET `blocked`=? WHERE email=?";

                try {
                    PreparedStatement ps = conn.prepareStatement(req2);
                    ps.setString(1, "oui");
                    ps.setString(2, user.getEmail());
                    ps.executeUpdate();
                    System.out.println("utilisateur bloqué");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }
     public boolean unBlockUtilisateur(Utilisateur user) {

        String req = "SELECT `blocked` FROM `utilisateur` WHERE email=?";
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, user.getEmail());
            ResultSet us = pst.executeQuery();

            if ((us.next()) && (us.getString(1).equals("oui"))) {
                String req2 = "UPDATE `utilisateur` SET `blocked`=? WHERE email=?";

                try {
                    PreparedStatement ps = conn.prepareStatement(req2);
                    ps.setString(1, "non");
                    ps.setString(2, user.getEmail());
                    ps.executeUpdate();
                    System.out.println("utilisateur non bloqué");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }

    public Utilisateur getUserById(int id) {
        String req = "SELECT * from `Utilisateur` where id=?";
        Utilisateur user = new Utilisateur();
        try {
            pst = conn.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet us = pst.executeQuery();

            while (us.next()) {
                user.setId(id);
                user.setNom(us.getString(2));
                user.setPrenom(us.getString(3));
                user.setEmail(us.getString(4));
                user.setDateNaissance(us.getDate(5));
                user.setNumTel(us.getInt(6));
                user.setVille(us.getString(7));
                user.setMdp(us.getString(8));
                user.setImage(us.getString(9));
                user.setBlocked(Blocked.valueOf(us.getString(10)));
                user.setRole(Role.valueOf(us.getString(11)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;

    }
    
     public Utilisateur getUserByEmail(String email) {
        String req = "SELECT * from `Utilisateur` where email=?";
        Utilisateur user = new Utilisateur();
        try {
            pst = conn.prepareStatement(req);
            pst.setString(1, email);
            ResultSet us = pst.executeQuery();

            while (us.next()) {
                user.setId(us.getInt(1));
                user.setNom(us.getString(2));
                user.setPrenom(us.getString(3));
                user.setEmail(us.getString(4));
                user.setDateNaissance(us.getDate(5));
                user.setNumTel(us.getInt(6));
                user.setVille(us.getString(7));
                user.setMdp(us.getString(8));
                user.setImage(us.getString(9));
                user.setBlocked(Blocked.valueOf(us.getString(10)));
                user.setRole(Role.valueOf(us.getString(11)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;

    }
     
     public boolean modifierMotDePasse(Utilisateur user){
          String req;

        req = "UPDATE `utilisateur` SET `mdp`=? WHERE id =?";

        try {
            PreparedStatement ps = conn.prepareStatement(req);
            System.out.println(user.getMdp());
            ps.setString(1, Crypt.hash(user.getMdp()));
            ps.setInt(2, user.getId());
            ps.executeUpdate();
            System.out.println("mdp modifié");
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
         
         
     }

}
