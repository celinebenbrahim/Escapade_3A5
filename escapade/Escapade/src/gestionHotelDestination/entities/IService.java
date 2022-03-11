/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionHotelDestination.entities;

import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Meryem
 */
public interface IService  <T>{
    
    void supprimerId(int id)throws SQLException;
    void ajouter(T entity)throws SQLException;
    void supprimer(T entity)throws SQLException;
    void modifier(T entity,int id)throws SQLException;
    List<T> afficher()throws SQLException;
    List<T> tri()throws SQLException;
    void rechercher(String pays )throws SQLException;
}
