package main;


import java.io.BufferedReader;
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
    
    public Control() throws IOException{
        //pruebita();
        this.distrito = new Distrito();
        cargar(this.distrito);
        run();
        
    }
   
    private void cargar(Distrito distrito){
        //TODO
    }
    
    private void run() throws IOException{
        Opcion op = null;
        boolean flag = true;
        Consola consola = new Consola();
        
        String input="";
        while(flag){
            op = consola.menu();
            input = consola.getInput();
            switch(op){
                case AGREGARSEDE: break;
                case AGREGARMESA: break;
                case AGREGARPERSONA: break;
                case MOSTRARSEDES: break;
                case MOSTRARSEDESYPERSONAS: break;
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
