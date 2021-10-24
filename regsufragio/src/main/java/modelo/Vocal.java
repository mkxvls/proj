
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

    public Vocal(String nombres, String apellidos, String rut, String direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean asistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
    
    
    @Override
    public String getTipo() {
        return Persona.VOCAL;
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
