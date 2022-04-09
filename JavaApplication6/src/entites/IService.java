/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package entites;

import java.util.List;

/**
 *
 * @author remo
 */
public interface IService<T> {
    
    boolean ajouter(T entity);
    boolean supprimer(T entity);
    boolean modifier(T entity);
    List<T> afficher();
    
}
