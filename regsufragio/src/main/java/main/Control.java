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
import org.json.*;

/**
 *  control de la aplicacion
 * @author Maximiliano Valencia Saez
 */
public class Control {
    private static final String keyAPI="&key=AIzaSyD1NHmgiisgmLJ6i6owEXtCVcsNCqDeTxQ";
    private static final String URLbase="https://maps.googleapis.com/maps/api/geocode/json?address=";
    
    private Distrito distrito;
    
    public Control(Distrito distrito) throws IOException{
        //pruebita();
        this.distrito = distrito;
        cargar(this.distrito);
        run();
    }
   
    private void cargar(Distrito distrito) throws FileNotFoundException, IOException{
        System.out.println("TESTING");
        try (BufferedReader lector = new BufferedReader(new FileReader("datos.json"))) {
            String linea = "";
            String datos = "";
            
            while( (linea = lector.readLine())!=null ){
                datos=datos+linea;
            }
            JSONObject job = new JSONObject(datos);
            JSONArray jarr = job.getJSONArray("Sedes");
             
            datos = "";
            String nombreSede;
            String direccionSede;
            int numeroMesa;
            for(Object o : jarr){
    System.out.println("Sede:");
                JSONObject jobi = (JSONObject) o;
                nombreSede = jobi.getString("Nombre");
    System.out.println(nombreSede);
                direccionSede = jobi.getString("Direccion");
    System.out.println(direccionSede);
                JSONArray jarrMesas = jobi.getJSONArray("Mesas");
    System.out.println(jarrMesas.toString());
                for(Object oMesa : jarrMesas){
                    JSONObject mesa = (JSONObject) oMesa;
                    numeroMesa = mesa.getInt("numero");
                    System.out.println(mesa.toString());
                    JSONArray jarrVotantes = mesa.getJSONArray("Personas");
                    for (Object oPersona : jarrVotantes){
                        JSONObject persona = (JSONObject) oPersona;
                        System.out.println(persona.toString());
                    }
                        
                }
            }   
        }
    }
    private void run() throws IOException{
        Opcion op = null;
        boolean flag = true;
        Consola consola = new Consola();
        
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
