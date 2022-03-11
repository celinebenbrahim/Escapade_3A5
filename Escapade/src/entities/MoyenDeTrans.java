/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import utils.Status;

/**
 *
 * @author KattaX
 */
public class MoyenDeTrans {
    int id;
    String libelle;
    String type;
    int capacite;
    String description;
    Status status;
    float tarifJ;
    int idAgence;
    int idDestination;

    public MoyenDeTrans() {
        this.status = Status.Disponible;
    }

    public MoyenDeTrans(int id, String libelle, String type, int capacite, String description, float tarifJ, int idAgence, int idDestination) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.capacite = capacite;
        this.description = description;
        this.tarifJ = tarifJ;
        this.idAgence = idAgence;
        this.idDestination = idDestination;
    }

    public MoyenDeTrans(int id, String libelle, String type, int capacite, String description, Status status, float tarifJ, int idAgence, int idDestination) {
        this.id = id;
        this.libelle = libelle;
        this.type = type;
        this.capacite = capacite;
        this.description = description;
        this.status = status;
        this.tarifJ = tarifJ;
        this.idAgence = idAgence;
        this.idDestination = idDestination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getTarifJ() {
        return tarifJ;
    }

    public void setTarifJ(float tarifJ) {
        this.tarifJ = tarifJ;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    @Override
    public String toString() {
        return "MoyenDeTrans{"+ " libelle=" + libelle + ", type=" + type + ", capacite=" + capacite + ", description=" + description + ", status=" + status + ", tarifJ=" + tarifJ + ", idAgence=" + idAgence + ", idDestination=" + idDestination + '}';
    }
   
}
