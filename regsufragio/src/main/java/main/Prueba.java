package main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import modelo.Direccion;

import org.json.*;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Prueba {
    private String keyAPI="&key=AIzaSyD1NHmgiisgmLJ6i6owEXtCVcsNCqDeTxQ";
    private String URLbase="https://maps.googleapis.com/maps/api/geocode/json?address=";
    
    public Prueba() throws IOException{
        pruebita();
    }
   
    public void pruebita() throws MalformedURLException, IOException{
        Direccion addr = new Direccion("2950","Brasil","Valparaiso");
        String urlquery = URLbase + addr.getNumero()+"+"+addr.getCalle() + "," + addr.getCiudad() + keyAPI;
        System.out.println(urlquery);
        
        URL url = new URL(urlquery);
        URLConnection urlc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        
        String inputLine;
        String input = "";
        int cont = 0;
        while((inputLine = in.readLine()) != null){
            System.out.println(cont +".-"+inputLine);
            cont++;
            input = input + inputLine;
        }
        in.close();
        
        JSONObject json = new JSONObject(input).getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        
        System.out.println(json.toString());
    
        double lng = json.getDouble("lng");
        double lat = json.getDouble("lat");
        System.out.println(lng +" "+lat);
    }
    
}
