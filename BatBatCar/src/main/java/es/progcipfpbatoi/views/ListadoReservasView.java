package es.progcipfpbatoi.views;

import es.progcipfpbatoi.model.entities.types.Reserva;

import java.util.List;

public class ListadoReservasView {
    private List<Reserva> reservas;

    public ListadoReservasView(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void visualizar() {
        GestorIO.print("=======================================");
        GestorIO.print("       Listado de Reservas             ");
        GestorIO.print("=======================================");
        GestorIO.print("CÓDIGO\tUSUARIO\t\tPLAZAS");

        for (Reserva reserva : reservas) {
            GestorIO.print(reserva.getCodiReserva() + "\t" + reserva.getUsuario().getNomUsuario() + "\t\t" + reserva.getNumero_plazas());
        }

        GestorIO.print("=======================================");
    }
}
