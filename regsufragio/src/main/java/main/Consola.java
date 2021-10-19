package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * interaccion con consola
 * @author Maximiliano Valencia Saez
 */
public class Consola {
    private String input;
    private BufferedReader lector;
    
    
    public Consola(){
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        this.input = "";
        ayuda();
        
    }

    public Opcion menu() throws IOException {
        boolean flag = true;
        String opcion;
        
        while(flag){
            textoPrimerMenu();
            opcion = lector.readLine();
            switch(opcion){
                case"1" : agregar() ; break;
                
            }
        }
        return Opcion.SKIP;
    }
    
    private void agregar() {
        System.out.println("***MENU AGREGAR***");
        System.out.println("1.- distrito");
        System.out.println("2.- ");
    }
    
    private void textoPrimerMenu(){
        System.out.println("***********MENU CONSOLA***********");
        System.out.println("1.-agregar");
        System.out.println("2.-mostrar");
        System.out.println("3.-editar");
        System.out.println("4.-eliminar");
        System.out.println("5.-buscar");
        System.out.println("6.-reportar");
        System.out.println("7.-graficar");
        System.out.println("8.-ventana");
        System.out.println("9.-seleccionar por criterio");
        System.out.println("10.-subconjunto filtrado por criterio");
    };
    
    private void ayuda(){
        System.out.println("manejo por consola\nIngrese con \"enter\"el numero de las opciones para seleccionar");
    }
    
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

}
