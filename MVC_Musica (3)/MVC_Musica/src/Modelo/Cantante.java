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
public class Cantante {
    private int idCantante;
    private String Nombre;
    private String NombreReal;
    private String Telefono;
    private String Direccion;
    private int idUsuario;

    public Cantante() {
        this.idCantante = 0;
        this.Nombre = null;
        this.NombreReal = null;
        this.Telefono = null;
        this.Direccion = null;
        this.idUsuario = 0;
    }

    public int getIdCantante() {
        return idCantante;
    }

    public void setIdCantante(int idCantante) {
        this.idCantante = idCantante;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNombreReal() {
        return NombreReal;
    }

    public void setNombreReal(String NombreReal) {
        this.NombreReal = NombreReal;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}

    