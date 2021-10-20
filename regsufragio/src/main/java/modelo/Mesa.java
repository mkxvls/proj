package modelo;

import java.util.List;

/**
 *  clase Mesa, con coleccion de personas(sufragantes y vocales)
 * @author Maximiliano Valencia Saez
 */
public class Mesa {
    private static final int CAPMAX = 50;
    private int capacidad;
    private int numero;
    private List<Vocal> vocales;
    private List<Apoderade> apoderades;
    private List<Votante> votantes;
    
    public Mesa(){
        this.capacidad = 50;
    }

    public void agregarPersona(Votante persona){
       if ( !(capacidad >= CAPMAX)){
           this.votantes.add(persona);
       }
    }

    public List<Vocal> getVocales() {
        return vocales;
    }

    public void setVocales(List<Vocal> vocales) {
        this.vocales = vocales;
    }

    public List<Apoderade> getApoderades() {
        return apoderades;
    }

    public void setApoderades(List<Apoderade> apoderades) {
        this.apoderades = apoderades;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Votante> getVotantes() {
        return votantes;
    }

    public void setVotantes(List<Votante> votantes) {
        this.votantes = votantes;
    }
    
}
