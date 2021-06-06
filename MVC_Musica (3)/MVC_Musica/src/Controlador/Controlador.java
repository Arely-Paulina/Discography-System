/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.AlbumDAO;
import Modelo.CancionDAO;
import Modelo.CantanteDAO;
import Modelo.UsuarioDAO;
import Vistas.VistaMenu;
import Vistas.Vista;
import Vistas.VistaCreditos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author Arely
 */
public class Controlador implements ActionListener, KeyListener, MouseListener{
    private Vista iniciarSesion;
    private VistaMenu menu=new VistaMenu();
    private VistaCreditos creditos=new VistaCreditos();
    private UsuarioDAO UD=new UsuarioDAO();
    private CantanteDAO CD=new CantanteDAO();
    private AlbumDAO AD=new AlbumDAO();
    private CancionDAO CAD=new CancionDAO();
    private String Cargo;
    int nUsuario;
    int nCantante;
    int nAlbum;
    int nCancion;
    int fila, filaCantante,filaAlbum, filaCancion;
    String UsuarioActual;
    int idActual;
    byte[] imagen;
    
    public Controlador(Vista iniciarSesion, UsuarioDAO DU,CantanteDAO CD,AlbumDAO AD, CancionDAO CAD){
    this.iniciarSesion=iniciarSesion;
    this.UD=UD;
    this.CD=CD;
    this.AD=AD;
    this.CAD=CAD;
    //this.menu=new Menu();
    this.iniciarSesion.btnExit.addActionListener(this);
    this.iniciarSesion.btnIngresar.addActionListener(this);
    this.menu.btnUsuarios.addActionListener(this);
    this.menu.btnCanciones.addActionListener(this);
    this.menu.btnCantantes.addActionListener(this);
    this.menu.btnAlbums.addActionListener(this);
    this.menu.btnExit2.addActionListener(this);
    this.menu.btnExit3.addActionListener(this);
    this.menu.btnExit4.addActionListener(this);
    this.menu.btnExit5.addActionListener(this);
    this.menu.btnExit6.addActionListener(this);
    this.menu.btnExit7.addActionListener(this);
    this.menu.btnExit8.addActionListener(this);
    this.menu.btnExit9.addActionListener(this);
    this.menu.btnEliminarAlbum.addActionListener(this);
    this.menu.btnEliminarCancion.addActionListener(this);
    this.menu.btnEliminarCantante.addActionListener(this);
    this.menu.btnEliminarUsuario.addActionListener(this);
    this.menu.btnInsertarAlbum.addActionListener(this);
    this.menu.btnInsertarCancion.addActionListener(this);
    this.menu.btnInsertarCantante.addActionListener(this);
    this.menu.btnInsertarUsuario.addActionListener(this);
    this.menu.btnModificarAlbum.addActionListener(this);
    this.menu.btnModificarCancion.addActionListener(this);
    this.menu.btnModificarCantante.addActionListener(this);
    this.menu.btnModificarUsuario.addActionListener(this);
    this.menu.txtDuracion.addKeyListener(this);
    this.menu.txtTelefono.addKeyListener(this);
    this.menu.txtDuracionCancion.addKeyListener(this);
    this.menu.btnGuardarAlbum.addActionListener(this);
    this.menu.btnGuardarCancion.addActionListener(this);
    this.menu.btnGuardarUsuario.addActionListener(this);
    this.menu.btnGuardarCantante.addActionListener(this);
    this.menu.tblUsuarios.addMouseListener(this);
    this.menu.tblCantantes.addMouseListener(this);
    this.menu.tblCanciones.addMouseListener(this);
    this.menu.tblAlbum.addMouseListener(this);
    this.nUsuario=UD.UltimoRegistro();
    this.nCantante=CD.UltimoRegistro();
    this.nAlbum=AD.UltimoRegistro();
    this.nCancion=CAD.UltimoRegistro();
    this.menu.txtBuscarUsuario.addKeyListener(this);
    this.menu.txtBuscarCantante.addKeyListener(this);
    this.menu.txtBuscarAlbum.addKeyListener(this);
    this.menu.txtBuscarCancion.addKeyListener(this);
    this.creditos.btnExit.addActionListener(this);
    this.iniciarSesion.btnCreditos.addActionListener(this);
    this.creditos.btnRegresar.addActionListener(this);
    this.menu.txtNombre.addKeyListener(this);
    this.menu.txtNombreReal.addKeyListener(this);
    this.menu.btnImagen.addActionListener(this);
    this.menu.btnImagen1.addActionListener(this);
    
    
    }

    public void iniciar(){
    this.iniciarSesion.setVisible(true);
    this.menu.setLocationRelativeTo(null);
    this.menu.txtUsuario.setText(String.valueOf(nUsuario+1));
    }
    
    public void llenarCombo(){
        this.menu.cbxCantante.removeAllItems();
        ArrayList<String> lista=new ArrayList<String>();
        lista=this.CD.llenarCombo();
        for(int i=0;i<lista.size();i++){
            this.menu.cbxCantante.addItem(lista.get(i));         
       }
        
        this.menu.cbxAlbumCancion.removeAllItems();
        ArrayList<String> listaAlbum=new ArrayList<String>();
        listaAlbum=this.AD.llenarCombo();
        for(int i=0;i<listaAlbum.size();i++){
            this.menu.cbxAlbumCancion.addItem(listaAlbum.get(i));
        }
    }
    public void limpiarUsuario(){
        this.menu.txtUsuario.setText(String.valueOf(nUsuario+1));
        this.menu.txtNickname.setText("");
        this.menu.txtClave.setText("");
        this.menu.txtNombre.setText("");
        this.menu.cbxCargo.setSelectedIndex(0);
        this.menu.txtRuta.setText("");
    }
    
    public void limpiarCantante(){
        this.menu.txtIdCantante.setText(String.valueOf(nCantante+1));
        this.menu.txtNombreArt.setText("");
        this.menu.txtNombreReal.setText("");
        this.menu.txtTelefono.setText("");
        this.menu.txtDireccion.setText("");
    }
    
    public void limpiarAlbum(){
        this.menu.txtIdAlbum.setText(String.valueOf(nAlbum+1));
        this.menu.txtNombreAlbum.setText("");
        this.menu.cbxCantante.setSelectedIndex(0);
        this.menu.txtProductor.setText("");
        this.menu.txtDuracion.setText("");
        this.menu.cbxGeneroAlbum.setSelectedIndex(0);
        this.menu.jDateGrabacion.setDateFormatString("2020-08-10");
        
    }
    
    public void limpiarCancion(){
        this.menu.txtIdCancion.setText(String.valueOf(nCancion+1));
        this.menu.txtNombreCancion.setText("");
        this.menu.cbxAlbumCancion.setSelectedIndex(0);
        this.menu.txtDuracionCancion.setText("");
        this.menu.cbxGeneroCancion.setSelectedIndex(0);
    }
    public void llenarTabla(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Usuario");
            modeloTabla.addColumn("Nickname");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Cargo");
            
            Object[] columna=new Object[5];
            int numberRegistros=UD.ListaUsuario().size();
            
            for(int i=0; i<numberRegistros;i++){
                columna[0]=UD.ListaUsuario().get(i).getIdUsuario();
                columna[1]=UD.ListaUsuario().get(i).getNickname();
                columna[2]=UD.ListaUsuario().get(i).getNombre();
                columna[3]=UD.ListaUsuario().get(i).getCargo();
                modeloTabla.addRow(columna);
              
            }
    
    }
    
    public void llenarTablaCantante(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Canción");
            modeloTabla.addColumn("Nombre artístico");
            modeloTabla.addColumn("Nombre real");
            modeloTabla.addColumn("Teléfono");
            modeloTabla.addColumn("Direccion");
            
            Object[] columna=new Object[5];
            int numberRegistros=CD.ListaCantante().size();
            for(int i=0;i<numberRegistros;i++){
                columna[0]=CD.ListaCantante().get(i).getIdCantante();
                columna[1]=CD.ListaCantante().get(i).getNombre();
                columna[2]=CD.ListaCantante().get(i).getNombreReal();
                columna[3]=CD.ListaCantante().get(i).getTelefono();
                columna[4]=CD.ListaCantante().get(i).getDireccion();
                modeloTabla.addRow(columna);
            }
    }
    
    public void llenarTablaAlbum(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id álbum");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Genéro");
            modeloTabla.addColumn("Duración");
            modeloTabla.addColumn("Fecha de grabación");
            modeloTabla.addColumn("Productor");
            modeloTabla.addColumn("Cantante");
            
            Object[] columna=new Object[7];
            int numberRegistros=AD.ListaAlbum().size();
            for(int i=0;i<numberRegistros;i++){
                columna[0]=AD.ListaAlbum().get(i).getIdAlbum();
                columna[1]=AD.ListaAlbum().get(i).getNombre();
                columna[2]=AD.ListaAlbum().get(i).getGenero();
                columna[3]=AD.ListaAlbum().get(i).getDuracion();
                columna[4]=AD.ListaAlbum().get(i).getGrabacion();
                columna[5]=AD.ListaAlbum().get(i).getProductor();
                columna[6]=AD.ListaAlbum().get(i).getNombreCantante();
                modeloTabla.addRow(columna);
            }
    
    }
    
    public void llenarTablaCancion(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Canción");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Álbum");
            modeloTabla.addColumn("Duración");
            modeloTabla.addColumn("Género");
            
            Object[] columna=new Object[5];
            int numberRegistros=CAD.ListaCancion().size();
            for(int i=0;i<numberRegistros;i++){
                columna[0]=CAD.ListaCancion().get(i).getIdCancion();
                columna[1]=CAD.ListaCancion().get(i).getNombre();
                columna[2]=CAD.ListaCancion().get(i).getNombreAlbum();
                columna[3]=CAD.ListaCancion().get(i).getDuracion();
                columna[4]=CAD.ListaCancion().get(i).getGenero();
                modeloTabla.addRow(columna);
            }
    
    }
    
    public void filtrar(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Usuario");
            modeloTabla.addColumn("Nickname");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Cargo");
            
            Object[] columna=new Object[5];
            int numberRegistros=UD.FiltrarUsuario(this.menu.txtBuscarUsuario.getText()).size();
            
            for(int i=0; i<numberRegistros;i++){
                columna[0]=UD.FiltrarUsuario(this.menu.txtBuscarUsuario.getText()).get(i).getIdUsuario();
                columna[1]=UD.FiltrarUsuario(this.menu.txtBuscarUsuario.getText()).get(i).getNickname();
                columna[2]=UD.FiltrarUsuario(this.menu.txtBuscarUsuario.getText()).get(i).getNombre();
                columna[3]=UD.FiltrarUsuario(this.menu.txtBuscarUsuario.getText()).get(i).getCargo();
                modeloTabla.addRow(columna);
              
            }
            
    }
    
    public void filtrarCantantes(JTable tablita){
    DefaultTableModel modeloTabla=new DefaultTableModel();
        tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Canción");
            modeloTabla.addColumn("Nombre artístico");
            modeloTabla.addColumn("Nombre real");
            modeloTabla.addColumn("Teléfono");
            modeloTabla.addColumn("Direccion");
            
            Object[] columna=new Object[5];
            int numberRegistros=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).size();
            for(int i=0;i<numberRegistros;i++){
                
                columna[0]=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).get(i).getIdCantante();
                columna[1]=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).get(i).getNombre();
                columna[2]=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).get(i).getNombreReal();
                columna[3]=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).get(i).getTelefono();
                columna[4]=CD.FiltrarCantante(this.menu.txtBuscarCantante.getText()).get(i).getDireccion();
                modeloTabla.addRow(columna);
            }
    }
    
    public void filtrarAlbum(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
            tablita.setModel(modeloTabla);
                modeloTabla.addColumn("Id Álbum");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Genéro");
                modeloTabla.addColumn("Duración");
                modeloTabla.addColumn("Fecha de grabación");
                modeloTabla.addColumn("Productor");
                modeloTabla.addColumn("Cantante");
                
                Object[] columna=new Object[7];
                int numberRegistros=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).size();
                for(int i=0;i<numberRegistros;i++){
                    columna[0]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getIdAlbum();
                    columna[1]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getNombre();
                    columna[2]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getGenero();
                    columna[3]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getDuracion();
                    columna[4]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getGrabacion();
                    columna[5]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getProductor();
                    columna[6]=AD.FiltrarAlbum(this.menu.txtBuscarAlbum.getText()).get(i).getNombreCantante();
                    modeloTabla.addRow(columna);
                    
                }
                
    }
    
    public void filtrarCancion(JTable tablita){
        DefaultTableModel modeloTabla=new DefaultTableModel();
            tablita.setModel(modeloTabla);
            modeloTabla.addColumn("Id Canción");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Álbum");
            modeloTabla.addColumn("Duración");
            modeloTabla.addColumn("Género");
            
            Object[] columna=new Object[5];
            int numberRegistros=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).size();
            for(int i=0;i<numberRegistros;i++){
                columna[0]=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).get(i).getIdCancion();
                columna[1]=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).get(i).getNombre();
                columna[2]=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).get(i).getNombreAlbum();
                columna[3]=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).get(i).getDuracion();
                columna[4]=CAD.FiltrarCancion(this.menu.txtBuscarCancion.getText()).get(i).getGenero();
                modeloTabla.addRow(columna);
            }
            
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String boton=e.getActionCommand();
        
            switch(boton){
            
                case "X":
                    this.iniciarSesion.dispose();
                    this.menu.dispose();
                    this.creditos.dispose();
                    System.exit(0);
                    break;
                
                case "Ingresar":
                    
                    if(this.UD.iniciarSesion(this.iniciarSesion.txtNick.getText(), String.valueOf(this.iniciarSesion.txtpassword.getPassword()))!=null){
                           switch(this.UD.getCargo()){
                                
                                case "empleado":
                                    
                                    this.menu.btnUsuarios.setEnabled(false);
                                    this.menu.btnEliminarAlbum.setEnabled(false);
                                    this.menu.btnEliminarCancion.setEnabled(false);
                                    this.menu.btnEliminarCantante.setEnabled(false);
                                    break;
                            }
                            
                        this.UsuarioActual=this.UD.getNickname();
                        this.idActual=this.UD.getId();
                        try{
                            this.imagen=UD.getImagen();
                            BufferedImage image = null;
                            InputStream in = new ByteArrayInputStream(this.imagen);
                            image = ImageIO.read(in);
                            ImageIcon imgi = new ImageIcon(image.getScaledInstance(150, 150, 0));
                            this.menu.lblImagen.setIcon(imgi);

                }catch(Exception ex){
                    
                }
                        this.menu.setVisible(true);
                        this.iniciarSesion.setVisible(false);
                        this.menu.lblUser.setText(UsuarioActual);  
                        this.llenarCombo();
                        this.llenarTablaCancion(this.menu.tblCanciones);
                    }else{
                         JOptionPane.showMessageDialog(null, "El nickname y/o clave son incorrectos");
                    }
                    
                    
                    break;
                
                case "Usuarios":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlUsuarios, RSPanelsSlider.DIRECT.RIGHT);
                    this.llenarTabla(this.menu.tblUsuarios);
                    break;
                    
                case "imagen": 
                    
                    JFileChooser j = new JFileChooser();
                    FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
                    j.setFileFilter(fil);
        
                    int s = j.showOpenDialog(this.menu);
                    if(s == JFileChooser.APPROVE_OPTION){
                    String ruta = j.getSelectedFile().getAbsolutePath();
                    this.menu.txtRuta.setText(ruta);
                    }
                    break;
                    
                case "modificarImagen":
                     
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter fileName = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
                    fileChooser.setFileFilter(fileName);
        
                    int sf = fileChooser.showOpenDialog(this.menu);
                    if(sf == JFileChooser.APPROVE_OPTION){
                    String rutaDos = fileChooser.getSelectedFile().getAbsolutePath();
                    this.menu.txtRuta1.setText(rutaDos);
                    }
                    break;
                
                case "Canciones":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlCanciones,RSPanelsSlider.DIRECT.RIGHT);
                    this.llenarTablaCancion(this.menu.tblCanciones);
                    
                    break;
                
                case "Cantantes":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlCantantes,RSPanelsSlider.DIRECT.RIGHT);
                    this.llenarTablaCantante(this.menu.tblCantantes);
                    break;
            
                case "Album":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlAlbum,RSPanelsSlider.DIRECT.RIGHT);
                    this.llenarTablaAlbum(this.menu.tblAlbum);
                    break;
                case "insertarUsuario":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlFormUsuario,RSPanelsSlider.DIRECT.RIGHT);
                    break;    
                case "modificarUsuario":
                    
                    this.UD.Modificar(this.menu.tblUsuarios.getValueAt(fila,0).toString(),this.menu.tblUsuarios.getValueAt(fila,1).toString(), this.menu.tblUsuarios.getValueAt(fila, 2).toString(), this.menu.tblUsuarios.getValueAt(fila, 3).toString() );
                    if(!this.menu.txtRuta1.getText().isEmpty()){
                        File rutaDos=new File(this.menu.txtRuta1.getText());
                        this.UD.ModificarImagen(this.menu.tblUsuarios.getValueAt(fila, 0).toString(), rutaDos);
                    }
                    this.menu.txtRuta1.setText("");
                    break;
                case "eliminarUsuario":
                    this.UD.Eliminar(this.menu.tblUsuarios.getValueAt(fila, 0).toString());
                    this.llenarTabla(this.menu.tblUsuarios);
                    break;  
                case "insertarCancion":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlFormCanciones,RSPanelsSlider.DIRECT.RIGHT);
                    break;    
                case "modificarCancion":
                    if(this.AD.idAlbum(this.menu.tblCanciones.getValueAt(filaCancion, 2).toString())!=null){
                        int albumCancion=Integer.parseInt(this.AD.idAlbum(this.menu.tblCanciones.getValueAt(filaCancion, 2).toString()));
                        this.CAD.Modificar(this.menu.tblCanciones.getValueAt(filaCancion, 0).toString(), this.menu.tblCanciones.getValueAt(filaCancion, 1).toString(), albumCancion, this.menu.tblCanciones.getValueAt(filaCancion, 3).toString(), this.menu.tblCanciones.getValueAt(filaCancion, 4).toString());
                    }else{
                        JOptionPane.showMessageDialog(null, "El álbum no existe en la base de datos");
                    }
                    break;
                case "eliminarCancion":
                    this.CAD.Eliminar(this.menu.tblCanciones.getValueAt(filaCancion, 0).toString());
                    this.llenarTablaCancion(this.menu.tblCanciones);
                    break;    
                case "insertarCantante":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlFormCantantes,RSPanelsSlider.DIRECT.RIGHT);
                    break;    
                case "modificarCantante":
                    this.CD.Modificar(this.menu.tblCantantes.getValueAt(filaCantante, 0).toString(), this.menu.tblCantantes.getValueAt(filaCantante, 1).toString(), this.menu.tblCantantes.getValueAt(filaCantante, 2).toString(), this.menu.tblCantantes.getValueAt(filaCantante, 3).toString(), this.menu.tblCantantes.getValueAt(filaCantante, 4).toString());
                    break;
                case "eliminarCantante":
                    this.CD.Eliminar(this.menu.tblCantantes.getValueAt(filaCantante, 0).toString());
                    this.llenarTablaCantante(this.menu.tblCantantes);
                    break;    
                case "insertarAlbum":
                    this.menu.rSPanelsSlider1.setPanelSlider(20, menu.pnlFormAlbum,RSPanelsSlider.DIRECT.RIGHT);
                    break;    
                case "modificarAlbum":
                    
                    if(this.CD.idCantante(this.menu.tblAlbum.getValueAt(filaAlbum,6).toString())!=null){
                        
                        int cantanteAlbum=Integer.parseInt(this.CD.idCantante(this.menu.tblAlbum.getValueAt(filaAlbum,6).toString()));
                        this.AD.Modificar(this.menu.tblAlbum.getValueAt(filaAlbum, 0).toString(), this.menu.tblAlbum.getValueAt(filaAlbum, 1).toString(), this.menu.tblAlbum.getValueAt(filaAlbum, 2).toString(), this.menu.tblAlbum.getValueAt(filaAlbum, 3).toString(), this.menu.tblAlbum.getValueAt(filaAlbum, 4).toString(), this.menu.tblAlbum.getValueAt(filaAlbum, 5).toString(), cantanteAlbum);
                    }else{
                        JOptionPane.showMessageDialog(null, "El cantante no existe en la base de datos");
                    }
                    
                    break;
                case "eliminarAlbum":
                    this.AD.Eliminar(this.menu.tblAlbum.getValueAt(filaAlbum, 0).toString());
                    this.llenarTablaAlbum(this.menu.tblAlbum);
                    
                    break;   
                
                case "GuardarAlbum":
                    this.validarAlbum();
                    this.limpiarAlbum();
                    break;
                case "GuardarCancion":
                    this.validarCancion();
                    this.limpiarCancion();
                    break;
                case "GuardarCantante":
                    this.validarCantante();
                    this.limpiarCantante();
                    break;
                case "GuardarUsuario":
                    this.validarUsuario();
                    this.limpiarUsuario();
                    break;
                    
                case "creditos":
                    this.creditos.setVisible(true);
                    this.iniciarSesion.setVisible(false);
                    break;
                case "Regresar":
                    this.creditos.dispose();
                    this.iniciarSesion.setVisible(true);
                    break;
        
        }
        }catch(Exception a){
            JOptionPane.showMessageDialog(null, "Fatality "+a.getCause());
        }
 
   }

    public void validarUsuario(){
        
        if(!this.menu.txtNickname.getText().isEmpty()&&!this.menu.txtClave.getText().isEmpty()&&!this.menu.txtNombre.getText().isEmpty()&&this.menu.cbxCargo.getSelectedIndex()!=0 ){
            File ruta = new File(this.menu.txtRuta.getText());
            this.UD.insertarUsuario(++nUsuario,this.menu.txtNickname.getText(), this.menu.txtClave.getText(), this.menu.txtNombre.getText(),String.valueOf(this.menu.cbxCargo.getSelectedItem()), ruta);
        }else{
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }
        if(this.menu.cbxCargo.getSelectedIndex()!=0){
        
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un cargo");
        }
    
    }
    
    public void validarCancion(){
    
        if(!this.menu.txtNombreCancion.getText().isEmpty()&&!this.menu.txtDuracionCancion.getText().isEmpty()&&this.menu.cbxGeneroCancion.getSelectedIndex()!=0){
            this.CAD.insertarCancion(++nCancion,this.menu.txtNombreCancion.getText(), Integer.parseInt(this.AD.idAlbum(String.valueOf(this.menu.cbxAlbumCancion.getSelectedItem()))), this.menu.txtDuracionCancion.getText(), String.valueOf(this.menu.cbxGeneroCancion.getSelectedIndex()), idActual);
        }else{
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }
        
        if(this.menu.cbxGeneroCancion.getSelectedIndex()!=0){
        
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un genéro");
        }
        
    }
    
    public void validarCantante(){
        if(!this.menu.txtNombreArt.getText().isEmpty()&&!this.menu.txtTelefono.getText().isEmpty()&&!this.menu.txtNombreReal.getText().isEmpty()&&!this.menu.txtDireccion.getText().isEmpty()){
            this.CD.insertarCantante(++nCantante,this.menu.txtNombreArt.getText(),this.menu.txtNombreReal.getText() ,this.menu.txtTelefono.getText() ,this.menu.txtDireccion.getText(), idActual);
        }else{
            JOptionPane.showMessageDialog(null, "Faltan campos por llenar");
        }
    }
    
    public void validarAlbum(){
        
        if(!this.menu.txtNombreAlbum.getText().isEmpty()&&!this.menu.txtProductor.getText().isEmpty()&&!this.menu.txtDuracion.getText().isEmpty()&&this.menu.cbxGeneroAlbum.getSelectedIndex()!=0&&this.menu.jDateGrabacion.getDate()!=null){
            Date d = this.menu.jDateGrabacion.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-d");
            String date = df.format(d);
            this.AD.insertarAlbum(++nAlbum,this.menu.txtNombreAlbum.getText(),String.valueOf(this.menu.cbxGeneroAlbum.getSelectedIndex()), this.menu.txtDuracion.getText(),date,this.menu.txtProductor.getText(), Integer.parseInt(this.CD.idCantante(String.valueOf(this.menu.cbxCantante.getSelectedItem()))), idActual);
    
        }else{
            JOptionPane.showMessageDialog(null, "Faltan campos llenar");
        }
        if(this.menu.jDateGrabacion.getDate()!=null){
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona una fecha");
        }
        
        if(this.menu.cbxGeneroAlbum.getSelectedIndex()!=0){
        
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un genéro");
        }
    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       if(e.getSource()==this.menu.txtDuracion || e.getSource()==this.menu.txtDuracionCancion){ 
        char caracter = e.getKeyChar();
        if(((caracter < '0') ||(caracter > '9')) &&(caracter != ':'))
        {
             e.consume(); 
        }
       }
  
       if(e.getSource()==this.menu.txtTelefono){
        char caracter = e.getKeyChar();
        if(((caracter < '0') ||(caracter > '9')))
        {
             e.consume(); 
        }
       
       }
       if(e.getSource()==this.menu.txtNombre || e.getSource()==this.menu.txtNombreReal){
           char caracter=e.getKeyChar();
           if(!Character.isLetter(caracter) && caracter!=KeyEvent.VK_SPACE){
               e.consume();
           }
       }
   
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.filtrar(this.menu.tblUsuarios);
        this.filtrarCantantes(this.menu.tblCantantes);
        this.filtrarAlbum(this.menu.tblAlbum);
        this.filtrarCancion(this.menu.tblCanciones);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.fila=this.menu.tblUsuarios.getSelectedRow();
        this.filaCantante=this.menu.tblCantantes.getSelectedRow();
        this.filaAlbum=this.menu.tblAlbum.getSelectedRow();
        this.filaCancion=this.menu.tblCanciones.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
