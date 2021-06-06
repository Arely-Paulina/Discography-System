/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Album;
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
public class AlbumDAO {
    private ConexionSQL cc;
    private Connection cn;
    
    public AlbumDAO(){
        cc=new ConexionSQL();
        cn= cc.GetConexion();
    }
    
    public int UltimoRegistro(){
        int valor=0;
        try{
            String SQL="SELECT MAX(idAlbum) AS idAlbum FROM Album";
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
    
    public String insertarAlbum(int idAlbum,String Nombre, String Genero, String Duracion, String Grabacion, String Productor,int idCantante, int idUsuario){
        String resul=null;
        try{
            String SQL="insert into Album(idAlbum,nombre,genero,duracion,grabacion,productor,idCantante, idUsuario) values(?,?,?,?,?,?,?,?)";    
            PreparedStatement pst=cn.prepareStatement(SQL);
            pst.setInt(1, idAlbum);
            pst.setString(2, Nombre);
            pst.setString(3, Genero);
            pst.setString(4, Duracion);
            pst.setString(5, Grabacion);
            pst.setString(6, Productor);
            pst.setInt(7, idCantante);
            pst.setInt(8, idUsuario);
            int n=pst.executeUpdate();
            if(n>0)
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registro fallido"+e.getMessage());
        }
        return resul;
    }

    public ArrayList<Album> ListaAlbum(){
        
        ArrayList ListaAlbum=new ArrayList();
        Album album;
        
        try{
            String SQL ="Select distinctrow idAlbum, Album.nombre, genero, duracion, grabacion, productor, Cantante.nombre from Album INNER JOIN Cantante ON Album.idCantante=Cantante.idCantante ";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                album=new Album();
                album.setIdAlbum(rs.getInt(1));
                album.setNombre(rs.getString(2));
                album.setGenero(rs.getString(3));
                album.setDuracion(rs.getString(4));
                album.setGrabacion(rs.getString(5));
                album.setProductor(rs.getString(6));
                album.setNombreCantante(rs.getString(7));
                
                
                ListaAlbum.add(album);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaAlbum;
    }
   
       public ArrayList<Album> FiltrarAlbum(String valor){
        
        ArrayList ListaAlbum=new ArrayList();
        Album album;
        
        try{
            String SQL="Select distinctrow idAlbum, Album.nombre, genero, duracion, grabacion, productor, Cantante.nombre from Album INNER JOIN Cantante ON Album.idCantante=Cantante.idCantante where Album.nombre like '%"+valor+"%'";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                album=new Album();
                album.setIdAlbum(rs.getInt(1));
                album.setNombre(rs.getString(2));
                album.setGenero(rs.getString(3));
                album.setDuracion(rs.getString(4));
                album.setGrabacion(rs.getString(5));
                album.setProductor(rs.getString(6));
                album.setNombreCantante(rs.getString(7));
                
                
                ListaAlbum.add(album);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaAlbum;
    }

    
 
    public void Modificar(String idAlbum, String nombre, String genero, String duracion, String grabacion, String productor,int idCantante){
        try{
            String SQL="update Album set nombre=?, genero=?, duracion=?, grabacion=?, productor=?, idCantante=? where idAlbum=?";
            String dAct=idAlbum;
            PreparedStatement pst= cn.prepareStatement(SQL);
            pst.setString(1, nombre);
            pst.setString(2, genero);
            pst.setString(3, duracion);
            pst.setString(4, grabacion);
            pst.setString(5, productor);
            pst.setInt(6, idCantante);
            pst.setString(7, dAct);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado con éxito"); 
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el registro"+e.getMessage());
        }
    }
    
  public void Eliminar(String idAlbum){
        try{
            String SQL= "delete from Album where idAlbum="+idAlbum;
            Statement st=cn.createStatement();
            int n=st.executeUpdate(SQL);
            if(n>=0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");  
        
            }
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Error al eliminar registro "+e.getMessage());  
        }
    }
  
     public ArrayList<String> llenarCombo(){
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<String> lista=new ArrayList<String>();
        String SQL="Select * from Album";
        try{
            ps= cn.prepareStatement(SQL);
            rs= ps.executeQuery();
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "Error al llenar combobox "+e.getMessage()); 
        }
        try{
            while(rs.next()){
                lista.add(rs.getString("Nombre"));
            
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al llenar combobox "+e.getMessage());
        }
        return lista;
    }
    
     public String idAlbum(String Nombre){
        String Album=null;
        Connection conexion=null;
        try{
            conexion=cc.GetConexion();
            String SQL="Select idAlbum, nombre from Album where nombre= '"+Nombre+"'";
            PreparedStatement st=conexion.prepareStatement(SQL);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                Album=String.valueOf(rs.getInt(1));
            }else{
                Album=null;
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al encontrar el Id del álbum :"+e.getMessage());  
        }
        return Album;
   }
}
