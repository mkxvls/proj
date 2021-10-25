
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
        super(nombres,apellidos,rut,direccion);
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

    @Override
    public boolean isCoordenada() {
        return this.getDireccion().isCoordenada();
    }
    
}
