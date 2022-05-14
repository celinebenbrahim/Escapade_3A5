/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escapade.utils;

import java.sql.*;


/**
 *
 * @author Meryem
 */
public class DataSource {
    
      private String url="jdbc:mysql://127.0.0.1:3306/escp";
    private String user="root";
    private String password="";
    private Connection cnx; 
    private static DataSource instance;
    
    private DataSource(){
        try{
        cnx=DriverManager.getConnection(url, user, password);
            System.out.println("DB connected");
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public static DataSource getInstance()
    { 
        if(instance==null)
        {
            instance=new DataSource();
        }
        return instance;
    }
  
    
    public Connection getCnx()
    {
        return cnx;
    }
    
    
}
