package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import org.json.JSONObject;

/**
 * * @author Maximiliano Valencia Saez
 */
public class Direccion implements Coordenable{
    private static final Base64.Decoder dec = Base64.getDecoder();
    private static final String keyApiBase64 ="JmtleT1BSXphU3lEMU5IbWdpaXNnbUxKNmk2b3dFWHRDVmNzTkNxRGVUeFE=";
    private static final String keyAPI =  new String(dec.decode(keyApiBase64));
    private static final String URLbase="https://maps.googleapis.com/maps/api/geocode/json?address=";
    
    
    private String numero;
    private String calle;
    private String ciudad;
    private double lat; //latitud
    private double lng; //longitud
    

    public Direccion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Direccion(String direccionSede) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    private void fijarCoords() throws MalformedURLException, IOException{
        //Direccion addr = new Direccion("2950","Brasil","Valparaiso");
        
        String urlquery = URLbase + this.getNumero()+"+"+this.getCalle() + "," + this.getCiudad() + keyAPI;
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
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public Double[] getCoords() {
        return null;
    }

    @Override
    public void setCoords() {
        fijarCoords();
    }
    
}
