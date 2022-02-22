/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionUserReclamation.entities;

import java.util.Date;

/**
 *
 * @author mahdi
 */
public class Reclamation {

    private int id;
    private Date date;
    private String description;
    private int idClient;

    public Reclamation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Reclamation(int id, Date date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }




    public Reclamation() {
    }

    public Reclamation(int id, Date date, String description, int idClient) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.idClient = idClient;
    }

    public Reclamation(Date date, String description, int idClient) {
        this.date = date;
        this.description = description;
        this.idClient = idClient;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", date=" + date + ", description=" + description + ", idClient=" + idClient + '}';
    }

}
