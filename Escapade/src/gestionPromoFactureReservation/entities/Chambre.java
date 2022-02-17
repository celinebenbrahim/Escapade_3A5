/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;

/**
 *
 * @author Meryem
 */
public class Chambre {
    private int id;
    private  int num;
    private enum type {simplee,doublee,triple,quadruple,suite};
    private enum vueSurMer {yes,no };
    private String description ;
    private double prixNuitée ;
    private enum status {disponible,indisponible};
    private int idHotel;

    public Chambre() {
    }

    public Chambre(int num, String description, double prixNuitée, int idHotel) {
        this.num = num;
        this.description = description;
        this.prixNuitée = prixNuitée;
        this.idHotel = idHotel;
    }

    public Chambre(int id, int num, String description, double prixNuitée, int idHotel) {
        this.id = id;
        this.num = num;
        this.description = description;
        this.prixNuitée = prixNuitée;
        this.idHotel = idHotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixNuitée() {
        return prixNuitée;
    }

    public void setPrixNuitée(double prixNuitée) {
        this.prixNuitée = prixNuitée;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    @Override
    public String toString() {
        return "Chambre{" + "id=" + id + ", num=" + num + ", description=" + description + ", prixNuit\u00e9e=" + prixNuitée + ", idHotel=" + idHotel + '}';
    }
    
    
    
    
}
