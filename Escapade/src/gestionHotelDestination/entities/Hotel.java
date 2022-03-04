/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.entities;

import java.io.File;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Meryem
 */
public class Hotel {

    private int idHotel;
    private String matriculeFiscale;
    private String nom;

    private int nbrEtoile;
    private String description;
    private int nbChambreTotal;
    private int maxChambre;
    private Destination destination;
    private String img;
    private ImageView imgview;

    public Hotel() {
    }

    public Hotel(String matriculeFiscale, String nom,
            int nbrEtoile, String description, int maxChambre,
            Destination destination, String img) {
        this.matriculeFiscale = matriculeFiscale;
        this.nom = nom;

        this.nbrEtoile = nbrEtoile;
        this.description = description;
        this.nbChambreTotal = 0;
        this.maxChambre = maxChambre;
        this.destination = destination;
        this.img = img;
    }

    public Hotel(int id, String matriculeFiscale, String nom,
            int nbrEtoile, String description, int maxChambre,
            Destination destination, String img) {
        this.idHotel = id;
        this.matriculeFiscale = matriculeFiscale;
        this.nom = nom;

        this.nbrEtoile = nbrEtoile;
        this.description = description;
        this.nbChambreTotal = 0;
        this.maxChambre = maxChambre;
        this.destination = destination;
        this.img = img;
    }
      

    public Hotel( String matriculeFiscale, String nom,
            int nbrEtoile, String description, int maxChambre,
            Destination destination, String img, int nbChambreTotal) {
   
        this.matriculeFiscale = matriculeFiscale;
        this.nom = nom;

        this.nbrEtoile = nbrEtoile;
        this.description = description;
        this.nbChambreTotal = nbChambreTotal;
        this.maxChambre = maxChambre;
        this.destination = destination;
        this.img = img;
    }
     public Hotel( int id,String matriculeFiscale, String nom,
            int nbrEtoile, String description, int maxChambre,
            Destination destination, String img, int nbChambreTotal) {
   
        this.matriculeFiscale = matriculeFiscale;
        this.nom = nom;
        this.idHotel=id;
        this.nbrEtoile = nbrEtoile;
        this.description = description;
        this.nbChambreTotal = nbChambreTotal;
        this.maxChambre = maxChambre;
        this.destination = destination;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ImageView getImgview() {
        return imgview;
    }

    public void setImgview(ImageView imgview) {
        this.imgview = imgview;
        imgview.setFitHeight(100);
        imgview.setFitWidth(100);
        imgview.setPreserveRatio(false);
    }

    public int getIdHotel() {
        return idHotel;
    }

    public static List<Hotel> generateImageViews(List<Hotel> hotels) {
        List<Hotel> liste = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {
            File f = new File("C:\\Users\\AH-INFO\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + hotel.getImg());
            hotel.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(hotel);
        }
        return liste;
    }

    public static ArrayList<Hotel> generateImageViews(ArrayList<Hotel> hotels) {
        ArrayList<Hotel> liste = new ArrayList<Hotel>();

        for (Hotel hotel : hotels) {
            File f = new File("C:\\Users\\AH-INFO\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + hotel.getImg());
            hotel.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(hotel);
        }
        return liste;
    }

    public static Hotel generateImageViews(Hotel hotel) {

        Hotel h;

        File f = new File("C:\\Users\\AH-INFO\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + hotel.getImg());
        hotel.setImgview(new ImageView(new Image(f.toURI().toString())));
        h = hotel;

        return h;
    }

    public void setIdHotel(int id) {
        this.idHotel = id;
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

    public int getMaxChambre() {
        return maxChambre;
    }

    public void setMaxChambre(int maxChambre) {
        this.maxChambre = maxChambre;
    }

    public int getNbChambreTotal() {
        return nbChambreTotal;
    }

    public void setNbChambreTotal(int nbChambreTotal) {
        this.nbChambreTotal = nbChambreTotal;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Hotel{" + "matriculeFiscale=" + matriculeFiscale + ", nom=" + nom
                + ", adresse=" + nbrEtoile + ", description="
                + description + ", nbChambreTotal=" + nbChambreTotal
                + "nb max chambre: " + maxChambre + "Emplacement:  Ville: " + destination.getVille()
                + "Pays: " + destination.getPays() + ", img=" + img + ", imgview=" + imgview + '}';
    }

    @Override
    public int hashCode() {

        return idHotel + nbrEtoile + matriculeFiscale.hashCode() + maxChambre;
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
        if (this.idHotel != other.idHotel) {
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

        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

}
