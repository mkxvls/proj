package modelo;


public class Votante extends Persona {
    private static final String tipo = "Votante normal";
    
    
    
    @Override
    public String getTipo() {
        return this.tipo;
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
