/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Amine
 */
public class Guide {
    
   int id ,idSiteTouristique ; 
   String nom , pernom , nationnalite , email , langue ;  

    public Guide() {
    }

    public Guide( String nom, String pernom, String nationnalite, String email, String langue) {
        this.nom = nom;
        this.pernom = pernom;
        this.nationnalite = nationnalite;
        this.email = email;
        this.langue = langue;
    }

    public Guide(int id, String nom, String pernom, String nationnalite, String email, String langue) {
        this.id = id;
        this.nom = nom;
        this.pernom = pernom;
        this.nationnalite = nationnalite;
        this.email = email;
        this.langue = langue;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSiteTouristique() {
        return idSiteTouristique;
    }

    public void setIdSiteTouristique(int idSiteTouristique) {
        this.idSiteTouristique = idSiteTouristique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPernom() {
        return pernom;
    }

    public void setPernom(String pernom) {
        this.pernom = pernom;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
   
    
}
