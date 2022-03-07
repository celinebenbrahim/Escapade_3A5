/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author mahdi
 */
public class Session {

    private static int idUser = -1;

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        Session.idUser = idUser;
    }

    public static boolean start(int currentUserID) {
        if (idUser == -1) {
            idUser = currentUserID;
            return true;
        }
        return false;
    }

    public static void end() {
        idUser=-1;
    }

}
