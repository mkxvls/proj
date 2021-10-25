package modelo;

import java.util.ArrayList;
import java.util.List;
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
        coordenar();
    }
    
    
    public void coordenar() {
            List<Map.Entry> direcciones = new ArrayList<>();
            direcciones.addAll(this.personasxRut.entrySet());
            direcciones.addAll(this.sedes.entrySet());
            
            for(Map.Entry entry : direcciones){
                Coordenable direccion = (Coordenable) entry.getValue();
                if(!direccion.isCoordenada()){
                    direccion.setCoords();
                }
            }
            
            for(Map.Entry entry : this.personasxRut.entrySet()){
                Persona persona = (Persona) entry.getValue();
                if(!persona.isTieneSede()){
                   asignarSede(persona);
                }
            }
            for(Map.Entry entry : this.sedes.entrySet()){
                ((Sede) entry.getValue()).asignarMesas();
            }
            
    }
    
    private void asignarSede(Persona persona){
        Double[] coordsPersona = persona.getCoords();
        double distancia=0.0;
        double distanciaMin=100000;
        String sedeMasCerca="";
        for(Map.Entry entry : this.sedes.entrySet()){
            Double[] coordsSede = ((Coordenable) entry.getValue()).getCoords();
            distancia = distanciaCoords(coordsPersona,coordsSede);
            if(distanciaMin > distancia){
                distanciaMin = distancia;
                sedeMasCerca = ((Sede) entry.getValue()).getNombre();
            }
        }
        if(!sedeMasCerca.equals("")){
            this.sedes.get(sedeMasCerca).agregarPersona(persona);
            persona.setSede(sedeMasCerca);
            persona.setTieneSede(true);
            System.out.println(persona.getNombres() + " asignado a " + sedeMasCerca);
        }
    }
    
    /**
     * calcula distancia entre coordenadas usando la formula del semiverseno
     * 
        R = radio de la Tierra
        Δlat = lat2− lat1
        Δlong = long2− long1
        a = sin²(Δlat/2) + cos(lat1) · cos(lat2) · sin²(Δlong/2)
        c = 2 · atan2(√a, √(1−a))
        d = R · c
     * @param x coordenada 1
     * @param y coordenada 2
     * @return
     */
    public static double distanciaCoords(Double[] x,Double[] y){
        final int R = 6371; // Radio de la tierra
        double latDistance = Math.toRadians(y[0] - x[0]);
        double lonDistance = Math.toRadians(y[1] - x[1]);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(x[0])) * Math.cos(Math.toRadians(y[0]))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convertir a metros
        distance = Math.pow(distance, 2); 
        return Math.sqrt(distance);
    }

    
    private void cargarPrueba(){
        agregarSede(new Sede("PUCV","Brasil 2950,Valparaiso"));
        agregarSede(new Sede("LICEO JUANA ROSS DE EDWARDS","Argentina 871,Valparaiso"));
        agregarSede(new Sede("LICEO TECNOLOGICO VILLA ALEMANA","Valparaiso 133,Villa Alemana"));
        
        agregarPersona("Pedro Juan","Soto Perez","11.111.111-1","Condell 1546,Valparaiso",Persona.VOTANTE);
        agregarPersona("Juan Pedro","Soto Perez","11.111.111-2","Condell 1546,Valparaiso",Persona.VOTANTE);
        agregarPersona("cosme","fulanito","11.111.111-3","Labruyere 284,Valparaiso",Persona.VOTANTE);
        agregarPersona("Felipe","Sanchez","11.111.111-4","Alba 107,Valparaiso",Persona.VOTANTE); // este falla hmmm
        agregarPersona("Rachel","Sanchez","11.111.111-5","El Vergel 203,Valparaiso",Persona.VOTANTE);
        agregarPersona("nombre1","apellido1","22.222.222-1","Valparaíso 298,Valparaíso",Persona.VOTANTE);
        agregarPersona("nombre2","apellido2","22.222.222-2","Valparaíso 298,Valparaíso",Persona.VOTANTE);
        agregarPersona("nombre3","apellido3","22.222.222-3","Pedro Montt 2585,Valparaíso",Persona.VOTANTE);
        agregarPersona("nombre4","apellido4","11.222.333.-4","Mirador 110,Villa Alemana",Persona.VOTANTE);
        
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
