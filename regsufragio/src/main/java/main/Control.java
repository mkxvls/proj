package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;
import modelo.Direccion;
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
    
    private Distrito distrito;
    private Consola consola;
    public Control() throws IOException{
        this.distrito = new Distrito();
        this.consola = new Consola();
        //cargar();
        //run();
        //guardar();
    }
   
    private void guardar() throws IOException{
        consola.display("GUARDAR");
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter("datosa.json"))){
            JSONObject datos = new JSONObject();
            JSONArray arregloSedes = new JSONArray();
            for(Map.Entry<String,Sede> entry : this.distrito.getSedes().entrySet()){
                JSONObject jsonSede = new JSONObject();
                Sede sede = entry.getValue();
                jsonSede.put("Nombre", sede.getNombre());
                jsonSede.put("Direccion",sede.getDireccion());
                JSONArray arregloMesas = new JSONArray();
                for(Mesa mesa : sede.getMesas()){
                    JSONObject jsonMesa = new JSONObject();
                    jsonMesa.put("numero",mesa.getNumero());
                    JSONArray arregloPersonas = new JSONArray();
                    for (Persona persona : mesa.getPersonas()){
                        JSONObject jsonPersona = new JSONObject();
                        jsonPersona.put("Nombre",persona.getNombres());
                        jsonPersona.put("Apellidos",persona.getApellidos());
                        jsonPersona.put("rut", persona.getRut());
                        jsonPersona.put("tipo",persona.getTipo());
                        jsonPersona.put("Direccion",persona.getDireccionString());
                        arregloPersonas.put(jsonPersona);
                    }
                    arregloMesas.put(jsonMesa);
                }
                arregloSedes.put(jsonSede);
            }
            datos.put("Sedes",arregloSedes);
            //escritor.write(datos.toString());
            System.out.println(datos.toString());
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
             
            datos = "";
            Sede sede = null;
            for(Object oSede : jarr){
                //para cada sede en el arreglo hacer ...
                JSONObject jSede = (JSONObject) oSede;
                sede = new Sede(jSede.getString("Nombre"),jSede.getString("Direccion"));
                JSONArray jarrMesas = jSede.getJSONArray("Mesas"); //agarrar el arreglo de mesas en la Sede
                for(Object oMesa : jarrMesas){
                    // para cada mesa del arreglo hacer ...
                    JSONObject jMesa = (JSONObject) oMesa;
                    sede.agregarMesa(jMesa.getInt("numero"));
                    JSONArray jarrVotantes = jMesa.getJSONArray("Personas"); //agarrar el arreglo de personas en la mesa
                    for (Object oPersona : jarrVotantes){
                        // para cada persona del arreglo hacer ...
                        JSONObject jPersona = (JSONObject) oPersona;
                        this.distrito.agregarPersona(   jPersona.getString("Nombre"),
                                                        jPersona.getString("Apellidos"),
                                                        jPersona.getString("Direccion"),
                                                        jPersona.getString("rut"),
                                                        jPersona.getString("tipo"),
                                                        sede.getNombre() );
                    }
                }
            }
            this.distrito.agregarSede(sede);
        }
    }
    private void run() throws IOException{
        Opcion op;
        boolean flag = true;
        
        String input;
        while(flag){
            input="";
            consola.setOutput("");
            op = consola.menu();
            input = consola.getInput();
            /**
            switch(op){
                case AGREGARSEDE -> this.distrito.agregarSede(input);
                case AGREGARMESA -> this.distrito.agregarMesa(input);
                case AGREGARPERSONA -> this.distrito.agregarMesa(input);
                case MOSTRARSEDES -> consola.setOutput(this.distrito.mostrarSedes());
                case MOSTRARSEDESYPERSONAS -> consola.setOutput(this.distrito.mostrarSedesYPersonas());
                case SALIR -> flag = false;
                default -> {
                }
                    
            }
            **/
        }  
    }
    
    
    
}
