package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.model.entities.Usuario;
import es.progcipfpbatoi.model.entities.types.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
import es.progcipfpbatoi.model.managers.Sistema;
import es.progcipfpbatoi.model.managers.ViajesManager;
import es.progcipfpbatoi.views.GestorIO;

/**
 * Clase que gestiona el menú de opciones. A partir de esta clase se ejecutan
 * las diferentes opciones del menú (casos de uso).
 * @author batoi
 */

public class Menu {

    private static final int OPCION_SALIR = 9;
    
    private ViajesController viajesController;
    private Sistema sistema;
    private ViajesManager viajesManager;
    private Viaje viaje;
    private Reserva reserva;

    public Menu() {
        this.viajesController = new ViajesController();
        this.sistema = new Sistema();
        this.viajesManager = new ViajesManager();
    }

    public void iniciar(){

        GestorIO.print("BatBatCar");
        GestorIO.print("=========");
        
        mostrarOpciones();
        int opcionSeleccionada = solicitarOpcion();
        ejecutarOpcion(opcionSeleccionada);
        
    }

    private void mostrarOpciones() {
        GestorIO.print("1.Establecer usuario (login)");
        GestorIO.print("2.Mostrar todos los viajes");
        GestorIO.print("3.Añadir viaje");
        GestorIO.print("4.Cancelar viaje");
        GestorIO.print("5.Realizar reserva");
        GestorIO.print("6.Modificar reserva");
        GestorIO.print("7.Cancelar reserva");
        GestorIO.print("8.Buscar y realizar reserva");
        GestorIO.print("9.Salir");
        GestorIO.print("Seleccionar una opción [1-9]:");
    }
    
    private int solicitarOpcion() {
        // Implementar método para solicitar la opción al usuario
        GestorIO.print("Introduce la opción:");
        return 2;
    }
    
    private void ejecutarOpcion(int opcionSeleccionada) {
        
        // Implementar método para ejecutar la opción recibida
        switch (opcionSeleccionada) {
            case 1 -> sistema.registrarUsuari();
            case 2 -> viajesController.listarViajes();
            case 3 -> viajesManager.add(viaje);
            case 4 ->viaje.getCancelado();
            case 5 ->reserva.getCodiReserva();
            default -> throw new UnsupportedOperationException("Por implementar");
        }
    }

}
