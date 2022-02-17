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
public class Destination {
    private int id;
    private String pays;
    private String ville;

    public Destination() {
    }

    public Destination(String pays, String ville) {
        this.pays = pays;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
       
        return id+ville.hashCode()+pays.hashCode();
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
        final Destination other = (Destination) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Destination{" + "Pays=" + pays + ", Ville=" + ville + '}';
    }
    
    
    
    
}
