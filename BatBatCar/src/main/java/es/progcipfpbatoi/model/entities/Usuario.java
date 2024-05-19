package es.progcipfpbatoi.model.entities;

import es.progcipfpbatoi.model.entities.types.Reserva;

import java.util.ArrayList;
import java.util.List;
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
    public List<Reserva> getReservas() {
        return reservas;
    }

}
