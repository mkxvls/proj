package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class Vista implements ActionListener {
    private FrameMenu menu;
    private FrameAgregar agregar;
    public Vista(){
        //menu = new FrameMenu(this);
        agregar = new FrameAgregar(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Boton Agregar":
                System.out.println("asd"); //DEBUG
                menu.setVisible(false);
                agregar = new FrameAgregar(this);
                break;
            case "Boton Listar":
                System.out.println("chao");
                break;
            case "Volver":
                if(agregar.isDisplayable()) agregar.dispose();
                menu.setVisible(true);
                break;
                    
        }
    }

    private void agregar() {
        
    }
   
}
