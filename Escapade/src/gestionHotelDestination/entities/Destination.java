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
public class Destination {
   
    private int id;
    private String pays;
    private String ville;
    private String img;
    private ImageView imgview;

    public Destination() {
    }

    public Destination(String pays, String ville) {
        this.pays = pays;
        this.ville = ville;
    }
      public Destination(int id,String pays, String ville) {
        this.id=id;
        this.pays = pays;
        this.ville = ville;
    }

    public Destination(String pays, String ville, String img) {
        this.pays = pays;
        this.ville = ville;
        this.img = img;
    }

    public Destination(String pays, String ville, String img, ImageView imgview) {
        this.pays = pays;
        this.ville = ville;
        this.img = img;
        this.imgview = imgview;
    }
    

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
       
        return id+ville.hashCode()+pays.hashCode();
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
  
   public static List<Destination> generateImageViews(List<Destination> Destinations) {
        List<Destination> liste = new ArrayList<Destination>();

        for (Destination destination : Destinations) {
            File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\"+destination.getImg());
            destination.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(destination);
        }
        return liste;
    }
    public static  ArrayList<Destination> generateImageViews( ArrayList<Destination> Destinations) {
         ArrayList<Destination> liste = new ArrayList<Destination>();

        for (Destination destination : Destinations) {
            File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\"+destination.getImg());
            destination.setImgview(new ImageView(new Image(f.toURI().toString())));
            liste.add(destination);
        }
        return liste;
    }
     public static Destination generateImageViews(Destination destination) {
        
         Destination d;
        
            File f = new File("C:\\Users\\mahdi\\Desktop\\Escapade_3A5-Celine\\Escapade_3A5-Celine\\Escapade\\src\\view\\ressources\\images\\" + destination.getImg());
            destination.setImgview(new ImageView(new Image(f.toURI().toString())));
            d=destination;
        
        return d;
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
        final Destination other = (Destination) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Destination{" + "pays=" + pays + ", ville=" + ville + ", img=" + img + '}';
    }

    
    
    
    
    
}
