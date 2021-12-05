package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import modelo.Distrito;
import modelo.Mesa;
import modelo.Persona;
import modelo.Sede;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Maximiliano Valencia Saez
 */
public class ControlArchivos {
    private static final String ARCHIVO = "datos.json";
    
    public static void guardar(Distrito distrito) throws IOException {
       try(BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO))){
            JSONObject datos = new JSONObject(); //crear objeto datos
            JSONArray arregloSedes = new JSONArray(); //crear  arreglosedes
            for(Map.Entry<String,Sede> entry : distrito.getSedes().entrySet()){ 
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
    }
    
    public static void cargar(Distrito distrito){
        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO))) {
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
                distrito.agregarSede(sede);
                JSONArray jarrMesas = jSede.getJSONArray("Mesas"); //agarrar el arreglo de mesas en la Sede
                for(Object oMesa : jarrMesas){
                    // para cada mesa del arreglo hacer ...
                    JSONObject jMesa = (JSONObject) oMesa;
                    sede.agregarMesa(jMesa.getInt("Numero"));
                    JSONArray jarrVotantes = jMesa.getJSONArray("Personas"); //agarrar el arreglo de personas en la mesa
                    for (Object oPersona : jarrVotantes){
                        // para cada persona del arreglo hacer ...
                        JSONObject jPersona = (JSONObject) oPersona;
                        distrito.agregarPersona(jPersona.getString("Nombre"),
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
            
        }
    }
    public static void reportar(Distrito distrito){
        //genera reporte csv de los datos del programa
        String out = "Nombres,apellidos,rut,direccion,Sede asignada,mesa\n";
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter("reporte.csv"))){
            for(Map.Entry entry : distrito.getPersonasxRut().entrySet() ){
                Persona persona = (Persona) entry.getValue();
                out = out + 
                    persona.getNombres()+","+
                    persona.getApellidos()+","+
                    persona.getRut()+","+
                    persona.getDireccionString()+","+
                    persona.getSede()+","+
                    persona.getMesa()+"\n" ;
            }
            escritor.write(out);
            System.out.println("reporte generado con exito");
        }catch(Exception e){
            System.out.println("no se pudo generar el reporte");
        }
        
    }
}
