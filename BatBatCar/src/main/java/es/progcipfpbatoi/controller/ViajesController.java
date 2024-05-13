package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.*;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;
import es.progcipfpbatoi.views.ListadoViajesView;

import java.util.HashMap;
import java.util.List;

public class ViajesController {

    private Usuario usuarioActivo;
    private ViajesManager viajesManager;
    private HashMap<String, Usuario> usuariosRegistrados;
    private static final int MAX_INTENTOS = 3;
    private int intentosRestantes = MAX_INTENTOS;
    private Viaje viaje;
    private int codigoViajeActual = 1;


    public ViajesController() {
        this.viajesManager = new ViajesManager();
        usuariosRegistrados = new HashMap<>();

    }

    /**
     * Lista todos los viajes del sistema.
     */
    public void listarViajes() {
        List<Viaje> viajes = viajesManager.findAll();
        (new ListadoViajesView(viajes)).visualizar();
    }

    /**
     * Añade un viaje al sistema, preguntando previamente por toda la información necesaria para crearlo.
     */
    public void añadirViaje() {
        mostrarOpcionesViajes();
        Usuario usuario = getUsuario();
        if (usuario != null) {
            int tipoViaje = GestorIO.getInt("Selecciona el tipo de viaje:", 1, 4);
            String ruta = GestorIO.getString("Introduzca la ruta a realizar:");
            int duracion = GestorIO.getInt("Introduzca la duración del viaje en minutos:");
            int precio = GestorIO.getInt("Introduzca el precio de cada plaza:");
            int plazas = GestorIO.getInt("Introduzca el número de plazas disponibles:");

            switch (tipoViaje) {
                case 1:
                    this.viaje = new Viaje(codigoViajeActual, usuario, ruta, duracion, plazas, precio);
                    break;
                case 2:
                    this.viaje = new ViajeCancelable(codigoViajeActual, usuario, ruta, duracion, plazas, precio);
                    break;
                case 3:
                    this.viaje = new ViajeExclusivo(codigoViajeActual, usuario, ruta, duracion, plazas, precio);
                    break;
                case 4:
                    this.viaje = new ViajeFlexible(codigoViajeActual, usuario, ruta, duracion, plazas, precio);
                    break;
            }
            this.viaje.setUsuario(usuario);
            this.viajesManager.add(this.viaje);
            GestorIO.print(this.viaje.toString());
            codigoViajeActual++;
        } else {
            GestorIO.print("Error,debe establecer un usuario");
        }
    }

    public void cancelarViaje() {
        // Comprobar si hay un usuario activo
        if (usuarioActivo == null) {
            GestorIO.print("Error: No hay ningún usuario activo. Vuelve al menú principal.");
            return;
        }

        // Obtener la lista de viajes que el usuario puede cancelar
        List<Viaje> viajesCancelables = viajesManager.buscarViajesReservables(usuarioActivo);

        // Mostrar los viajes cancelables
       GestorIO.print("Listado de viajes cancelables:");
        for (Viaje viaje : viajesCancelables) {
            GestorIO.print(viaje.getCodigoDeViaje() + " - " + viaje.getRuta());
        }

        // Pedir al usuario que seleccione el viaje que desea cancelar
        int codigoViaje = GestorIO.getInt("Introduce el código del viaje que deseas cancelar (0 para cancelar):");

        // Si el usuario cancela la operación
        if (codigoViaje == 0) {
            GestorIO.print("Operación cancelada.");
            return;
        }

        // Buscar el viaje seleccionado por su código
        Viaje viajeCancelar = null;
        for (Viaje viaje : viajesCancelables) {
            if (viaje.getCodigoDeViaje() == codigoViaje) {
                viajeCancelar = viaje;
                break;
            }
        }

        // Verificar si se encontró el viaje seleccionado y si pertenece al usuario activo
        if (viajeCancelar == null || !viajeCancelar.getPropietario().equals(usuarioActivo)) {
            GestorIO.print("Error: El viaje seleccionado no existe o no se puede cancelar.");
            return;
        }

        // Cancelar el viaje
        viajeCancelar.cancelarViaje();
        GestorIO.print("Viaje cancelado correctamente.");
    }



    public void registrarUsuario() {
        String nombreUsuario = GestorIO.getString("Introduce el nombre de usuario:");

        if (usuariosRegistrados.containsKey(nombreUsuario)) {
            Usuario usuario = usuariosRegistrados.get(nombreUsuario);
            iniciarSesion(usuario);
        } else {
            String contraseña = GestorIO.getString("Introduce la contraseña:");

            // Verificar si el usuario existe como usuario activo
            Usuario usuarioActivo = getUsuario();
            if (usuarioActivo != null) {
                usuariosRegistrados.remove(usuarioActivo.getNomUsuario());
            }

            Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña);
            usuariosRegistrados.put(nombreUsuario, nuevoUsuario);
            setUsuarioActivo(nuevoUsuario);
            GestorIO.print("Bienvenido " + nombreUsuario + ". Usuario activo: " + nuevoUsuario.getNomUsuario());
        }
    }


    private void iniciarSesion(Usuario usuario) {
        int intentosRestantes = MAX_INTENTOS;

        do {
            String contraseña = GestorIO.getString("Introduce la contraseña:");

            if (contraseña.equals(usuario.getContraseña())) {
                GestorIO.print("¡Inicio de sesión exitoso! Bienvenido, " + usuario.getNomUsuario() + ".");
                return;
            } else {
                intentosRestantes--;
                GestorIO.print("Contraseña incorrecta. Intentos restantes: " + intentosRestantes);
            }

            if (intentosRestantes <= 0) {
                GestorIO.print("Se han agotado los intentos. Saliendo del programa...");
                System.exit(0);
            }
        } while (true);
    }
    public void realizarReserva() {
        if (usuarioActivo == null) {
            GestorIO.print("Error: No se ha establecido un usuario. Por favor, inicia sesión.");
            return;
        }

        // Obtener todos los viajes disponibles para el usuario activo
        List<Viaje> viajesDisponibles = viajesManager.buscarViajesReservables(usuarioActivo);

        // Mostrar los viajes disponibles al usuario
        GestorIO.print("Listado de viajes disponibles:");
        for (Viaje viaje : viajesDisponibles) {
            GestorIO.print(viaje.getCodigoDeViaje() + " - " + viaje.getRuta());
        }

        // Solicitar al usuario que seleccione un viaje
        int codigoViajeSeleccionado = GestorIO.getInt("Introduce el código del viaje que deseas reservar:");

        // Encontrar el viaje seleccionado por el usuario
        Viaje viajeSeleccionado = null;
        for (Viaje viaje : viajesDisponibles) {
            if (viaje.getCodigoDeViaje() == codigoViajeSeleccionado) {
                viajeSeleccionado = viaje;
                break;
            }
        }

        // Verificar si se encontró el viaje seleccionado
        if (viajeSeleccionado == null) {
            GestorIO.print("Error: El viaje seleccionado no está disponible para reservar.");
            return;
        }

        // Verificar si el viaje es exclusivo y si ya se ha realizado una reserva en él
        if (viajeSeleccionado instanceof ViajeExclusivo && ((ViajeExclusivo) viajeSeleccionado).getreservaRealizada()) {
            GestorIO.print("Error: Ya se ha realizado una reserva en este viaje exclusivo.");
            return;
        }

        // Pedir los datos necesarios para realizar la reserva
        int numeroPlazas = GestorIO.getInt("Introduce el número de plazas que deseas reservar:");

        // Realizar la reserva
        Reserva reserva = new Reserva(usuarioActivo, numeroPlazas);
        viajeSeleccionado.añadirReserva(reserva);

        // Mostrar información de la reserva
        GestorIO.print("Reserva realizada con éxito:");
        GestorIO.print("Viaje: " + viajeSeleccionado.getRuta());
        GestorIO.print("Número de plazas reservadas: " + numeroPlazas);
        GestorIO.print("Datos del usuario: " + usuarioActivo.toString());
    }






    public Usuario getUsuario() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    private void mostrarOpcionesViajes() {
        GestorIO.print("1.Viaje Estándar");
        GestorIO.print("2.Viaje Cancelable");
        GestorIO.print("3.Viaje Exclusivo");
        GestorIO.print("4.Viaje Flexible");
    }
}
