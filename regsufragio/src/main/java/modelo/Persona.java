package modelo;

/**
 * Persona abstracta
 * @author Maximiliano Valencia Saez
 */
public abstract class Persona implements Coordenable {
    public static final String VOCAL = "Vocal";
    public static final String VOTANTE = "Votante";
    public static final String APODERADE = "Apoderade";
    public static final String SINPARTIDO = "00";
    
    private String rut;
    private String Nombres;
    private String Apellidos;
    private Direccion direccion;
    private boolean tieneSede = false;
    private boolean tieneMesa = false;
    private String sede;
    private String mesa;
    private String partido;
    private double distanciaSede;
    
    public Persona(){
        
    }
    
    /**
     *  constructor persona abstracta
     * @param nombre
     * @param apellido
     * @param rut
     * @param direccion
     * @param partido
     */
    public Persona(String nombre,String apellido,String rut,String direccion,String partido){
        this.Nombres=nombre;
        this.Apellidos=apellido;
        this.rut=rut;
        this.partido=partido;
        this.direccion = new Direccion(direccion);
        this.distanciaSede = 0.0;
    }

    @Override
    public String toString(){   
        return Nombres+","+Apellidos+","+rut+","+direccion.getDireccionString()+","+partido;
    }
    
    public abstract String getTipo();
    
    @Override
    public String getDireccionString(){
        return this.direccion.getDireccionString();
    }
    
    public String getPartido(){
        return this.partido;
    }
    public void setPartido(String partido){
        this.partido=partido;
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

    public boolean isTieneSede() {
        return tieneSede;
    }

    public void setTieneSede(boolean tieneSede) {
        this.tieneSede = tieneSede;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
        this.tieneMesa=true;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
        this.tieneSede=true;
    }

    public boolean isTieneMesa() {
        return tieneMesa;
    }

    public void setTieneMesa(boolean tieneMesa) {
        this.tieneMesa = tieneMesa;
    }

    public double getDistanciaSede() {
        return distanciaSede;
    }

    public void setDistanciaSede(double distanciaSede) {
        this.distanciaSede = distanciaSede;
    }
    
    
}
