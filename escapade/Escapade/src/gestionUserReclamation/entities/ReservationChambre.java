/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionUserReclamation.entities;

import java.util.Date;

/**
 *
 * @author AH-INFO
 */
public class ReservationChambre {
    
    private int id;
    private int idClient;
    private int idChambre;
    private Date dateAller;
    private Date dateRetour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
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

    @Override
    public String toString() {
        return "ReservationChambre{" + "idClient=" + idClient + ", idChambre=" + idChambre + ", dateAller=" + dateAller + ", dateRetour=" + dateRetour + '}';
    }

    public ReservationChambre(int idClient, int idChambre, Date dateAller, Date dateRetour) {
        this.idClient = idClient;
        this.idChambre = idChambre;
        this.dateAller = dateAller;
        this.dateRetour = dateRetour;
    }

    public ReservationChambre() {
    }

  
    
    
}
