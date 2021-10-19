package main;

/**
 * interaccion con consola
 * @author Maximiliano Valencia Saez
 */
public class Consola {
    private Opcion opcion;
    private String input="";
    
    public Consola(){
        
    }

    public void menu() {
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
    }

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    
}
