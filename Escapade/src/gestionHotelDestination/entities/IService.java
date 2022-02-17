/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.entities;

import java.util.*;

/**
 *
 * @author Meryem
 */
public interface IService  <T>{
    
    void supprimerId(int id);
    void ajouter(T entity);
    void supprimer(T entity);
    void modifier(T entity,int id);
    List<T> afficher();
    
}
