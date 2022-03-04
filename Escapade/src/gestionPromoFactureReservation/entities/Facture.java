/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;
import gestionUserReclamation.entities.Utilisateur;

import java.util.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author asus
 */
public class Facture {

    private int idF;
    private float prixTotal;
    private Date date;
    private Utilisateur client;
    private float prixFinal;
    private Promotion promotion;

    public Facture() {
    }

    public Facture(int idF) {
        this.idF = idF;
    }

    public Facture(int idF, float prixTotal, Date date, Utilisateur client, float prixFinal, Promotion promotion) {
        this.idF = idF;
        this.prixTotal = prixTotal;
        this.date = date;
        this.client = client;
        this.prixFinal = prixFinal;
        this.promotion = promotion;
    }

    

    public Facture(float prixTotal, Date date, Utilisateur client, float prixFinal, Promotion promotion) {
        this.prixTotal = prixTotal;
        this.date = date;
        this.client = client;
        this.prixFinal = prixFinal;
        this.promotion = promotion;
    }

    

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
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

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
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
