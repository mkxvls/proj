package modelo;

/**
 * Persona abstracta
 * @author Maximiliano Valencia Saez
 */
public abstract class Persona implements Coordenable {
    private String rut;
    private String Nombres;
    private String Apellidos;
    private String idMesa;
    private Direccion direccion;
    
    public Persona(){
        
    }
    
    /**
     *
     * @return booleano de si es vocal
     */
    public abstract boolean esVocal();
    
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
    }
    
}
