package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Vista implements Gui  {
    
    
    private FrameMenu menu;
    private FrameAgregar agregar;
    private JFrame listar;
    
    private ActionListener listener;
    
    
    public Vista(ActionListener listener){
        this.listener=listener;
        //menu = new FrameMenu(this);
       // agregar = new FrameAgregar(listener);
        
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

    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    @Override
    public String[] getFields() {
        return agregar.getFields();
    }

}
