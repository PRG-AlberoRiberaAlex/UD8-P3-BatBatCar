package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;
import es.progcipfpbatoi.views.ListadoViajesView;

import java.util.HashMap;
import java.util.List;

public class ViajesController {

    private Usuario usuario;
    private ViajesManager viajesManager;
    private HashMap<String, Usuario> usuariosRegistrados;
    private static final int MAX_INTENTOS = 3;
    private int intentosRestantes = MAX_INTENTOS;

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
    public void anyadirViaje() {
        //this.viajesManager.add(new Viaje());
        GestorIO.print("Viaje añadido con éxito");
    }
    public void registrarUsuario() {
        String nombreUsuario = GestorIO.getString("Introduce el nombre de usuario:");

        // Verificar si el usuario ya está registrado
        if (usuariosRegistrados.containsKey(nombreUsuario)) {
            int intentosRestantes = MAX_INTENTOS;
            Usuario usuario = usuariosRegistrados.get(nombreUsuario);

            do {
                String contraseña = GestorIO.getString("Introduce la contraseña:");

                // Verificar si la contraseña es correcta
                if (contraseña.equals(usuario.getContraseña())) {
                    GestorIO.print("¡Inicio de sesión exitoso! Bienvenido, " + nombreUsuario + ".");
                    return;
                } else {
                    intentosRestantes--;
                    GestorIO.print("Contraseña incorrecta. Intentos restantes: " + intentosRestantes);
                }

                if (intentosRestantes <= 0) {
                    GestorIO.print("Se han agotado los intentos. Saliendo del programa...");
                    System.exit(0);
                    return;
                }
            } while (true);
        } else {
            // El usuario no está registrado
            GestorIO.print("El usuario no está registrado.");

            // Solicitar una nueva contraseña para el nuevo usuario
            String contraseña = GestorIO.getString("Introduce la contraseña:");

            // Crear un nuevo usuario y agregarlo al mapa de usuarios registrados
            Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña);
            usuariosRegistrados.put(nombreUsuario, nuevoUsuario);
            GestorIO.print("Usuario registrado correctamente.");
            return;
        }
    }
}
