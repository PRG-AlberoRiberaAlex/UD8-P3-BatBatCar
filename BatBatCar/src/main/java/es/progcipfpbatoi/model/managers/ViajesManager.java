package es.progcipfpbatoi.model.managers;

import es.progcipfpbatoi.model.entities.types.Viaje;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestor de viajes. Manejará la lista de los viajes tanto para almancenar nueva 
 * información sobre ella como para recuperar la totalidad o parte de la información
 * como también información relacionada con dicha lista.
 * @author batoi
 */

public class ViajesManager {

    private List<Viaje> viajes;

    public ViajesManager() {
        this.viajes = new ArrayList<>();
        init();
    }

    /**
     * Añade un nuevo viaje al repositorio
     * @param
     */
    public void add(Viaje viaje) {
        viajes.add(viaje);
    }
    
    /**
     * Cancela un viaje
     * @param viaje
     */
    public void cancel(Viaje viaje){
        throw new UnsupportedOperationException("Por implementar");
    }

    /**
     * Obtiene el número de viajes actualmente registrados
     * @return
     */
    public int getNumViajes() {
        throw new UnsupportedOperationException("Por implementar");
    }
    
    /**
     * Obtiene todos los viajes
     * @return
     */
    public List<Viaje> findAll() {
        return viajes;
    }

    private void init() {
        // añade a la colección "viajes" todos los viajes que creas necesario tener de inicio en tu sistema
        // this.add(new Viaje(....));
    }
}
