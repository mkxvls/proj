package modelo;

import java.util.List;

/**
 *  clase Mesa, con coleccion de personas(sufragantes y vocales)
 * @author Maximiliano Valencia Saez
 */
public class Mesa {
    private int capacidad;
    private int numero;
    
    private List<Persona> personas;
    
    public Mesa(){
    }

    public void agregarPersona(Persona persona){
        personas.add(persona);
    }
    
}
