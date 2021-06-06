/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class ConexionSQL {
    private static Connection conn;
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static String user="root";
    private static String password="1234";
    private static String url="jdbc:mysql://localhost:3306/cancion";
    
    public ConexionSQL(){
        conn=null;
        try{
            Class.forName(driver);
            conn=(Connection)DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa");
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Conexión fallida "+ e.getMessage());
        }
    }
    
    public Connection GetConexion(){
        return conn;
    }
    
    public void Desconectar(){
        conn=null;
    }
}
