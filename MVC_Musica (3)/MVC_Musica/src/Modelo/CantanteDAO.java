
package Modelo;

import Modelo.Cantante;
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


public class CantanteDAO {
    private ConexionSQL cc;
    private Connection cn;
    
    public CantanteDAO(){
        cc=new ConexionSQL();
        cn= cc.GetConexion();
    }
    
    public int UltimoRegistro(){
    int valor=0;
    try{
        String SQL="SELECT MAX(idCantante) AS idCantante FROM Cantante";
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
    
    public String insertarCantante(int idCantante,String Nombre, String nombreReal, String Telefono, String Direccion, int idUsuario){
        String resul=null;
        try{
        String SQL="insert into Cantante(idCantante,nombre,nombreReal,telefono,direccion,idUsuario) values(?,?,?,?,?,?)";    
        PreparedStatement pst=cn.prepareStatement(SQL);
        pst.setInt(1, idCantante);
        pst.setString(2, Nombre);
        pst.setString(3, nombreReal);
        pst.setString(4, Telefono);
        pst.setString(5, Direccion);
        pst.setInt(6, idUsuario);
        int n=pst.executeUpdate();
        if(n>0)
        JOptionPane.showMessageDialog(null, "Registro exitoso");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registro fallido"+e.getMessage());
        }
        return resul;
    }

    public ArrayList<Cantante> ListaCantante(){
        
        ArrayList ListaCantante=new ArrayList();
        Cantante cantante;
        try{
            String SQL ="select * from Cantante";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                cantante=new Cantante();
                cantante.setIdCantante(rs.getInt(1));
                cantante.setNombre(rs.getString(2));
                cantante.setNombreReal(rs.getString(3));
                cantante.setTelefono(rs.getString(4));
                cantante.setDireccion(rs.getString(5));
                cantante.setIdUsuario(rs.getInt(6));
                ListaCantante.add(cantante);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaCantante;
    }
    
    public ArrayList<Cantante> FiltrarCantante(String valor){
        
        ArrayList ListaCantantes=new ArrayList();
        Cantante cantante;
        try{
            String SQL ="select *from Cantante where nombre like '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(SQL);
            while (rs.next()){
                cantante=new Cantante();
                cantante.setIdCantante(rs.getInt(1));
                cantante.setNombre(rs.getString(2));
                cantante.setNombreReal(rs.getString(3));
                cantante.setTelefono(rs.getString(4));
                cantante.setDireccion(rs.getString(5));
                cantante.setIdUsuario(rs.getInt(6));
                ListaCantantes.add(cantante);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaCantantes;
    }
    
    public void Modificar(String idCantante, String nombre, String nombreReal, String telefono,String direccion){
        try{
            String SQL="update Cantante set nombre=?, nombreReal=?, telefono=?, direccion=? where idCantante=?";
            String dAct=idCantante;
            PreparedStatement pst= cn.prepareStatement(SQL);
            pst.setString(1, nombre);
            pst.setString(2, nombreReal);
            pst.setString(3, telefono);
            pst.setString(4, direccion);
            pst.setString(5, dAct);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado con éxito"); 
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el registro"+e.getMessage());
        }
    }
    
    public void Eliminar(String idCantante){
        try{
            String SQL= "delete from Cantante where idCantante="+idCantante;
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
        String SQL="Select * from Cantante";
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
    
       public String idCantante(String Nombre){
        String Cantante=null;
        Connection conexion=null;
        try{
            conexion=cc.GetConexion();
            String SQL="Select idCantante, nombre from Cantante where nombre= '"+Nombre+"'";
            PreparedStatement st=conexion.prepareStatement(SQL);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                Cantante=String.valueOf(rs.getInt(1));
            }else{
                Cantante=null;
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al encontrar el Id del cantante :"+e.getMessage());  
        }
        return Cantante;
   }
}
