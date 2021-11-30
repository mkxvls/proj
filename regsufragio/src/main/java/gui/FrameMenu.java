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
        listar.setBounds(X , Y + H * 2 + pad , L , H);
        listar.addActionListener(v);
        listar.setActionCommand(OP_LISTAR);
        this.add(listar);
    
        this.setLocationRelativeTo(null);
    
    }

    @Override
    public String[] getFields() {
        return null;
    }

}
