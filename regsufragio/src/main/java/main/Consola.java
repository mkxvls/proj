package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import modelo.Persona;

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
    
    /**
     * muestra por consola lo ingresado por parametro
     * @param display
     */
    public void display(String display) {
        System.out.println(display);
    }

    /**
     * funcion principal de la consola, bucle del menu y navegacion de este
     * @return Opcion opciones posibles del programa
     * @throws IOException
     */
    public Opcion menu() throws IOException {
        boolean flag = true;
        String opcion;
        while(flag){
            limpiarInput();
            textoPrimerMenu();
            opcion = lector.readLine();
            switch(opcion){
                case"0" : return Opcion.SALIR;
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
                            case 3 : return Opcion.MOSTRARSEDESMESASPERSONAS;
                        }
                break;
                case "3":
                    switch(editar()){
                            case 1 : return Opcion.EDITSEDE;
                            case 2 : return Opcion.EDITPERS;
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
                case "9": //selectxcriterio
                    switch(selectxcriterio()){
                        case 1: return Opcion.SEDEMASPERS;
                        case 2: return Opcion.SEDEMENOSPERS;
                        case 3: return Opcion.PERSONAMASLEJOS;
                         
                    }
                break;
                case "10": //subconjunto por criterio
                    switch(subconjunto()){
                        case 1: return Opcion.VOCCPART;
                        case 2: return Opcion.VOCSPART;
                        case 3: return Opcion.APOCPART;
                        case 4: return Opcion.APOSPART;
                        case 5: return Opcion.VOTMISMADIR;
                    }
                break;
            }
        }
        return Opcion.SKIP;
    }
    /**
     *  menu subconjunto filtrado por criterio
     * @return opcion seleccionada
     * @throws IOException 
     */
    private int subconjunto() throws IOException{
        System.out.println("*** MENU FILTRAR POR CRITERIO");
        System.out.println("¿Que desea buscar?");
        System.out.println("1.- vocales de las mesas con partidos");
        System.out.println("2.- vocales de las mesas sin partidos");
        System.out.println("3.- apoderados de las mesas con partidos");
        System.out.println("4.- apoderades de las mesas sin partidos");
        System.out.println("5.- votantes con la misma direccion");
        switch(this.lector.readLine()){
            case "1": return 1;
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
        }
        return -1;
    }
    /**
     *  menu seleccionar por criterio
     * @return opcion seleccionada
     * @throws IOException 
     */
    private int selectxcriterio() throws IOException{
        System.out.println("*** MENU SELECCIONAR POR CRITERIO ***");
        System.out.println("¿que desea buscar?");
        System.out.println("1.-Sede con menor cantidad de personas");
        System.out.println("2.-Sede con mayor cantidad de personas");
        System.out.println("3.-Persona mas lejos de su sede");
        switch(this.lector.readLine()){
            case "1": return 1;
            case "2": return 2;
            case "3": return 3;
        }
        return -1;
    }
    /**
     * menu mostrar por pantalla listado de elementos  
     * @return
     * @throws IOException 
     */
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
    /**
     *  menu edicion elemento
     * @return opcion
     * @throws IOException 
     */
    private int editar() throws IOException{
        System.out.println("*** MENU EDICION ***");
        System.out.println("¿Que desea editar?");
        System.out.println("1.- Sede");
        System.out.println("2.- Persona");
        switch(lector.readLine()){
            case "1" : return 1;
            case "2" : return 2;
        }
        return -1;
    }
    /**
     *  texto del menu principal
     */
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
        System.out.println("0.- Salir");
    };
    
    
    //AGREGAR
    private void addMesa(){
        System.out.println("**** mesa agregada ****");
    }
    /**
     *  menu agregar persona, hace los llamados para armar el input hacia Control
     * @throws IOException 
     */
    private void addPersona() throws IOException{
        System.out.println("**** Datos Persona *****");
        System.out.println("Nombres:");
        armarInput(this.lector.readLine());
        System.out.println("Apellidos");
        armarInput(this.lector.readLine());
        System.out.println("Rut formato 12.345.678-9");
        armarInput(this.lector.readLine());
        System.out.println("Direccion, de forma \\CALLE NUMERO,CIUDAD\\  ");
        armarInput(this.lector.readLine());
        System.out.println("tipo: 1.-Vocal 2.-Apoderade 3.-Votante normal");
        switch(lector.readLine()){
            case "1" : 
                armarInput(Persona.VOCAL);
                break;
            case "2" : 
                armarInput(Persona.APODERADE);
                break;
            case "3" : 
                armarInput(Persona.VOTANTE);
                break;
        }
        System.out.println("partido: sigla en mayusculas, si ninguno escriba \"00\",sin comillas");
        armarInput(lector.readLine());
    }
    /**
     *  menu agregar sede, hace los llamados para armar el input hacia Control
     * @throws IOException 
     */
    private void addSede() throws IOException{
        System.out.println("*** Datos Sede***");
        System.out.println("nombre:");
        armarInput(this.lector.readLine());
        System.out.println("Direccion, de forma \\CALLE NUMERO,CIUDAD\\  ");
        armarInput(this.lector.readLine());
        display(this.input);
    }
    
    /**
     *  menu agregar, navegacion de las opciones
     * @return opcion
     * @throws IOException 
     */
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
    public String[] getInput() {
        return input.split("%");
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
