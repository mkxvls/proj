package modelo;

import java.util.Map;
import java.util.TreeMap;

/**
 *  clase para el distrito , la coleccion de sedes y personas
 * 
 * @author Maximiliano Valencia Saez
 */
public class Distrito {
    private String numero;
    private Map<String,Persona> personasxRut;
    private Map<String,Persona> personasxSede;
    private Map<String,Sede> sedes;
    
    public Distrito(){
        this.numero="";
        this.personasxRut= new TreeMap<>();
        this.personasxSede = new TreeMap<>();
        this.sedes = new TreeMap<>();
        cargarPrueba();
    }
    private void cargarPrueba(){
        
        agregarSede(new Sede("PUCV","Brasil 2950,Valparaiso"));
        agregarSede(new Sede("LICEO JUANA ROSS DE EDWARDS","Argentina 871,Valparaiso"));
        agregarSede(new Sede("LICEO TECNOLOGICO VILLA ALEMANA","Valparaiso 133,Villa Alemana"));
        
        agregarPersona("Pedro Juan","Soto Perez","11.111.111-1","Condell 1546,Valparaiso",Persona.VOTANTE);
        agregarPersona("Juan Pedro","Soto Perez","11.111.111-2","Condell 1546,Valparaiso",Persona.VOTANTE);
        agregarPersona("cosme","fulanito","11.111.111-3","Labruyere 284,Valparaiso",Persona.VOTANTE);
        agregarPersona("Felipe","Sanchez","11.111.111-4","Alba 107,Valparaiso",Persona.VOTANTE);
        agregarPersona("Rachel","Sanchez","11.111.111-5","El Vergel 203,Valparaiso",Persona.VOTANTE);
        agregarPersona("nombre1","apellido1","22.222.222-1","Valparaíso 298, Valparaíso",Persona.VOTANTE);
        agregarPersona("nombre2","apellido2","22.222.222-2","Valparaíso 298, Valparaíso",Persona.VOTANTE);
        agregarPersona("nombre3","apellido3","22.222.222-3","Pedro Montt 2585, Valparaíso",Persona.VOTANTE);
    }
    
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Map<String, Persona> getPersonasxRut() {
        return personasxRut;
    }

    public void setPersonasxRut(Map<String, Persona> personasxRut) {
        this.personasxRut = personasxRut;
    }

    public Map<String, Persona> getPersonasxSede() {
        return personasxSede;
    }

    public void setPersonasxSede(Map<String, Persona> personasxSede) {
        this.personasxSede = personasxSede;
    }

    public Map<String, Sede> getSedes() {
        return sedes;
    }

    public void setSedes(Map<String, Sede> sedes) {
        this.sedes = sedes;
    }

    public void agregarSede(Sede sede) {
        this.sedes.put(sede.getNombre(), sede);
    }

    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo) {
        
        switch(tipo){
            case "Vocal" -> this.personasxRut.put(rut,new Vocal(nombres,apellidos,rut,direccion));
            case "Votante" -> this.personasxRut.put(rut,new Votante(nombres,apellidos,rut,direccion));
            case "Apoderade" -> this.personasxRut.put(rut,new Apoderade(nombres,apellidos,rut,direccion));
        }
    }

    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo, String nombreSede) {
        agregarPersona(nombres,apellidos,rut,direccion,tipo);
        switch(tipo){
            case "Vocal" -> this.personasxSede.put(nombreSede, new Vocal(nombres,apellidos,rut,direccion));
            case "Votante" -> this.personasxSede.put(nombreSede,new Votante(nombres,apellidos,rut,direccion));
            case "Apoderade" -> this.personasxSede.put(nombreSede, new Apoderade(nombres,apellidos,rut,direccion));
        }
    }
}
