package es.progcipfpbatoi.model.entities;

import es.progcipfpbatoi.model.entities.types.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Clase que representa a un usuario de la aplicación
 */
public class Usuario {
    private String nomUsuario;
    private String contraseña;
    private List<Reserva> reservas;

    public Usuario(String nomUsuari, String contraseña) {
        this.nomUsuario = nomUsuari;
        this.contraseña = contraseña;
        this.reservas = new ArrayList<>();

    }
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public boolean haRealizadoReservaParaViaje(Viaje viaje) {
        for (Reserva reserva : reservas) {
            if (reserva.getCodiReserva().equals(viaje)) {
                return true;
            }
        }
        return false;
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
