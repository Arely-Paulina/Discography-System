
package Main;

import Controlador.Controlador;
import Modelo.AlbumDAO;
import Modelo.CancionDAO;
import Modelo.CantanteDAO;
import Modelo.UsuarioDAO;
import Vistas.Vista;
import javax.swing.JOptionPane;

/**
 * @author Arely
 */
public class MVC {

    public static void main(String[] args) {
    
        try{
            Vista ventana=new Vista();
            UsuarioDAO DU=new UsuarioDAO();
            CantanteDAO CD=new CantanteDAO();
            AlbumDAO AD=new AlbumDAO();
            CancionDAO CAD=new CancionDAO();
            Controlador controller=new Controlador(ventana,DU,CD,AD,CAD);
            controller.iniciar();
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Fatality"); 
        }
    }
    
}
