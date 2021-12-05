package main;

import gui.Gui;
import gui.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import modelo.Distrito;

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
        distrito = new Distrito();
        ControlArchivos.cargar(this.distrito);
        ventana = new Vista(this);
        ventana.listar(distrito.mostrarSedesGui());
    }
   
    public void normal() throws IOException, RutException, PersonaDuplicadaException{
        distrito = new Distrito();
        consola = new Consola();
        ControlArchivos.cargar(this.distrito);
        distrito.coordenar();
        run();
        ControlArchivos.guardar(this.distrito);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] op = e.getActionCommand().split("-");
        System.out.println(Arrays.toString(op));
        switch(op[0]){
            case Gui.OP_VER:
                ventana.listarSede(distrito.mostrarDatosSedeGui(op[1]), Integer.parseInt(op[1])-1 );
                break;
            case Gui.OP_SALIR:
                System.exit(0);
                break;
            case Gui.OP_AGREGAR:
                ventana.agregar();
                break;
            case Gui.OP_LISTAR:
                ventana.listar(distrito.mostrarSedesGui());
                break;
            case Gui.OP_VOLVER:
                ventana.volver();
                break;
            case Gui.OP_ADDSEDE:
                distrito.agregarSede(ventana.getFields(Gui.OP_ADDSEDE));
                distrito.coordenar();
                ventana.clearFields(Gui.OP_ADDSEDE);
                break;
            case Gui.OP_ADDPERS:
                distrito.agregarPersona(ventana.getFields(Gui.OP_ADDPERS));
                distrito.coordenar();
                ventana.clearFields(Gui.OP_ADDPERS);
                break;
        }
    }

    
    private void run() throws IOException, RutException, PersonaDuplicadaException{
        Opcion op;
        boolean flag = true;
        
        String[] input;
        while(flag){
            
            distrito.coordenar();
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
                    try{
                        if(!checkRut(input)){
                            throw new RutException("rut erroneo");
                        }
                        try{
                            if(checkPersona(input)){
                                throw new PersonaDuplicadaException("persona ya existe con ese rut");
                            }
                            distrito.agregarPersona(input);
                            distrito.coordenar();
                        }catch(PersonaDuplicadaException ex){
                            consola.display(ex.getMessage());
                        }
                    }catch(RutException ex){
                       consola.display(ex.getMessage());
                    }
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
    
    private void reportar() throws IOException {
        ControlArchivos.reportar(this.distrito);
    }

    public  boolean checkRut(String[] input) {
        String regex="^(\\d{2}\\.\\d{3}\\.\\d{3}-)([a-zA-Z]{1}$|\\d{1}$)";
        return input[2].matches(regex);
    }

    public boolean checkPersona(String[] input) {
        return distrito.checkPersona(input[2]);
    }
}
