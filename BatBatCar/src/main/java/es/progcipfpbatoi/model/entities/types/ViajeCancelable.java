package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.ArrayList;
import java.util.Iterator;

public class ViajeCancelable extends Viaje {
    public ViajeCancelable(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasOfertables, int precio) {
        super(codigoDeViaje, propietario, ruta, min, plazasOfertables, precio);
    }

    public void cancelarReserva(String codigoReserva) {
        // Iterar sobre las reservas del viaje
        Iterator<Reserva> iterator = reservas.iterator();
        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            // Verificar si el código de reserva coincide
            if (reserva.getCodiReserva().equals(codigoReserva)) {
                iterator.remove();
                GestorIO.print("Reserva cancelada correctamente.");
                return;
            }
        }
        GestorIO.print("No se encontró ninguna reserva con el código proporcionado.");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String tipodeavion() {
        return "Cancelable";
    }
}
