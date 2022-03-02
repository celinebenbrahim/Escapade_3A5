/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;
import gestionUserReclamation.entities.Utilisateur;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Facture {

    private int id;
    private double prixTotal;
    private Date date;
    private Utilisateur client;
    private double prixFinal;
    private Promotion promotion;

    public Facture() {
    }

    public Facture(int id, Double prixTotal, Date date, Utilisateur client, Double prixFinal, Promotion promotion) {
        this.id = id;
        this.prixTotal = prixTotal;
        this.date = date;
        this.client = client;
        this.prixFinal = prixFinal;
        this.promotion = promotion;
    }

    public Facture(Double prixTotal, Date date, Utilisateur client, Double prixFinal, Promotion promotion) {
        this.prixTotal = prixTotal;
        this.date = date;
        this.client = client;
        this.prixFinal = prixFinal;
        this.promotion = promotion;
    }

   
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(Double prixFinal) {
        this.prixFinal = prixFinal;
    }

  

   

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "Facture{"  + ", prixTotal=" + prixTotal + ", date=" + date + ", client=" + client + ", prixFinal=" + prixFinal + ", promotion=" + promotion + '}';
    }

    

    

   

}
