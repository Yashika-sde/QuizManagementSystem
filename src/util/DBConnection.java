/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author acer
 */
public class DBConnection
{
    private static final String URL = "jdbc:postgresql://localhost:5432/quizdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "magizhchi";
    
    public static Connection getConnection()
    {
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL,USER,PASSWORD);     
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;      
        }
       
    }
}
