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
    }
    
    /**
     * Con los datos cargados, a los que cumplan la interfaz coordenable se realiza lo principal del programa 
     * asignar las personas a las sedes mas cercanas 
     */
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
            
    }
    /**
     *  A la persona se le asigna la sede existente mas cercana a la direccion de la persona
     * @param persona 
     */
    private void asignarSede(Persona persona){
        Double[] coordsPersona = persona.getCoords();
        double distancia=0.0;
        double distanciaMin=100000;
        String sedeMasCerca="";
        for(Map.Entry entry : this.sedes.entrySet()){
            Double[] coordsSede = ((Coordenable) entry.getValue()).getCoords();
            distancia = distanciaCoords(coordsPersona,coordsSede);
            persona.setDistanciaSede(distancia);
            if(distanciaMin > distancia){
                distanciaMin = distancia;
                sedeMasCerca = ((Sede) entry.getValue()).getNombre();
            }
        }
        if(!sedeMasCerca.equals("")){
            this.sedes.get(sedeMasCerca).agregarPersona(persona);
            persona.setSede(sedeMasCerca);
//          System.out.println(persona.getNombres() + " asignado a " + sedeMasCerca);
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
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
                Math.cos(Math.toRadians(x[0])) * Math.cos(Math.toRadians(y[0]))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convertir a metros
        distance = Math.pow(distance, 2); 
        return Math.sqrt(distance);
    }

    
    public void cargarPrueba(){
        agregarSede(new Sede("PUCV","Brasil 2950,Valparaiso"));
        agregarSede(new Sede("LICEO JUANA ROSS DE EDWARDS","Argentina 871,Valparaiso"));
        agregarSede(new Sede("LICEO TECNOLOGICO VILLA ALEMANA","Valparaiso 133,Villa Alemana"));
        
        agregarPersona("Pedro Juan","Soto Perez","11.111.111-1","Condell 1546,Valparaiso",Persona.VOTANTE,"00");
        agregarPersona("Juan Pedro","Soto Perez","11.111.111-2","Condell 1546,Valparaiso",Persona.VOTANTE,"00");
        agregarPersona("cosme","fulanito","11.111.111-3","Labruyere 284,Valparaiso",Persona.VOTANTE,"PC");
        agregarPersona("Rachel","Sanchez","11.111.111-5","El Vergel 203,Valparaiso",Persona.VOTANTE,"PC");
        agregarPersona("nombre1","apellido1","22.222.222-1","Valparaíso 298,Valparaíso",Persona.VOTANTE,"RN");
        agregarPersona("nombre2","apellido2","22.222.222-2","Valparaíso 298,Valparaíso",Persona.VOTANTE,"RN");
        agregarPersona("nombre3","apellido3","22.222.222-3","Pedro Montt 2585,Valparaíso",Persona.VOTANTE,"PPD");
        agregarPersona("nombre4","apellido4","11.222.333.-4","Mirador 110,Villa Alemana",Persona.VOTANTE,"PPD");
        
    }
    
    /**
     * arma un string con los datos de las sedes cargadas
     * @return string datos a mostrar
     */
    public String mostrarSedes() {
        String output=null;
        output="SEDES\n";
        int count=1;
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output=output + count + ".- "+ sede.getNombre()+" ; "+ sede.getDireccionString()+"\n";
            count++;
        }
        
        return output;
    }
    /**
     * arma un string con los datos de las sedes y sus personas asignadas a estas
     * @return 
     */
    public String mostrarPersonasxSede(){
        String output = null;
        output="Personas por sede \n";
        int i=1;
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output=output + i + ".- "+ sede.getNombre()+" ; "+ sede.getDireccionString()+"\n";
            output=output + sede.mostrarPersonas();
            i++;
        }
        
        return output;
    }
    /**
     *  muestra las sedes , sus mesas y lsas personas por mesa
     * @return 
     */
    public String mostrarSedesMesasyPersonas() {
        String output=null;
        int i = 1;
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output=output + i + ".- "+ sede.getNombre()+" ; "+ sede.getDireccionString()+"\n";
            output=output + sede.mostrarMesasYPersonas();
            i++;
        }
        
        return output;
    }

    /**
     * agrega la sede al distrito
     * @param sede 
     */
    public void agregarSede(Sede sede) {
        this.sedes.put(sede.getNombre(), sede);
    }
    /**
     * agrega la sede al distrito
     * @param input arreglo donde [0] nombre sede
     *                            [1] direccion sede
     */
    public void agregarSede(String[] input) {
        agregarSede(new Sede(input[0],input[1]));
    }
    
    //SOBREESCRITURA
    /**
     *  agrega persona al distrito
     * @param nombres
     * @param apellidos
     * @param rut
     * @param direccion
     * @param tipo
     * @param partido 
     */
    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo,String partido) {
        switch(tipo){
            case Persona.VOCAL :
                this.personasxRut.put(rut,new Vocal(nombres,apellidos,rut,direccion,partido));
                break;
            case Persona.VOTANTE :
                this.personasxRut.put(rut,new Votante(nombres,apellidos,rut,direccion,partido));
                break;
            case Persona.APODERADE :
                this.personasxRut.put(rut,new Apoderade(nombres,apellidos,rut,direccion,partido));
                break;
        }
    }
    /**
     * agrega persona al distrito y a la sede de nombre nombreSede
     * @param nombres
     * @param apellidos
     * @param rut
     * @param direccion
     * @param tipo
     * @param nombreSede
     * @param partido 
     */
    public void agregarPersona(String nombres, String apellidos, String rut, String direccion, String tipo, String nombreSede,String partido) {
        agregarPersona(nombres,apellidos,rut,direccion,tipo,partido);
        switch(tipo){
            case Persona.VOCAL : 
                Vocal vocal = new Vocal(nombres,apellidos,rut,direccion,partido);
                this.personasxSede.put(nombreSede, vocal);
                this.sedes.get(nombreSede).agregarPersona(vocal);
            case Persona.VOTANTE :
                Votante votante = new Votante(nombres,apellidos,rut,direccion,partido);
                this.personasxSede.put(nombreSede,votante);
                this.sedes.get(nombreSede).agregarPersona(votante);
            case Persona.APODERADE : 
                Apoderade apoderade = new Apoderade(nombres,apellidos,rut,direccion,partido);
                this.personasxSede.put(nombreSede, apoderade);
                this.sedes.get(nombreSede).agregarPersona(apoderade);
        }
    }
    
    /**
     * agrega persona al distrito y a la sede
     * @param input arreglo donde arr[nombres,apellidos,rut,direccion,tipo,partido]
     */
    public void agregarPersona(String[] input) {
        agregarPersona(input[0],input[1],input[2],input[3],input[4],input[5]);
    }
    
    /**
     *
     * @return
     */
    public String getSedeMasPersonas() {
        String output=null;
        int count=0;
        String sedeMasPersonas="";
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            int aux = sede.getVotantesxrut().size();
            if(  aux > count  ){
                sedeMasPersonas= sede.getNombre();
                count=aux;
                output= sede.getNombre()+" ; "+ sede.getDireccionString()+"\n";
            }
        }

        return output;
    }

    /**
     *
     * @return
     */
    public String getSedeMenosPersonas() {
        String output=null;
        int count=1000;
        String sedeMenosPersonas="";
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            int aux = sede.getVotantesxrut().size();
            if(  aux < count  ){
                sedeMenosPersonas= sede.getNombre();
                count=aux;
                output= sede.getNombre()+" ; " + sede.getDireccionString() + "que tiene " +sede.getCantidadPersonas() +" personas" +"\n";
            }
        }

        return output;
    }

    /**
     *
     * @return las personas mas lejos de su sede
     */
    public String getPersonaMasLejos() {
        String output = null;
        double distancia = 0;
        Persona persona = null;
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            persona = sede.getPersonaMasLejos();
            if(distancia < persona.getDistanciaSede() ){
               distancia = persona.getDistanciaSede();
               output= "La persona mas lejana a su sede es: \n"+
                       persona.getNombres() + " " + persona.getApellidos() + ", RUT: " + persona.getRut() +
                       ". Su sede es :" + sede.getNombre() + " ubicada en : "+ sede.getDireccionString();
            }
        }
        return output;
    }

    /**
     *
     * @return String output de los vocales con partidos
     */
    public String getVocalesConPartidos() {
        String output=null;
        
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output = output + "Sede :"+ sede.getNombre() + "\n" +  sede.getVocalesConPartidos();
        }
        
        return output;
    }

    /**
     *
     * @return String output de los vocales sin partidos
     */
    public String getVocalesSinPartidos() {
        String output=null;
        
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output = output + "Sede :"+ sede.getNombre() + "\n" +  sede.getVocalesSinPartidos();
        }
        return output;
    }

   /**
     *
     * @return String output de los apoderados con partidos
     */
    public String getApoderadesConPartidos() {
        String output=null;
        
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output = output + "Sede :"+ sede.getNombre() + "\n" +  sede.getApoderadesConPartidos();
        }
        return output;
    }

    /**
     *
     * @return String output de los apoderados sin partidos
     */
    public String getApoderadosSinPartidos() {
        String output=null;
        
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output = output + "Sede :"+ sede.getNombre() + "\n" +  sede.getApoderadosSinPartidos();
        }
        return output;
    }

    /**
     *
     * @return Sting output de los votantes que posean la misma direccion
     */
    public String getVotantesMismaDir() {
        String output = null;
        for(Map.Entry entry : this.sedes.entrySet()){
            Sede sede = (Sede) entry.getValue();
            output = output + sede.getVotantesMismaDir();
        }
        return output;
    }
    
    //getters y setters
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
