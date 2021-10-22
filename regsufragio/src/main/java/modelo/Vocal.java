
package modelo;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Vocal extends Persona {
    private boolean asistencia;
    
    public Vocal(){
        super();
        this.asistencia=false;
    }

    public boolean asistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
    
    
    @Override
    public String getTipo() {
        return "Vocal";
    }

    @Override
    public Double[] getCoords() {
        return this.getDireccion().getCoords();
    }

    @Override
    public void setCoords() {
        this.getDireccion().setCoords();
    }
    
}
