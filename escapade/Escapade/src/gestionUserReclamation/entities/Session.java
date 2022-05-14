/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionUserReclamation.entities;

/**
 *
 * @author mahdi
 */
public class Session {
    private static int idUser;

    public static int getIdUser() {
        return idUser;
    }

    public  void  setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
