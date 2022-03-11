/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
     private String img;
      private ImageView imgview;

    public Chambre() {
    }

    public Chambre( int num, Type type, VueSurMer vueSurMer, String description, double prixNuit, Status status, String img) {
        this.num = num;
        this.type = type;
        this.vueSurMer = vueSurMer;
        this.description = description;
        this.prixNuit = prixNuit;
        this.status = status;
        this.img = img;
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

    public Chambre(int id, int num, Type type, VueSurMer vueSurMer, String description, double prixNuit, Status status, Hotel hotel, String img, ImageView imgview) {
        this.id = id;
        this.num = num;
        this.type = type;
        this.vueSurMer = vueSurMer;
        this.description = description;
        this.prixNuit = prixNuit;
        this.status = status;
        this.hotel = hotel;
        this.img = img;
        this.imgview = imgview;
    }
    
      public Chambre(int num, Type type, VueSurMer vueSurMer, String description, double prixNuit, Status status, Hotel hotel, String img) {
      
        this.num = num;
        this.type = type;
        this.vueSurMer = vueSurMer;
        this.description = description;
        this.prixNuit = prixNuit;
        this.status = status;
        this.hotel = hotel;
        this.img = img;
      
        
    }

 
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
    public VueSurMer getVueSurMer() {
        return vueSurMer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public double getPrixNuit() {
        return prixNuit;
    }

    public void setPrixNuit(double prixNuitée) {
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
        return "Chambre{" + "num=" + num + ", type=" + type + ", vueSurMer=" + vueSurMer + ", description=" + description + ", prixNuit=" + prixNuit + ", status=" + status + ", img=" + img + ", imgview=" + imgview + '}';
    }

    public static List<Chambre> generateImageViews(List<Chambre> chambres) {
        List<Chambre> liste = new ArrayList<Chambre>();

          for (Chambre chambre : chambres) {
            File f = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + chambre.getImg());
            chambre.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(chambre);
        }
        return liste;
    }

    public static ArrayList<Chambre> generateImageViews(ArrayList<Chambre> chambres) {
        ArrayList<Chambre> liste = new ArrayList<Chambre>();

        for (Chambre chambre : chambres) {
            File f = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + chambre.getImg());
            chambre.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(chambre);
        }
        return liste;
    }

    public static Chambre generateImageViews(Chambre chambre) {

        Chambre ch;

        File f = new File("C:\\Users\\asus\\Documents\\NetBeansProjects\\Escapade\\src\\view\\ressources\\images\\" + chambre.getImg());
        chambre.setImgview(new ImageView(new Image(f.toURI().toString())));
        ch = chambre;

        return ch;
    }
  
    
    
    
    
}
