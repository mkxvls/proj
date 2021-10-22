package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    private static final String keyAPI="&key=AIzaSyD1NHmgiisgmLJ6i6owEXtCVcsNCqDeTxQ";
    private static final String URLbase="https://maps.googleapis.com/maps/api/geocode/json?address=";
    
    private Distrito distrito;
    private Consola consola;
    public Control() throws IOException{
        //pruebita();
        this.distrito = new Distrito();
        this.consola = new Consola();
        cargar();
        run();
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
                                                        jMesa.getInt("numero"));
                    }
                }
            }
            this.distrito.agregarSede(sede);
        }
    }
    private void run() throws IOException{
        Opcion op = null;
        boolean flag = true;
        
        String input="";
        while(flag){
            input ="";
            consola.setOutput("");
            op = consola.menu();
            input = consola.getInput();
            switch(op){
                case AGREGARSEDE:
                    this.distrito.agregarSede(input);
                    break;
                case AGREGARMESA:
                    this.distrito.agregarMesa(input);
                    break;
                case AGREGARPERSONA:
                    this.distrito.agregarMesa(input);
                    break;
                case MOSTRARSEDES:
                    consola.setOutput(this.distrito.mostrarSedes());
                    break;
                case MOSTRARSEDESYPERSONAS:
                    consola.setOutput(this.distrito.mostrarSedesYPersonas());
                    break;
                case SALIR: flag = false; break;
                default:
                    
            }
        }  
    }
    
    public void pruebita() throws MalformedURLException, IOException{
        Direccion addr = new Direccion("2950","Brasil","Valparaiso");
        
        String urlquery = URLbase + addr.getNumero()+"+"+addr.getCalle() + "," + addr.getCiudad() + keyAPI;
//System.out.println(urlquery);
        URL url = new URL(urlquery);
        URLConnection conn = url.openConnection();
        String input;
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            input = "";
            while((inputLine = stream.readLine()) != null){
                input=input+inputLine;
            }
        }
        JSONObject json = new JSONObject(input).getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
//System.out.println(json.toString());
        double lng = json.getDouble("lng");
        double lat = json.getDouble("lat");
        System.out.println(lng +" "+lat);
    }
    
}
