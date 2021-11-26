package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author max
 */
public class FrameAgregar extends JFrame implements ActionListener{
    public FrameAgregar(Vista v) {
        super("Agregar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setLayout(null);
        
        JLabel label1 = new JLabel("¿Que desea agregar?");
        label1.setBounds(100, 10, 200, 40);
        this.add(label1);
        
        JButton bMesa = new JButton("Mesa");
        bMesa.setBounds(100, 50, 100, 40);
        bMesa.setActionCommand("Mesa");
        bMesa.addActionListener(this);
        this.add(bMesa);
        
        JButton bSede = new JButton("Sede");
        bSede.setBounds(100, 100, 100, 40);
        bSede.setActionCommand("Sede");
        bSede.addActionListener(this);
        this.add(bSede);
        
        JButton bPersona = new JButton("Persona");
        bPersona.setBounds(100, 150, 100, 40);
        bPersona.setActionCommand("Persona");
        bPersona.addActionListener(this);
        this.add(bPersona);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Mesa":
                System.out.println("agregar Mesa");
                break;
            case "Sede":
                System.out.println("agregar Sede");
                break;
            case "Persona":
                System.out.println("agregar Persona");
                break;
        }
    }
}
