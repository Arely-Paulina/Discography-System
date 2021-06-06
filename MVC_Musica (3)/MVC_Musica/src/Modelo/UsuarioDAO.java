package Modelo;
import Vistas.VistaMenu;
import Modelo.Usuario;
import Modelo.ConexionSQL;
import com.mysql.cj.protocol.Resultset;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
public class UsuarioDAO {
    private ConexionSQL cc;
    private Connection cn;
    private String nickname;
    private int id;
    private String cargo;
    private byte[] imagen;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    
    
    public UsuarioDAO(){
        cc=new ConexionSQL();
        cn= cc.GetConexion();
    }
    
    public int UltimoRegistro(){
    int valor=0;
    try{
        String SQL="SELECT MAX(idUsuario) AS idUsuario FROM usuario";
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
    
    public String insertarUsuario(int idUsuario, String Nickname, String Clave, String Nombre, String Cargo, File ruta){
        String resul=null;
        byte[] icono = new byte[(int) ruta.length()];
        try{
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            
        }catch(Exception ex){
            icono=null;
        }
        
        try{
        String SQL="insert into Usuario(idUsuario,nickname,clave,nombre,cargo,imagen) values(?,?,?,?,?,?)";    
        PreparedStatement pst=cn.prepareStatement(SQL);
        pst.setInt(1, idUsuario);
        pst.setString(2, Nickname);
        pst.setString(3, Clave);
        pst.setString(4, Nombre);
        pst.setString(5, Cargo);
        pst.setBytes(6, icono);
        int n=pst.executeUpdate();
        if(n>0)
        JOptionPane.showMessageDialog(null, "Registro exitoso");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registro fallido"+e.getMessage());
        }
        return resul;
    }

    public ArrayList<Usuario> ListaUsuario(){
        
        ArrayList ListaUsuarios=new ArrayList();
        Usuario usuario;
        try{
            String SQL ="select * from Usuario";
            PreparedStatement ps= cn.prepareStatement(SQL);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                usuario=new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNickname(rs.getString(2));
                usuario.setClave(rs.getString(3));
                usuario.setNombre(rs.getString(4));
                usuario.setCargo(rs.getString(5));
                ListaUsuarios.add(usuario);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaUsuarios;
    }
    
  
    public ArrayList<Usuario> FiltrarUsuario(String valor){
        
        ArrayList ListaUsuarios=new ArrayList();
        Usuario usuario;
        try{
            String SQL ="select *from Usuario where nickname like '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(SQL);
            while (rs.next()){
                usuario=new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNickname(rs.getString(2));
                usuario.setClave(rs.getString(3));
                usuario.setNombre(rs.getString(4));
                usuario.setCargo(rs.getString(5));
                ListaUsuarios.add(usuario);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pueden enlistar los registros"+e.getMessage());
        }
        return ListaUsuarios;
    }
    
    
    
    public void Modificar(String idUsuario, String Nickname, String Nombre, String Cargo){
        String resul=null;
        try{
            String SQL="update Usuario set nickname=?, nombre=?, cargo=? where idUsuario=?";
            String dAct=idUsuario;
            PreparedStatement pst= cn.prepareStatement(SQL);
            pst.setString(1, Nickname);
            pst.setString(2, Nombre);
            pst.setString(3, Cargo);
            pst.setString(4, dAct);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro modificado con éxito"); 
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el registro"+e.getMessage());
        }
    }
    
    public void ModificarImagen(String idUsuario, File ruta){
        byte[] icono = new byte[(int) ruta.length()];
        try{
            InputStream input = new FileInputStream(ruta);
            input.read(icono);
            
        }catch(Exception ex){
            icono=null;
        }
        try{
            String SQL="update Usuario set imagen=? where idUsuario=?";
            String dAct=idUsuario;
            PreparedStatement pst= cn.prepareStatement(SQL);
            pst.setBytes(1, icono);
            pst.setString(2, dAct);
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Imagen de usuario modficada"); 
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar el imagen"+e.getMessage());
        }
    }
    
    public void Eliminar(String idUsuario){
        try{
            String SQL= "delete from Usuario where idUsuario="+idUsuario;
            Statement st=cn.createStatement();
            int n=st.executeUpdate(SQL);
            if(n>=0){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");  
        
            }
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, "Error al eliminar registro "+e.getMessage());  
        }
    }
    
    public String iniciarSesion(String nickname, String clave){
        String buscarUsuario=null;
        Connection conexion=null;
        
        try{
            conexion=cc.GetConexion();
            String SQL=("Select idUsuario, nickname, clave, cargo, imagen from Usuario where nickname= '"+nickname+"' && clave='"+clave+"'");
            PreparedStatement st= conexion.prepareStatement(SQL);
            ResultSet rs= st.executeQuery();
            if(rs.next()){
                buscarUsuario=String.valueOf(rs.getInt(1));
                this.setId(rs.getInt(1));
                this.setNickname(rs.getString(2));
                this.setCargo(rs.getString(4));
                this.setImagen(rs.getBytes(5));
                
            }else{
                buscarUsuario=null;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al iniciar sesion :"+e.getMessage());  
        }
        return buscarUsuario;
    }

    
    
    

    
}
