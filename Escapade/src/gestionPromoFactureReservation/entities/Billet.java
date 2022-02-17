/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;

import java.sql.*;
import java.util.Objects;

/**
 *
 * @author Meryem
 */
public class Billet {

    private int id;
    private Date dateAller;
    private Date dateRetour;
    private int type; //faut changer ca dans la bd en enum
    private double prix;
    private String compagnieAerienne;
    private int idClient;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Billet() {
    }

    public Billet(Date dateAller, Date dateRetour, int type, double prix, String compagnieAerienne, int idClient) {
        this.dateAller = dateAller;
        this.dateRetour = dateRetour;
        this.type = type;
        this.prix = prix;
        this.compagnieAerienne = compagnieAerienne;
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Billet{" + "dateAller=" + dateAller + ", dateRetour=" + dateRetour + ", type=" + type + ", prix=" + prix + ", compagnieAerienne=" + compagnieAerienne + '}';
    }

    @Override
    public int hashCode() {
      
        return id+idClient;
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
        if (this.idClient != other.idClient) {
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
        return true;
    }

}
