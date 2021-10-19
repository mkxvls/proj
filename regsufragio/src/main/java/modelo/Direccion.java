package modelo;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Direccion  {
    private String numero;
    private String calle;
    private String ciudad;
    
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
    
    
}
