/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;

import java.util.List;

/**
 *
 * @author asus
 */
public interface Iservice<T> {

    void supprimerId(int id);

    void ajouter(T entity);

    void supprimer(T entity);

    void modifier(T entity,int id);
    
   

    List<T> afficher();
}
