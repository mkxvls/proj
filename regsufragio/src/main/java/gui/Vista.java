package gui;  

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Vista {
    
    private FrameMenu menu;
    private FrameAgregar agregar;
    private FrameListar listar;
    private ActionListener listener;
    
    
    public Vista(ActionListener listener){
        this.listener=listener;
        //menu = new FrameMenu(this);
       // agregar = new FrameAgregar(listener);
        
    }
    public void listarSede(List datosSede,int idx){
        listar.mostrarDatosSede(datosSede,idx);
    }

    public void agregar() {
        if(menu != null && menu.isDisplayable()){
            menu.dispose();
        }
        if(listar != null && listar.isDisplayable()){
            listar.dispose();
        }
        agregar = new FrameAgregar(listener);
        agregar.setVisible(true);
    }

    public void listar(List listaSedes) {
        if(agregar != null && agregar.isDisplayable()){
            agregar.dispose();
        }
        if(menu != null && menu.isDisplayable()){
            menu.dispose();
        }
        listar = new FrameListar(listaSedes,listener);
        listar.setVisible(true);
    }

    public void menu() {
        if(menu != null && menu.isDisplayable()){
            menu.setVisible(true);
        }
        menu = new FrameMenu(listener);
        menu.setVisible(true);
    }

    public void volver() {
        if(agregar != null && agregar.isDisplayable()){
            agregar.dispose();
        }
        if(listar != null && listar.isDisplayable()){
            listar.dispose();
        }
        menu();
        
    }
    
    
    public String[] getFields(String op) {
        return agregar.getFields(op);
    }

    
    public void clearFields(String op) {
        agregar.clearFields(op);
        return;
    }

}
