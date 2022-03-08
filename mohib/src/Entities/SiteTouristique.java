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
public class SiteTouristique { 
    
    int id , idGuide , idDestination , prix ; 
    String type , libelle , adresse , description  ; 
    

    public SiteTouristique(int id, String type, String libelle, String adresse, String description, int prix) {
        this.id = id;
        this.type = type;
        this.libelle = libelle;
        this.adresse = adresse;
        this.description = description;
        this.prix = prix;
    }

    public SiteTouristique() {
        
    }

    public SiteTouristique(String text, String text0, String text1, String text2, String text3) {
        
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
}
