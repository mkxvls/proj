package modelo;

import java.util.List;
import java.util.Map;

/**
 * clase Sede, con la coleccion de mesas
 * @author Maximiliano Valencia Saez
 */
public class Sede implements Coordenable{
    private Direccion direccion;
    private Map<Persona,String> personasxRut;
    private List<Mesa> mesas;
    
    public Sede(){
        
    }
    
    @Override
    public Double[] getCoords() {
    
        return null;
    }


}
