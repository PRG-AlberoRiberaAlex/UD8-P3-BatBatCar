package es.progcipfpbatoi.model.entities.types;

import es.progcipfpbatoi.model.entities.Usuario;

public class Reserva {
    private static int contadorReservas = 0;
    private String codigo;
    private Usuario usuario;
    protected int numero_plazas;

    public Reserva(Usuario usuario, int numeroPlazas) {
        this.usuario = usuario;
        this.numero_plazas = numeroPlazas;
        this.codigo = generarCodiReservaUnic();
    }

    private String generarCodiReservaUnic() {
        return "RES-" + (++contadorReservas); // Generación de código de reserva único
    }

    public boolean validarReserva(Viaje viaje) {
        // Condición a: El usuario no puede ser igual al propietario
        if (usuario.equals(viaje.getPropietario())) {
            System.out.println("Error: El usuario que hace la reserva es igual al propietario.");
            return false;
        }

        // Condición b: El usuario no puede haber realizado ya una reserva en el viaje
        if (!viaje.compararUsuario(viaje,usuario)) {
            System.out.println("Error: El usuario ya ha realizado una reserva en este viaje.");
            return false;
        }

        // Condición c: El viaje no debe estar cerrado ni cancelado
        if (viaje.getCerrado() || viaje.getCancelado()) {
            System.out.println("Error: El viaje está cerrado o cancelado.");
            return false;
        }

        // Condición d: Deben haber plazas disponibles
        if (viaje.getPlazas_reservadas() + numero_plazas > viaje.getPlazas_reservables()) {
            System.out.println("Error: No hay plazas disponibles para estas solicitudes.");
            return false;
        }

        // Todas las condiciones son válidas
        return true;
    }

    public String getCodiReserva() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getNumero_plazas() {
        return numero_plazas;
    }
}
