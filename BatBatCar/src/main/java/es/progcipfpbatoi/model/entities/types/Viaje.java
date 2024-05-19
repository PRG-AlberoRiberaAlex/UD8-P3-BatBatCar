package es.progcipfpbatoi.model.entities.types;
/*
 * Clase que representa a un viaje estándar
 */

import es.progcipfpbatoi.model.entities.Usuario;

import java.util.ArrayList;
import java.util.Iterator;

public class Viaje {
    private int CodigoDeViaje;
    private Usuario Propietario;
    private String Ruta;
    private int Min;
    private int Plazas_reservables;
    private static int Plazas_reservadas = 0;
    private int Precio;
    private boolean Cerrado;
    private boolean Cancelado;
    protected ArrayList<Reserva> reservas;

    public Viaje(int codigoDeViaje, Usuario propietario, String ruta, int min, int plazasReservables, int precio) {
        CodigoDeViaje = codigoDeViaje;
        Propietario = propietario;
        Ruta = ruta;
        Min = min;
        Plazas_reservables = plazasReservables ;
        Precio = precio;
        reservas= new ArrayList<>();
    }
    public String tipodeavion(){
        return "Estandar";
    }

    public double getprecioTotal() {
        return Precio * Plazas_reservadas;
    }

    // Método para añadir una reserva al viaje
    public void añadirReserva(Reserva reserva) {
        if (!Cerrado && !Cancelado && Plazas_reservadas < Plazas_reservables) {
            if (!Propietario.equals(reserva.getUsuario())) {
                if (compararUsuario(this, reserva.getUsuario())) {
                    if (Plazas_reservadas + reserva.getNumero_plazas() <= Plazas_reservables) {
                        // Validar la reserva utilizando el método validarReserva() de la clase Reserva
                        if (reserva.validarReserva(this)) {
                            reservas.add(reserva);
                            Plazas_reservadas += reserva.getNumero_plazas();
                            System.out.println("Reserva añadida correctamente.");
                        }
                    } else {
                        System.out.println("No hay suficientes plazas disponibles.");
                    }
                }
            } else {
                System.out.println("El propietario no puede realizar una reserva en su propio viaje.");
            }
        } else {
            System.out.println("No se puede añadir la reserva en este momento.");
        }
    }


    // Metodo para cerrar el viaje
    public void tancarViatge() {
        if (Plazas_reservadas == Plazas_reservables) {
            Cerrado = true;
            System.out.println("Viaje cerrado correctamente. No se permiten mas reservas.");
        } else {
            System.out.println("No se puede cerrar el viatge, aun hay plazas disponibles.");
        }
    }

    // Metodo para cancelar el viaje
    public void cancelarViaje() {
        Cancelado = true;
        System.out.println("Viaje cancelado correctamente.");
    }
    public boolean contieneReserva(Reserva reserva) {
        return reservas.contains(reserva);
    }

    public int getCodigoDeViaje() {
        return CodigoDeViaje;
    }

    public boolean compararUsuario(Viaje viatge, Usuario usuario) {
        for (Reserva reserva : viatge.reservas) {
            if (reserva.getUsuario().equals(usuario)) {
                System.out.println("Error: El usuario ya ha realizado una reserva en este viaje.");
                return false;
            }
        }
        return true;
    }
    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        Plazas_reservables -= reserva.getNumero_plazas();
        System.out.println("Reserva eliminada correctamente.");
    }


    public boolean compararCodigo() {
        for (Reserva reserva : reservas) {
            if (reserva.getCodiReserva().equals(String.valueOf(CodigoDeViaje))) {
                return true;
            }
        }
        return false;
    }

    public void mostrarReservas() {
        Iterator it = reservas.iterator();
        while (it.hasNext()) {
            Reserva element = (Reserva) it.next();
            System.out.print(element.getCodiReserva() + " - " + element.getUsuario() + " - " + element.getNumero_plazas());
        }
    }
    public void cerrarViaje() {
        if (!Cerrado && Plazas_reservadas == Plazas_reservables) {
            Cerrado = true;
            System.out.println("Viaje cerrado correctamente. No se permiten más reservas.");
        } else {
            System.out.println("No se puede cerrar el viaje. Asegúrate de que todas las plazas estén reservadas.");
        }
    }



    public Usuario getPropietario() {
        return Propietario;
    }

    public String getRuta() {
        return Ruta;
    }

    public boolean getCerrado() {
        return Cerrado;
    }

    public boolean getCancelado() {
        return Cancelado;
    }

    public int getPlazas_reservadas() {
        return Plazas_reservadas;
    }

    public int getPlazas_reservables() {
        return Plazas_reservables;
    }

    @Override
    public String toString() {
        return "Viaje de tipo " + tipodeavion() + " del propietario " + getPropietario().getNomUsuario() + " con codigo " + getCodigoDeViaje() + " y ruta " + getRuta() + " con " + getPlazas_reservables() + " plazas ofertadas añadido con éxito";
    }


    public void setUsuario(Usuario usuario) {
        this.Propietario=usuario;
    }

    public int getPrecio() {
        return Precio;
    }
    public void setPlazas_reservadas(int nuevasPlazasReservadas) {
        this.Plazas_reservadas = nuevasPlazasReservadas;
    }
}




