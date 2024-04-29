package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.views.GestorIO;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sistema {

    private HashMap<String, Usuario> usuariosRegistrados;

    public Sistema() {
        usuariosRegistrados = new HashMap<>();
    }

    public void registrarUsuari() {
        Scanner scanner = new Scanner(System.in);

        GestorIO.print("Introduce el nombre de usuario:");
        String nombreUsuario = scanner.nextLine();

        GestorIO.print("Introduce la contraseña:");
        String contraseña = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña);
        usuariosRegistrados.put(nombreUsuario, nuevoUsuario);

        GestorIO.print("Usuario registrado correctamente.");
    }


}
