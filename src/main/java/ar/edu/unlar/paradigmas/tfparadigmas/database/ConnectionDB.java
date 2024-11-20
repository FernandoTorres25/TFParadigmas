/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.unlar.paradigmas.tfparadigmas.database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author WinUser
 */
public class ConnectionDB {
    
    Connection conexion = null;
    
    String user = "postgres";
    String password = "Casiokorg12";
    String bd = "accidenteslaborales";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;

    public Connection obtenerConexion(){
            try {
                Class.forName("org.postgresql.Driver");
                
                conexion = DriverManager.getConnection(cadena,user,password);
                
                //JOptionPane.showMessageDialog(null, "Se conecto correctamente a la db");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error:"+ e.toString());
            }
            
            return conexion;
    }
}
