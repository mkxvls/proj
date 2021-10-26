package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *  clase Mesa, con coleccion de personas(sufragantes y vocales)
 * @author Maximiliano Valencia Saez
 */
public class Mesa {
    private static final int CAPMAX = 15;
    private int numero;
    private List<Vocal> vocales;
    private List<Apoderade> apoderades;
    private List<Persona> votantes;
    private boolean llena=false;
    /**
     *  constructor vacio
     */
    public Mesa(){
        this.vocales = new ArrayList<>();
        this.apoderades = new ArrayList<>();
        this.votantes = new ArrayList<>();
    }
    /**
    * constructor con paremetro numero mesa 
    * @param numeroMesa int
    */
    public Mesa(int numeroMesa) {
        this();
        this.numero=numeroMesa;
    }

    /**
     *  agrega la persona a la mesa
     * @param persona
     */
    public void agregarPersona(Persona persona){
       if ( !this.llena){
           this.votantes.add(persona);
           switch(persona.getTipo()){
               case Persona.APODERADE -> this.apoderades.add((Apoderade) persona);
               case Persona.VOCAL -> this.vocales.add((Vocal) persona);
           }
       }
       if(this.votantes.size()>=CAPMAX){
           this.llena=true;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Persona> getVotantes() {
        return votantes;
    }

    public void setVotantes(List<Persona> votantes) {
        this.votantes = votantes;
    }
    
    public List<Persona> getPersonas(){
        return this.votantes;
    }

    public boolean isLlena() {
        return llena;
    }

    public void setLlena(boolean llena) {
        this.llena = llena;
    }

    public String mostrarPersonas() {
        String output= null;
        int i=1;
        output="Personas:\n";
        for(Persona persona : this.votantes){
            output=output+"\t"+i+".-"+" "+ persona.toString() + "\n";
            i++;
        }
        
        return output;
    }
    
}
