/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Chaima
 */
public class Commentaire {

    int id;
    User user;
    AgenceLoc agence;
    String username;
    String Contenu;
    String date;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

  
        public Commentaire(int id, User user,  AgenceLoc agence, String Contenu, String date,String username) {
        this.id = id;
        this.user = user;
        this.agence = agence;
        this.Contenu = Contenu;
        this.date = date;
        this.username = username;

    }

    public Commentaire(int id, User user, AgenceLoc agence, String Contenu, String date) {
        this.id = id;
        this.user = user;
        this.agence = agence;
        this.Contenu = Contenu;
        this.date = date;
    }

    public AgenceLoc getAgence() {
        return agence;
    }

    public void setAgence(AgenceLoc product) {
        this.agence = product;
    }
 

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   @Override
    public String toString() {
        return "NAME:" + username + " \n" + Contenu + " \nDATE:" + date  ;
    }
    
}
