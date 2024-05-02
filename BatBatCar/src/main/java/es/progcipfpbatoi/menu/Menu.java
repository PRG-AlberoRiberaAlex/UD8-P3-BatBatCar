package es.progcipfpbatoi.menu;

import es.progcipfpbatoi.controller.ViajesController;
import es.progcipfpbatoi.model.entities.types.Reserva;
import es.progcipfpbatoi.model.entities.types.Viaje;
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
    private ViajesManager viajesManager;
    private Viaje viaje;
    private Reserva reserva;


    public Menu() {
        this.viajesController = new ViajesController();
        this.viajesManager = new ViajesManager();
    }

    public void iniciar(){
        GestorIO.print("BatBatCar");
        GestorIO.print("=========");

        int opcionSeleccionada;
        do {
            mostrarOpciones();
            opcionSeleccionada = solicitarOpcion();
            ejecutarOpcion(opcionSeleccionada);
        } while (opcionSeleccionada != 9);
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
        return GestorIO.getInt("Introduce la opción:",1,9);

    }
    
    private void ejecutarOpcion(int opcionSeleccionada) {
        
        // Implementar método para ejecutar la opción recibida
        switch (opcionSeleccionada) {
            case 1 -> viajesController.registrarUsuario();
            case 2 -> viajesController.listarViajes();
            case 3 -> viajesManager.add(viaje);
            case 4 ->viaje.getCancelado();
            case 5 ->reserva.getCodiReserva();
            case 9 ->GestorIO.print("Saliendo del programa...");
            default -> GestorIO.getInt("");
        }
    }

}
