package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.ArrayList;

public class ViajeExclusivo extends Viaje {
    private boolean reservaRealizada = false;

    public ViajeExclusivo(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasOfertables, int precio, boolean cerrado, boolean cancelado, ArrayList<Reserva> reservas) {
        super(codigoDeViaje, propietario, ruta, min, plazasOfertables, precio, cerrado, cancelado, reservas);
    }

    @Override
    public void añadirReserva(Reserva reserva) {
        if (!reservaRealizada) {
            super.añadirReserva(reserva);
            reservaRealizada = true;
        } else {
            GestorIO.print("Este viaje exclusivo solo permite una reserva.");
        }
    }
}
