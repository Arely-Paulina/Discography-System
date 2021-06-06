/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Cancion;
import Modelo.ConexionSQL;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class CancionDAO {
    private ConexionSQL cc;
    private Connection cn;
    
    public CancionDAO() {
        cc=new ConexionSQL();
        cn= cc.GetConexion();
    }
    
    public int UltimoRegistro(){
       int valor=0;
       try{
           String SQL="SELECT MAX(idCancion) AS idCancion FROM Cancion";
           Statement st=cn.createStatement();
           ResultSet rs=st.executeQuery(SQL);
           if(rs!=null){
               rs.next();
               valor=rs.getInt(1);
            }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Consulta fallida"+e.getMessage());
       }
       return valor;
    }
    public String insertarCancion(int idCancion,String nombre, int idAlbum, String Duracion, String Genero, int idUsuario){
        String resul=null;
        try{
            String SQL="insert into Cancion(idCancion,nombre,idAlbum,duracion,genero,idUsuario) values(?,?,?,?,?,?)";    
            PreparedStatement pst=cn.prepareStatement(SQL);
            pst.setInt(1, idCancion);
            pst.setString(2, nombre);
            pst.setInt(3, idAlbum);
            pst.setString(4, Duracion);
            pst.setString(5, Genero);
            pst.setInt(6, idUsuario);
            int n=pst.executeUpdate();
            if(n>0)
            JOptionPane.showMessageDialog(null, "Registro exitoso");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Registro fallido"+e.getMessage());
            }
        return resul;
    }

    public ArrayList<Cancion> ListaCancion(){
        
        ArrayList ListaCancion=new ArrayList();
        Cancion cancion;
        try{
            String SQL ="Select distinctrow idCancion, Cancion.nombre, Album.nombre, Cancion.duracion, Cancion.genero from Cancion INNER JOIN Album ON Cancion.idAlbum=Album.idAlbum ";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                cancion=new Cancion();
                cancion.setIdCancion(rs.getInt(1));
                cancion.setNombre(rs.getString(2));
                cancion.setNombreAlbum(rs.getString(3));
                cancion.setDuracion(rs.getString(4));
                cancion.setGenero(rs.getString(5));
                ListaCancion.add(cancion);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "nN se pueden enlistar los registros"+e.getMessage());
        }
        return ListaCancion;
    }
    
    public ArrayList<Cancion> FiltrarCancion(String valor){
        
        ArrayList ListaCancion=new ArrayList();
        Cancion cancion;
        try{
            String SQL ="Select distinctrow idCancion, Cancion.nombre, Album.nombre, Cancion.duracion, Cancion.genero from Cancion INNER JOIN Album ON Cancion.idAlbum=Album.idAlbum where Cancion.nombre like '%"+valor+"%'";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                cancion=new Cancion();
                cancion.setIdCancion(rs.getInt(1));
                cancion.setNombre(rs.getString(2));
                cancion.setNombreAlbum(rs.getString(3));
                cancion.setDuracion(rs.getString(4));
                cancion.setGenero(rs.getString(5));
                ListaCancion.add(cancion);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaCancion;
    }

    public void Modificar(String idCancion, String nombre, int idAlbum, String duracion, String genero){
        try{
            String SQL="update Cancion set nombre=?, idAlbum=?, duracion=?, genero=? where idCancion=?";
            String dAct=idCancion;
            PreparedStatement pst= cn.prepareStatement(SQL);
            pst.setString(1, nombre);
            pst.setInt(2, idAlbum);
            pst.setString(3, duracion);
            pst.setString(4, genero);
            pst.setString(5, dAct);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado con éxito"); 
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el registro"+e.getMessage());
        }
    }
    
      public void Eliminar(String idCancion){
        try{
            String SQL= "delete from Cancion where idCancion="+idCancion;
            Statement st=cn.createStatement();
            int n=st.executeUpdate(SQL);
            if(n>=0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");  
        
            }
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Error al eliminar registro "+e.getMessage());  
        }
    }
}
