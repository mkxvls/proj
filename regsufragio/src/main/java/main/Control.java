package main;

import gui.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import modelo.Distrito;
import modelo.Persona;

/**
 *  control de la aplicacion
 * @author Maximiliano Valencia Saez
 */
public class Control implements ActionListener{
    private Distrito distrito; //modelo
    private Consola consola;    //vista
    private Vista ventana;      //vista gui
    
    public Control() throws IOException{
        
    }
    
    public void testing(){
        ventana = new Vista(this);
        ventana.agregar();
    }
   
    public void normal() throws IOException{
        distrito = new Distrito();
        consola = new Consola();
        cargar();
        distrito.coordenar();
        run();
       // guardar();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case Vista.OP_AGREGAR:
                ventana.agregar();
                break;
            case Vista.OP_LISTAR:
                ventana.listar();
                break;
            case Vista.OP_VOLVER:
                ventana.volver();
                break;
            case Vista.OP_ADDSEDE:
                String[] a =  (ventana.getFields());
                System.out.println( a[0] );
                System.out.println(a[1]);
                break;
        }
    }

    
    private void run() throws IOException{
        Opcion op;
        boolean flag = true;
        
        String[] input;
        while(flag){
            input=null;
            consola.setOutput("");
            
            op = consola.menu();
            input = consola.getInput();
            
            switch(op){
                case AGREGARSEDE :
                    distrito.agregarSede(input);
                    break;
//                case AGREGARMESA -> this.distrito.agregarMesa(input);
                case AGREGARPERSONA : 
                    distrito.agregarPersona(input);
                    break;
                case MOSTRARSEDES : 
                    consola.display(distrito.mostrarSedes());
                    break;
                case MOSTRARSEDESYPERSONAS : 
                    consola.display(distrito.mostrarPersonasxSede());
                    break;
                case MOSTRARSEDESMESASPERSONAS :
                    consola.display(distrito.mostrarSedesMesasyPersonas());
                    break;
//                case  EDITPERS -> 
//                case EDITSEDE ->
                
                case REPORTAR : 
                    reportar();
                    break;
                //seleccionado por criterio
                case SEDEMASPERS : 
                    consola.display(distrito.getSedeMasPersonas());
                    break;
                case SEDEMENOSPERS : 
                    consola.display(distrito.getSedeMenosPersonas());
                    break;
                case PERSONAMASLEJOS : 
                    consola.display(distrito.getPersonaMasLejos());
                    break;
                    
                //filtrado por criterio
                case VOCCPART : 
                    consola.display(distrito.getVocalesConPartidos());
                    break;
                case VOCSPART :
                    consola.display(distrito.getVocalesSinPartidos());
                    break;
                case APOCPART : 
                    consola.display(distrito.getApoderadesConPartidos());
                    break;
                case APOSPART :
                    consola.display(distrito.getApoderadosSinPartidos());
                    break;
                case VOTMISMADIR : 
                    consola.display(distrito.getVotantesMismaDir());
                    break;
                case VENTANA :
                    this.ventana = new Vista(this);
                    this.ventana.menu();
                    break;
                case SALIR : 
                    flag = false;
                    break;
            }
        }  
    }
    
    private void guardar() throws IOException {
        consola.display("GUARDAR");
        ControlArchivos.guardar(this.distrito);
        consola.display("GUARDADO");
    }
    private void cargar() {
        consola.display("CARGAR");
        ControlArchivos.cargar(this.distrito);
    }

    private void reportar() throws IOException {
        //genera reporte csv de los datos del programa
        String out = "Nombres,apellidos,rut,direccion,Sede asignada,mesa\n";
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter("reporte.csv"))){
            for(Map.Entry entry : this.distrito.getPersonasxRut().entrySet() ){
                Persona persona = (Persona) entry.getValue();
                out = out + 
                    persona.getNombres()+","+
                    persona.getApellidos()+","+
                    persona.getRut()+","+
                    persona.getDireccionString()+","+
                    persona.getSede()+","+
                    persona.getMesa()+"\n" ;
            }
            escritor.write(out);
            consola.display("reporte generado con exito");
        }catch(Exception e){
            consola.display("no se pudo generar el reporte");
        }
        
    }

}
