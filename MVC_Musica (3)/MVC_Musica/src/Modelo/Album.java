/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author HP
 */
public class Album {
    private int idAlbum;
    private String Nombre;
    private String Genero;
    private String Duracion;
    private String Grabacion;
    private String Productor;
    private int idCantante;
    private String nombreCantante;

    public String getNombreCantante() {
        return nombreCantante;
    }

    public void setNombreCantante(String nombreCantante) {
        this.nombreCantante = nombreCantante;
    }
    private int idUsuario;
    

    public Album() {
        this.idAlbum = 0;
        this.Nombre = null;
        this.Genero = null;
        this.Duracion = null;
        this.Grabacion = null;
        this.Productor = null;
        this.idCantante = 0;
        this.idUsuario = 0;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getGrabacion() {
        return Grabacion;
    }

    public void setGrabacion(String Grabacion) {
        this.Grabacion = Grabacion;
    }

    public String getProductor() {
        return Productor;
    }

    public void setProductor(String Productor) {
        this.Productor = Productor;
    }

    public int getIdCantante() {
        return idCantante;
    }

    public void setIdCantante(int idCantante) {
        this.idCantante = idCantante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
}
