package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.ArrayList;

public class ViajeFlexible extends Viaje {
    public ViajeFlexible(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasOfertables, int precio) {
        super(codigoDeViaje, propietario, ruta, min, plazasOfertables, precio);
    }
    public void cambiarPlazas(Reserva reserva, int nuevasPlazas) {
        if (getPlazas_reservables() >= reservas.size()) {
            int index = reservas.indexOf(reserva);
            if (index != -1) {
                Reserva reservaExistente = reservas.get(index);
                reservaExistente.numero_plazas = nuevasPlazas;
                GestorIO.print("Número de plazas cambiado correctamente.");
            } else {
                GestorIO.print("La reserva no existe en este viaje.");
            }
        } else {
            GestorIO.print("No hay suficientes plazas reservables para realizar el cambio.");
        }
    }


    public void cambiarPrecio(int nuevoPrecio) {

    }
    public String toString() {
        return super.toString();
    }
    public String tipodeavion() {
        return "Flexible";
    }
}
