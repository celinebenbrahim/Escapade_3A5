/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPromoFactureReservation.entities;

import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public interface Iservice<T> {

    void supprimerId(int id) throws SQLException;

    void ajouter(T entity) throws SQLException;

    void supprimer(T entity) throws SQLException ;

    void modifier(T entity,int id) throws SQLException;
    
   

    List<T> afficher() throws SQLException;
}
