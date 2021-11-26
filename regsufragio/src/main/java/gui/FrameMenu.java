package gui;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author max
 */
public class FrameMenu extends JFrame{
    private JButton agregar;
    private JButton listar;
    
    public FrameMenu(Vista v){
        super("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 200);
    
        this.setLayout(null);
        
        agregar = new JButton("Agregar");
        agregar.setBounds(50,10,100,30);
        agregar.addActionListener(v);
        agregar.setActionCommand("Boton Agregar");
        this.add(agregar);
        
        listar = new JButton("Listar");
        listar.setBounds(50,50,100,30);
        listar.addActionListener(v);
        listar.setActionCommand("Boton Listar");
        this.add(listar);
    
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    
    }

}
