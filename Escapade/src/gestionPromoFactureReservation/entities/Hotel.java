/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;
import java.util.*;

/**
 *
 * @author Meryem
 */
public class Hotel {
    private int id;
    private String matriculeFiscale;
    private String nom;
    private String adresse;
    private int nbrEtoile;
    private String description;
    private int nbChambreTotal;
    private int idDestination;
    private enum type {}; 

    public Hotel() {
    }

    public Hotel(String matriculeFiscale, String nom, String adresse, int nbrEtoile, String description, int nbChambreTotal, int idDestination) {
        this.matriculeFiscale = matriculeFiscale;
        this.nom = nom;
        this.adresse = adresse;
        this.nbrEtoile = nbrEtoile;
        this.description = description;
        this.nbChambreTotal = nbChambreTotal;
        this.idDestination = idDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbrEtoile() {
        return nbrEtoile;
    }

    public void setNbrEtoile(int nbrEtoile) {
        this.nbrEtoile = nbrEtoile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbChambreTotal() {
        return nbChambreTotal;
    }

    public void setNbChambreTotal(int nbChambreTotal) {
        this.nbChambreTotal = nbChambreTotal;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    @Override
    public String toString() {
        return "Hotel{" + "matriculeFiscale=" + matriculeFiscale + ", nom=" + nom + ", adresse=" + adresse + ", nbrEtoile=" + nbrEtoile + ", description=" + description + ", nbChambreTotal=" + nbChambreTotal +  '}';
    }

    @Override
    public int hashCode() {
      
        return id+nbrEtoile+matriculeFiscale.hashCode();
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
        final Hotel other = (Hotel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbrEtoile != other.nbrEtoile) {
            return false;
        }
        if (this.nbChambreTotal != other.nbChambreTotal) {
            return false;
        }
       
        if (!Objects.equals(this.matriculeFiscale, other.matriculeFiscale)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    
    
}
