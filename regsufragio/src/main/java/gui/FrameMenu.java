package gui;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author max
 */
public class FrameMenu extends JFrame implements Gui{
    private JButton agregar;
    private JButton listar;
    private JButton salir;
    private int pad = 5;
    
    public FrameMenu(ActionListener v){
        super("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE_X/2 ,  SIZE_Y/2);
        this.setResizable(false);
    
        this.setLayout(null);
        
        agregar = new JButton("Agregar");
        agregar.setBounds(X , Y + pad , L , H);
        agregar.addActionListener(v);
        agregar.setActionCommand(OP_AGREGAR);
        this.add(agregar);
        
        listar = new JButton("Listar");
        listar.setBounds(X , Y + H + pad*2 , L , H);
        listar.addActionListener(v);
        listar.setActionCommand(OP_LISTAR);
        this.add(listar);
        
        salir = new JButton("Salir");
        salir.setBounds(X,Y+H*2+pad*3,L,H);
        salir.addActionListener(v);
        salir.setActionCommand(OP_SALIR);
        this.add(salir);
        
        this.setLocationRelativeTo(null);
    
    }

}
