package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.entities.types.ViajeExclusivo;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de viajes. Manejará la lista de los viajes tanto para almancenar nueva
 * información sobre ella como para recuperar la totalidad o parte de la información
 * como también información relacionada con dicha lista.
 *
 * @author batoi
 */

public class ViajesManager {

    private List<Viaje> viajes;

    public ViajesManager() {
        this.viajes = new ArrayList<>();
        init();

    }

    /**
     * Añade un nuevo viaje al repositorio
     *
     * @param
     */
    public void add(Viaje viaje) {
        viajes.add(viaje);
    }

    /**
     * Cancela un viaje
     *
     * @param viaje
     */
    public void cancel(Viaje viaje) {
        viajes.remove(viaje);
    }

    /**
     * Obtiene el número de viajes actualmente registrados
     *
     * @return
     */
    public int getNumViajes() {
        return viajes.size();
    }

    /**
     * Obtiene todos los viajes
     *
     * @return
     */
    public List<Viaje> findAll() {
        return viajes;
    }

    private void init() {
        // añade a la colección "viajes" todos los viajes que creas necesario tener de inicio en tu sistema
        // this.add(new Viaje(....));
    }

    public List<Viaje> buscarViajesReservables(Usuario usuario) {
        List<Viaje> viajesReservables = new ArrayList<>();
        for (Viaje viaje : viajes) {
            // Verificar si el viaje no está cancelado y no pertenece al usuario
            if (!viaje.getCancelado() && !viaje.getPropietario().equals(usuario)) {
                viajesReservables.add(viaje);
            }
        }
        return viajesReservables;
    }

    public List<Viaje> buscarViajesReservablesPorUsuario(Usuario usuario) {
        List<Viaje> viajesReservables = new ArrayList<>();
        for (Viaje viaje : viajes) {
            // Verificar si el viaje no está cancelado y  pertenece al usuario
            if (!viaje.getCancelado() && viaje.getPropietario().equals(usuario)) {
                viajesReservables.add(viaje);
            }
        }
        return viajesReservables;
    }

    public Viaje obtenerViajePorReserva(Reserva reserva) {
        for (Viaje viaje : viajes) {
            if (viaje.contieneReserva(reserva)) {
                return viaje;
            }
        }
        return null;
    }
}
