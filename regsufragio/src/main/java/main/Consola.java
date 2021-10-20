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
    private String output;
    private BufferedReader lector;
    
    
    public Consola(){
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        this.input = "" ;
        this.output = "" ;
        ayuda();
        
    }

    public Opcion menu() throws IOException {
        boolean flag = true;
        String opcion;
        while(flag){
            limpiarInput();
            textoPrimerMenu();
            opcion = lector.readLine();
            switch(opcion){
                case"1":
                        switch(agregar()){
                            case 1 : return Opcion.AGREGARSEDE;
                            case 2 : return Opcion.AGREGARMESA;
                            case 3 : return Opcion.AGREGARPERSONA;
                        }
                break;
                case"2":
                        switch(mostrar()){
                            case 1 : return Opcion.MOSTRARSEDES;
                            case 2 : return Opcion.MOSTRARSEDESYPERSONAS;
                        }
                break;
                case "3":
                    switch(editar()){
                            case 1 : return null;
                            case 2 : return null;
                            case 3 : return null;
                        }
                break;
                case "4":
                break;
                case "5":
                break;
                case "6":
                break;
                case "7":
                break;
                case "8":
                break;
                case "9":
                break;
                case "10":
                break;
            }
        }
        return Opcion.SKIP;
    }
    
    private int mostrar() throws IOException{
        System.out.println("**** MENU MOSTRAR****");
        System.out.println("1.- mostrar solo sedes");
        System.out.println("2.- mostrar sedes y las personas que deben asistir las mismas ");
        switch(this.lector.readLine()){
            case "1": return 1;
            case "2": return 2;
        }
        return -1;
    }
    
    private int editar(){
        //TODO
        return -1;
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
    
    
    //AGREGAR
    private void addMesa(){
        System.out.println("**** mesa agregada ****");
    }
    private void addPersona() throws IOException{
        System.out.println("**** Datos Persona *****");
        System.out.println("Nombres:");
        armarInput(this.lector.readLine());
        System.out.println("Apellidos");
        armarInput(this.lector.readLine());
        System.out.println("Direccion, de forma \\NUMERO CALLE,CIUDAD\\  ");
        armarInput(this.lector.readLine());
    }
    
    private void addSede() throws IOException{
        System.out.println("*** Datos Sede***");
        System.out.println("nombre:");
        armarInput(this.lector.readLine());
        System.out.println("Direccion, de forma \\NUMERO CALLE,CIUDAD\\  ");
        armarInput(this.lector.readLine());
    }
    
    private int agregar() throws IOException {
        System.out.println("***MENU AGREGAR***");
        System.out.println("¿que desea agreagr?");
        System.out.println("1.- sede");
        System.out.println("2.- mesa");
        System.out.println("3.- persona");
        String opcion = lector.readLine();
        switch(opcion){
            case "1" : addSede();return 1;
            case "2" : addMesa();return 2;
            case "3" : addPersona(); return 3;
        }
        return -1;
    }

    //Getters y setters
    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }
    private void limpiarInput(){
        this.input="";
    }
    private void armarInput(String nuevo ){
        this.input = this.input + nuevo + "%";
    }
    private void ayuda(){
        System.out.println("manejo por consola\nIngrese con \"enter\"el numero de las opciones para seleccionar");
    }
}
