package modelo;

/**
 * Persona abstracta
 * @author Maximiliano Valencia Saez
 */
public abstract class Persona implements Coordenable {
    public static final String VOCAL = "Vocal";
    public static final String VOTANTE = "Votante";
    public static final String APODERADE = "Apoderade";
    
    private String rut;
    private String Nombres;
    private String Apellidos;
    private Direccion direccion;
    private boolean isAsignadoASede = false;
    private String mesa;
    public Persona(){
        
    }
    
    public Persona(String nombre,String apellido,String rut,String direccion){
        this.Nombres=nombre;
        this.Apellidos=apellido;
        this.rut=rut;
        this.direccion = new Direccion(direccion);
        
    }

    
    public abstract String getTipo();
    
    @Override
    public String getDireccionString(){
        return this.direccion.getDireccionString();
    }
    
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean isIsAsignadoASede() {
        return isAsignadoASede;
    }

    public void setIsAsignadoASede(boolean isAsignadoASede) {
        this.isAsignadoASede = isAsignadoASede;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }
    
    
}
