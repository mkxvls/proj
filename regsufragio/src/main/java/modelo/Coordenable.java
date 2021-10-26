package modelo;

/**
 *  Interfaz coordenable
 * @author max
 */
public interface Coordenable {

    /**
     * arreglo de coordenadas 
     * [0] latitud
     * [1] longitud                         
     * @return coords arreglo de coordenadas
     */
    public Double[] getCoords();

    /**
     *  metodo para fijar las coordenadas
     */
    public void setCoords();

    /**
     *
     * @return direccion direccion como String CALLE NUMERO,CIUDAD
     */
    public String getDireccionString();

    /**
     *
     * @return  si ya se le asigno coordenadas
     */
    public boolean isCoordenada();
}
