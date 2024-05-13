package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.ArrayList;

public class ViajeExclusivo extends Viaje {
    private boolean reservaRealizada = false;

    public ViajeExclusivo(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasOfertables, int precio) {
        super(codigoDeViaje, propietario, ruta, min, plazasOfertables, precio);
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
    public String toString() {
        return super.toString();
    }

    @Override
    public String tipodeavion() {
        return "Exclusivo";
    }

    public boolean getreservaRealizada() {
        return reservaRealizada;
    }
}
