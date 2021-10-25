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
    private Map<String,Persona> votantesxrut;
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
    public void asignarMesas(){
        if(this.mesas.isEmpty()){
            agregarMesa();
        }
        for(Mesa mesa : this.mesas){
            for(Map.Entry entry : this.votantesxrut.entrySet()){
                if( !((Persona) entry.getValue()).isTieneMesa() && !mesa.isLlena()){
                    mesa.agregarPersona((Persona) entry.getValue());
                }
            }
        }
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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
    
    public void agregarMesa(){
        agregarMesa( new Mesa(1) );
    }

    public void agregarMesa(int numeroMesa) {
        if(this.mesas.isEmpty()){
            agregarMesa(new Mesa());
        }
        else{
            this.mesas.add((new Mesa(numeroMesa)));
        }
    }

    public void agregarMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Persona> getVotantesxrut() {
        return votantesxrut;
    }

    public void setVotantesxrut(Map<String, Persona> votantesxrut) {
        this.votantesxrut = votantesxrut;
    }

    @Override
    public String getDireccionString() {
        return this.direccion.getDireccionString();
    }

    public void agregarPersona(Persona persona) {
        this.votantesxrut.put(persona.getRut(), persona);
    }

    @Override
    public boolean isCoordenada() {
        return this.getDireccion().isCoordenada();
    }
    
}
