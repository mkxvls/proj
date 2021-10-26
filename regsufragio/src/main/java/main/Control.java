package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import modelo.Distrito;
import modelo.Mesa;
import modelo.Sede;
import modelo.Persona;
import org.json.*;

/**
 *  control de la aplicacion
 * @author Maximiliano Valencia Saez
 */
public class Control {
    
    private Distrito distrito; //modelo
    private Consola consola;    //vista
    
    public Control() throws IOException{
        distrito = new Distrito();
        consola = new Consola();
       // distrito.cargarPrueba();
       // distrito.coordenar();
        cargar();
        consola.display(distrito.mostrarSedes());
        consola.display(distrito.mostrarPersonasxSede());
        consola.display(distrito.mostrarSedesMesasyPersonas());
        //run();
        //guardar();
    }
   
    
    private void run() throws IOException{
        Opcion op;
        boolean flag = true;
        
        String[] input;
        while(flag){
            input=null;
            consola.setOutput("");
            op = consola.menu();
            input = consola.getInput();
            
            switch(op){
                case AGREGARSEDE ->  distrito.agregarSede(input);
//                case AGREGARMESA -> this.distrito.agregarMesa(input);
                case AGREGARPERSONA -> distrito.agregarPersona(input);
                case MOSTRARSEDES -> consola.display(distrito.mostrarSedes());
                case MOSTRARSEDESYPERSONAS -> consola.display(distrito.mostrarPersonasxSede());
                case MOSTRARSEDESMESASPERSONAS -> consola.display(distrito.mostrarSedesMesasyPersonas());
//                case  EDITPERS -> 
//                case EDITSEDE ->
                //seleccionado por criterio
                case SEDEMASPERS -> consola.display(distrito.getSedeMasPersonas());
                case SEDEMENOSPERS -> consola.display(distrito.getSedeMenosPersonas());
                case PERSONAMASLEJOS -> consola.display(distrito.getPersonaMasLejos());
                //filtrado por criterio
                case VOCCPART -> consola.display(distrito.getVocalesConPartidos());
                case VOCSPART -> consola.display(distrito.getVocalesSinPartidos());
                case APOCPART -> consola.display(distrito.getApoderadesConPartidos());
                case APOSPART -> consola.display(distrito.getApoderadosSinPartidos());
                case VOTMISMADIR -> consola.display(distrito.getVotantesMismaDir());
                case SALIR -> flag = false;
                default -> {
                }
                    
            }
        }  
    }
    private void guardar() throws IOException{
        consola.display("GUARDAR");
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter("datos.json"))){
            JSONObject datos = new JSONObject(); //crear objeto datos
            JSONArray arregloSedes = new JSONArray(); //crear  arreglosedes
            for(Map.Entry<String,Sede> entry : this.distrito.getSedes().entrySet()){ 
                JSONObject jsonSede = new JSONObject(); //crear objeto sedes, que va en el arreglosedes 
                Sede sede = entry.getValue();   //agarrar la sede de la entrada
                //lenar sede
                JSONArray arregloMesas = new JSONArray();   //crear arreglomesas
                for(Mesa mesa : sede.getMesas()){
                    JSONObject jsonMesa = new JSONObject();  //crear objeto mesa
                    //llenar mesa
                    JSONArray arregloPersonas = new JSONArray();    //crear arreglopersonas
                    for (Persona persona : mesa.getPersonas()){ //UTILIZACION DE CLASE ABSTRACTA EN CONTEXTO
                        JSONObject jsonPersona = new JSONObject();  //crear objeto persona
                        //lenar persona
                        jsonPersona.put("Nombre",persona.getNombres());
                        jsonPersona.put("Apellidos",persona.getApellidos());
                        jsonPersona.put("Rut", persona.getRut());
                        jsonPersona.put("Tipo",persona.getTipo());
                        jsonPersona.put("Direccion",persona.getDireccionString());
                        jsonPersona.put("Partido",persona.getPartido());
                        arregloPersonas.put(jsonPersona);   //colocar objeto persona en arregloPersonas
                    }
                    jsonMesa.put("Personas",arregloPersonas); //colocar arregloPersona en objeto mesa
                    jsonMesa.put("Numero",mesa.getNumero());
                    arregloMesas.put(jsonMesa); //colocar objeto mesa en arregloMesa
                }
                jsonSede.put("Mesas",arregloMesas); //colocar arreglomesas en objeto sede
                jsonSede.put("Nombre", sede.getNombre());   
                jsonSede.put("Direccion",sede.getDireccionString());
                arregloSedes.put(jsonSede); //colocar objetoSede en arregloSede
            }
            datos.put("Sedes",arregloSedes); //colocar arregloSedes en objeto datos
            escritor.write(datos.toString());
            escritor.close();
        }
        consola.display("GUARDADO");
    }
    
    private void cargar() throws FileNotFoundException, IOException{
        consola.display("CARGAR");
        
        
        try (BufferedReader lector = new BufferedReader(new FileReader("datos.json"))) {
            String linea = "";
            String datos = "";
            
            while( (linea = lector.readLine())!=null ){
                datos=datos+linea;
            }
            lector.close();
            
            JSONObject job = new JSONObject(datos); //armar objeto json con datos leidos
            JSONArray jarr = job.getJSONArray("Sedes"); // agarrar el arreglo Sedes desde el objeto json
             
            Sede sede = null;
            int i=1;
            for(Object oSede : jarr){
                //para cada sede en el arreglo hacer ...
                JSONObject jSede = (JSONObject) oSede;
                sede = new Sede(jSede.getString("Nombre"),jSede.getString("Direccion"));
                this.distrito.agregarSede(sede);
                JSONArray jarrMesas = jSede.getJSONArray("Mesas"); //agarrar el arreglo de mesas en la Sede
                for(Object oMesa : jarrMesas){
                    // para cada mesa del arreglo hacer ...
                    JSONObject jMesa = (JSONObject) oMesa;
                    sede.agregarMesa(jMesa.getInt("Numero"));
                    JSONArray jarrVotantes = jMesa.getJSONArray("Personas"); //agarrar el arreglo de personas en la mesa
                    for (Object oPersona : jarrVotantes){
                        // para cada persona del arreglo hacer ...
                        JSONObject jPersona = (JSONObject) oPersona;
                        System.out.println(i++);
                        this.distrito.agregarPersona(   jPersona.getString("Nombre"),
                                                        jPersona.getString("Apellidos"),
                                                        jPersona.getString("Rut"),
                                                        jPersona.getString("Direccion"),
                                                        jPersona.getString("Tipo"),
                                                        sede.getNombre(),
                                                        jPersona.getString("Partido"));
                    }
                }
            }
        }catch(Exception e){
            consola.display("no se encuentra el archivo con los datos");
        }
    }
}
