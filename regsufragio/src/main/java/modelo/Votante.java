package modelo;


public class Votante extends Persona {

    public Votante(String nombres, String apellidos, String rut, String direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public String getTipo() {
        return Persona.VOTANTE;
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
