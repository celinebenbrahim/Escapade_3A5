/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;
import gestionUserReclamation.entities.Utilisateur;

import java.util.*;

/**
 *
 * @author Meryem
 */
public class Billet {

    private int id;
    private Date dateAller;
    private Date dateRetour;
    private TypeBillet type; 
    private double prix;
    private String compagnieAerienne;
    private Utilisateur client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAller() {
        return dateAller;
    }

    public void setDateAller(Date dateAller) {
        this.dateAller = dateAller;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public TypeBillet getType() {
        return type;
    }

    public void setType(TypeBillet type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCompagnieAerienne() {
        return compagnieAerienne;
    }

    public void setCompagnieAerienne(String compagnieAerienne) {
        this.compagnieAerienne = compagnieAerienne;
    }

  

    public Billet() {
    }

    public Billet(Date dateAller, Date dateRetour, TypeBillet type, double prix, 
            String compagnieAerienne, Utilisateur client) {
        this.dateAller = dateAller;
        this.dateRetour = dateRetour;
        this.type = type;
        this.prix = prix;
        this.compagnieAerienne = compagnieAerienne;
        this.client = client;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

   

    @Override
    public String toString() {
        return "Billet{" + "dateAller=" + dateAller + ", dateRetour=" + dateRetour + ", type=" + type + ", prix=" + prix + ", compagnieAerienne=" + compagnieAerienne + '}';
    }

    @Override
    public int hashCode() {
      
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Billet other = (Billet) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.compagnieAerienne, other.compagnieAerienne)) {
            return false;
        }
        if (!Objects.equals(this.dateAller, other.dateAller)) {
            return false;
        }
        if (!Objects.equals(this.dateRetour, other.dateRetour)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }

  

}
