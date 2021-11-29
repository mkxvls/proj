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
    ActionListener listenerPadre;
    public FrameAgregar(Vista v) {
        super("Agregar");
        listenerPadre = (ActionListener) v;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setLayout(null);
        
        panelPrincipal();
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Mesa":
                panelMesa();
                System.out.println("agregar Mesa");
                break;
            case "Sede":
                panelSede();
                System.out.println("agregar Sede");
                break;
            case "Persona":
                panelPersona();
                System.out.println("agregar Persona");
                break;
        }
    }

    private void panelPrincipal(){
        this.getContentPane().removeAll();
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
    
    }
    private void panelSede(){
        this.getContentPane().removeAll();
        JLabel label1 = new JLabel("Agregar Sede");
        label1.setBounds(100,10,200,40);
        this.add(label1);
        //meterle los componentes para que meta los datos de la sede
        //Nombre, ubicacion(direccion(calle,numero,ciudad))
        
        menuInferior();
    }

    private void panelMesa(){
        
        menuInferior();
    }
    private void panelPersona(){
    
        menuInferior();
    }
    
    
    private void menuInferior(){
        //boton para volver, donde lo dejo?
        JButton bVolver = new JButton("<-Volver");
        //TODO BOUNDS
        bVolver.setActionCommand("Volver");
        bVolver.addActionListener(this.listenerPadre);
        this.add(bVolver);
        //boton confirmar
        JButton bConfirmar = new JButton("Confirmar");
        //TODO BOUNDS
        bConfirmar.setActionCommand("Confirmar");
        bConfirmar.addActionListener(this);
        this.add(bConfirmar);

    }
}
