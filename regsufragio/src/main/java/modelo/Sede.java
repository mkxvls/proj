package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * clase Sede, con la coleccion de mesas
 * @author Maximiliano Valencia Saez
 */
public class Sede implements Coordenable{
    private String nombre;
    private Direccion direccion;
    private Map<Persona,String> votantesxrut;
    private List<Mesa> mesas;
    
    public Sede(){
        this.direccion = new Direccion();
        this.votantesxrut = new TreeMap<>();
        this.mesas = new ArrayList<>();
    }
    public Sede(Direccion direccion){
        this.direccion = direccion;
        this.votantesxrut = new TreeMap<>();
        this.mesas = new ArrayList<>();
        
    }

    public Sede(String nombreSede, String direccionSede) {
        this.nombre = nombreSede;
        this.direccion = new Direccion(direccionSede);
        this.votantesxrut = new TreeMap<>();
        this.mesas = new ArrayList<>();
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Map<Persona, String> getPersonasxRut() {
        return votantesxrut;
    }

    public void setPersonasxRut(Map<Persona, String> personasxRut) {
        this.votantesxrut = personasxRut;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
    
    @Override
    public Double[] getCoords() {
        return this.direccion.getCoords();
    }

    @Override
    public void setCoords() {
        this.direccion.setCoords();
    }

    public void agregarMesa(int numeroMesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregarMesa(Mesa mesa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<Persona, String> getVotantesxrut() {
        return votantesxrut;
    }

    public void setVotantesxrut(Map<Persona, String> votantesxrut) {
        this.votantesxrut = votantesxrut;
    }

    @Override
    public String getDireccionString() {
        return this.direccion.getDireccionString();
    }
    
}
