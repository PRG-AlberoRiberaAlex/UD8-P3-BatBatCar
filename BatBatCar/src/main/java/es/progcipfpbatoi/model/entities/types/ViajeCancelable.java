package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.ArrayList;
import java.util.Iterator;

public class ViajeCancelable extends Viaje {
    public ViajeCancelable(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasOfertables, int precio, boolean cerrado, boolean cancelado, ArrayList<Reserva> reservas) {
        super(codigoDeViaje, propietario, ruta, min, plazasOfertables, precio, cerrado, cancelado,reservas);
    }

    public void cancelarReserva(String codigoReserva) {
        // Iterar sobre las reservas del viaje
        Iterator<Reserva> iterator = reservas.iterator();
        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            // Verificar si el código de reserva coincide
            if (reserva.getCodiReserva().equals(codigoReserva)) {
                // Eliminar la reserva del viaje
                iterator.remove();
                GestorIO.print("Reserva cancelada correctamente.");
                return;
            }
        }
        // Si no se encontró la reserva con el código dado
        GestorIO.print("No se encontró ninguna reserva con el código proporcionado.");
    }

}
