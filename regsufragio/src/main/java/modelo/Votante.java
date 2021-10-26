package modelo;


public class Votante extends Persona {

    public Votante(String nombres, String apellidos, String rut, String direccion,String partido) {
        super(nombres,apellidos,rut,direccion,partido);
    
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

    @Override
    public boolean isCoordenada() {
        return this.getDireccion().isCoordenada();
    }
    
}
