/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.util.Date;

/**
 *
 * @author mahdi
 */
public class Reclamation {

    private int id;
    private Date date;
    private String description;
    private Utilisateur utilisateur;
    
    public Reclamation() {
    }

    public Reclamation(int id, Date date, String description, Utilisateur utilisateur) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.utilisateur = utilisateur;
    }

    public Reclamation(Date date, String description, Utilisateur utilisateur) {
        this.date = date;
        this.description = description;
        this.utilisateur = utilisateur;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "date=" + date + ", description=" + description + ", nom=" + utilisateur.getNom() +", prenom=" + utilisateur.getPrenom() +", email=" + utilisateur.getEmail() + '}';
    }



}
