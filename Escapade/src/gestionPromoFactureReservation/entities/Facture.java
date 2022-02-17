/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class Facture {
    
    private int id;
    private float prixTotal;
    private Date date ;
    private int idClient;
    private float prixFinal;

    public Facture() {
    }

    public Facture(int id, float prixTotal, Date date, int idClient, float prixFinal) {
        this.id = id;
        this.prixTotal = prixTotal;
        this.date = date;
        this.idClient = idClient;
        this.prixFinal = prixFinal;
    }

    public Facture(float prixTotal, Date date, int idClient, float prixFinal) {
        this.prixTotal = prixTotal;
        this.date = date;
        this.idClient = idClient;
        this.prixFinal = prixFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }

   

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", prixTotal=" + prixTotal + ", date=" + date + ", idClient=" + idClient + ", prixFinal=" + prixFinal + '}';
    }
    
    
    
    
}
