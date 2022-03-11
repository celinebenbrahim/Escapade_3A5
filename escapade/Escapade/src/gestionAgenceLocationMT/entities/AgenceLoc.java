/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionAgenceLocationMT.entities;

/**
 *
 * @author KattaX
 */
public class AgenceLoc {
    int id;
    String libelle;
    String adresse;
    String email;
    int numTel;

    public AgenceLoc() {
       
    }

    public AgenceLoc(int id, String libelle, String adresse, String email, int numTel) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "AgenceLoc{" + "libelle=" + libelle + ", adresse=" + adresse + ", email=" + email + ", numTel=" + numTel + '}';
    }
    
}
