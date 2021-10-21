
package main;

import java.io.IOException;
import modelo.Distrito;

/**
 *  Clase integradora
 * @author Maximiliano Valencia Saez
 */
public class Main {
    
    public static void main(String[] args) throws IOException{
        System.out.println("hola mundo!");
        new Control(new Distrito());
    }
   
}
