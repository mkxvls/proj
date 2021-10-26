package modelo;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Apoderade extends Persona{
    
    /**
     *
     */
    public Apoderade(){
    }

    /**
     *  clase apoderade
     * @param nombres
     * @param apellidos
     * @param rut
     * @param direccion
     * @param partido
     */
    public Apoderade(String nombres, String apellidos, String rut, String direccion,String partido) {
        super(nombres,apellidos,rut,direccion,partido);
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

    @Override
    public boolean isCoordenada() {
        return this.getDireccion().isCoordenada();
    }
}
