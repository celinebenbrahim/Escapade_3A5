/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionUserReclamation.entities;

import java.util.Date;

/**
 *
 * @author mahdi
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private int numTel;
    private String ville;
    private String login;
    private String mdp;
    private Role role;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String login, String mdp, Role role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.login = login;
        this.mdp = mdp;
        this.role = role;
    }

    public Utilisateur(String nom, String prenom, String email, Date dateNaissance, int numTel, String ville, String login, String mdp, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.ville = ville;
        this.login = login;
        this.mdp = mdp;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{"  + "nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
}
