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
    
    public void agregarSede(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarMesa(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String mostrarSedes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String mostrarSedesYPersonas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarSede(Sede sede) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarPersona(Persona votante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarPersona(String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarPersona(String string, String string0, String string1, String string2, String string3, int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
