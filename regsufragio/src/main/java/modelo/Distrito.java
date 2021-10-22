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
    private Map<Persona,String> personasxRut;
    private Map<Persona,String> personasxSede;
    private Map<Sede,String> sedes;
    
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
    public Map<Persona, String> getPersonasxRut() {
        return personasxRut;
    }

    public void setPersonasxRut(Map<Persona, String> personasxRut) {
        this.personasxRut = personasxRut;
    }

    public Map<Persona, String> getPersonasxSede() {
        return personasxSede;
    }

    public void setPersonasxSede(Map<Persona, String> personaxSede) {
        this.personasxSede = personaxSede;
    }

    public Map<Sede, String> getSedes() {
        return sedes;
    }

    public void setSedes(Map<Sede, String> sedes) {
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
    
}
