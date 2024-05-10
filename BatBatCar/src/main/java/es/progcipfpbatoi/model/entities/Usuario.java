package es.progcipfpbatoi.model.entities;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {
    private String nomUsuario;
    private String contraseña;

    public Usuario(String nomUsuari, String contraseña) {
        this.nomUsuario = nomUsuari;
        this.contraseña = contraseña;
    }


    public String getNomUsuario() {
        return nomUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public String toString() {
        return getNomUsuario();
    }
}
