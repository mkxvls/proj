package modelo;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Apoderade extends Persona{
    private String partido;
    
    public Apoderade(){
        this.partido="No especifica";
    }

    public Apoderade(String nombres, String apellidos, String rut, String direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String getTipo() {
        return Persona.APODERADE;
    }
    @Override
    public Double[] getCoords() {
        return  this.getDireccion().getCoords();
    }
    @Override
    public void setCoords() {
        this.getDireccion().setCoords();
    }
    public String getPartido() {
        return partido;
    }
    public void setPartido(String partido) {
        this.partido = partido;
    }
}
