/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author mahdi
 */
public class Crypt {
     public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
     public static boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
    
}
