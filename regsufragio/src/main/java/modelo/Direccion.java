package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *  Clase direccion
 * se encarga de todo lo de una direccion, pasandole la direccion como string o parametros sueltos
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
    private boolean coordenada=false;

    /**
     *  constructor parametros sueltos
     * @param numero numero de la calle
     * @param calle nombre de la calle
     * @param ciudad ciudad donde se ubica
     */
    public Direccion(String numero,String calle,String ciudad) {
        this.numero = numero;
        this.calle = calle;
        this.ciudad = ciudad;
    
    }

    /**
     *  contructor string CALLE NUMERO,CIUDAD
     * @param direccionSede CALLE NUMERO,CIUDAD
     */
    public Direccion(String direccionSede) {
        String[] tokens = tokenizarDireccion(direccionSede);
        this.numero=tokens[0];
        this.calle=tokens[1];
        this.ciudad=tokens[2];
    }

    public Direccion() {
    }
    
    /**
     *  pide las coordenadas a la api geocoding de Google parsea el resultado
     * y lo guarda en las variables privadas
     * @throws MalformedURLException
     * @throws IOException 
     */
    private void fijarCoords() throws MalformedURLException, IOException{
        String urlquery = URLbase + this.getNumero()+"+"+this.getCalle() + "," + this.getCiudad() + keyAPI;
        URL url = new URL(urlquery.replaceAll("\\s","%"));
        URLConnection conn = url.openConnection();
        String input="";
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while((inputLine = stream.readLine()) != null){
                input=input+inputLine;
            }
        }
        JSONObject json = new JSONObject(input).getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
        this.lng = json.getDouble("lng");
        this.lat = json.getDouble("lat");
//        System.out.println(this.lat + "--" + this.lng + ":" + "ciudad:"+this.ciudad +"calle:"+this.calle +"numero:"+this.numero );
        this.coordenada=true;
    }
    /**
     * tokeniza una direccion CALLE NUMERO,CIUDAD en un arreglo de strings donde
     * [0] Numero
     * [1] Calle
     * [2] Ciudad
     * @param string
     * @return direccion arreglo de strings
     */
    private String[] tokenizarDireccion(String string){
            String[] direccion = string.split(",");
            String tokenCiudad = direccion[1];
            String tokenNumero = Integer.toString(getIntFromEnd(direccion[0]));
            String tokenCalle = direccion[0].replaceAll(" "+tokenNumero,"");
          return new String[]{tokenNumero,tokenCalle,tokenCiudad};
    }
    /**
     *  agarra los numeros al final de un string
     * @param string string a recortar
     * @return numeros al final de la string
     */
    private static int getIntFromEnd (String string) {
        for (int a = string.length()-1; a >=0; a--)
            try {
                int result = Integer.parseInt(string.substring(a,string.length()));
                if(a == 0) return result;
            } catch (Exception e) {
                if(a == string.length()-1) break;
                return Integer.parseInt(string.substring(a+1,string.length()));
            }
        return -1;
    }
    /**
     *  obtiene la direccion como un string CALLE NUMERO,CIUDAD
     * @return string
     */
    public String getDireccionString(){
        return this.calle+" "+this.numero+","+this.ciudad;
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
        return new Double[]{this.lat,this.lng};
    }

    @Override
    public void setCoords() {
        try {
            fijarCoords();
        } catch (IOException ex) {
            Logger.getLogger(Direccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isCoordenada() {
        return coordenada;
    }

    public void setCoordenada(boolean coordenada) {
        this.coordenada = coordenada;
    }
    
    
    
}
