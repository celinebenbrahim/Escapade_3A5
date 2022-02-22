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
    private Type type ;
    private VueSurMer vueSurMer ;
    private String description ;
    private double prixNuit ;
    private Status status ;
    private Hotel hotel;

    public Chambre() {
    }

    public Chambre(int num, Type type, VueSurMer vueSurMer, String description, double prixNuitée, Status status, Hotel hotel) {
        this.num = num;
        this.type = type;
        this.vueSurMer = vueSurMer;
        this.description = description;
        this.prixNuit = prixNuitée;
        this.status = status;
        this.hotel = hotel;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public VueSurMer getVueSurMer() {
        return vueSurMer;
    }

    public void setVueSurMer(VueSurMer vueSurMer) {
        this.vueSurMer = vueSurMer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return prixNuit;
    }

    public void setPrixNuitée(double prixNuitée) {
        this.prixNuit = prixNuitée;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Chambre{" + "num=" + num + ", type=" + type + ", vueSurMer=" + vueSurMer + ", description=" + description + ", prixNuit=" + prixNuit + 
                ", status=" + status + ",Hotel: " + hotel.getNom() +  " } ";
    }

  
    
    
    
    
}
