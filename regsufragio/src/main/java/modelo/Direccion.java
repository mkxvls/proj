package modelo;

/**
 * * @author Maximiliano Valencia Saez
 */
public class Direccion implements Coordenable{
    private String numero;
    private String calle;
    private String ciudad;
    private double lat; //latitud
    private double lng; //longitud
    
    public Direccion(String numero,String calle,String ciudad){
        this.numero=numero;
        this.calle=calle;
        this.ciudad=ciudad;
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
    
    }
    
}
