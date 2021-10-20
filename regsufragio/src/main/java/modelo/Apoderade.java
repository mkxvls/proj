package modelo;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Apoderade extends Votante{
    private String partido;
    
    public Apoderade(){
        this.partido="No especifica";
    }
    @Override
    public String getTipo() {
        return "Apoderade";
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
