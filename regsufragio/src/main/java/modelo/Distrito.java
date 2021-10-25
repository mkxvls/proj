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
    
    
    public void coordenar() {
            for(Map.Entry<String,Sede> entry : this.sedes.entrySet()){ 
                Coordenable direccionSede = (Coordenable) entry.getValue(); //USO DE INTERFAZ EN CONTEXTO
                if(!direccionSede.isCoordenada()){
                    direccionSede.setCoords();
                }
            }
            for(Map.Entry<String,Persona> entry : this.personasxRut.entrySet()){
                Coordenable direccionPersona = (Coordenable) entry.getValue();
                if(!direccionPersona.isCoordenada()){
                    direccionPersona.setCoords();
                }
            }
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
        agregarPersona("nombre4","apellido4","11.222.333.-4","Mirador 110, Villa Alemana",Persona.VOTANTE);
        
    }
    
    
    public void agregarSede(Sede sede) {
        this.sedes.put(sede.getNombre(), sede);
    }
    
    //SOBREESCRITURA
    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo) {
        switch(tipo){
            case Persona.VOCAL -> this.personasxRut.put(rut,new Vocal(nombres,apellidos,rut,direccion));
            case Persona.VOTANTE -> this.personasxRut.put(rut,new Votante(nombres,apellidos,rut,direccion));
            case Persona.APODERADE -> this.personasxRut.put(rut,new Apoderade(nombres,apellidos,rut,direccion));
        }
    }

    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo, String nombreSede) {
        agregarPersona(nombres,apellidos,rut,direccion,tipo);
        switch(tipo){
            case Persona.VOCAL : 
                Vocal vocal = new Vocal(nombres,apellidos,rut,direccion);
                this.personasxSede.put(nombreSede, vocal);
                this.sedes.get(nombreSede).agregarPersona(vocal);
            case Persona.VOTANTE :
                Votante votante = new Votante(nombres,apellidos,rut,direccion);
                this.personasxSede.put(nombreSede,votante);
                this.sedes.get(nombreSede).agregarPersona(votante);
            case Persona.APODERADE : 
                Apoderade apoderade = new Apoderade(nombres,apellidos,rut,direccion);
                this.personasxSede.put(nombreSede, apoderade);
                this.sedes.get(nombreSede).agregarPersona(apoderade);
        }
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


}
