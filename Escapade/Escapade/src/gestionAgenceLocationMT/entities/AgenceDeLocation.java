/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionAgenceLocationMT.entities;

/**
 *
 * @author bechir ben youssef
 */
public class AgenceDeLocation {

    private int id;
    private String libelle;
    private String adresse;
    private String email;
    private int numTel;

    public AgenceDeLocation() {
    }

    public AgenceDeLocation(int id, String libelle, String adresse, String email, int numTel) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
    }

    public AgenceDeLocation(String libelle, String adresse, String email, int numTel) {
        this.libelle = libelle;
        this.adresse = adresse;
        this.email = email;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "AgenceDeLocation{" + "id=" + id + ", libelle=" + libelle + ", adresse=" + adresse + ", email=" + email + ", numTel=" + numTel + '}';
    }

}
