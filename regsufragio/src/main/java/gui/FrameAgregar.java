package gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author max
 */
public class FrameAgregar extends JFrame implements ActionListener,Gui{

    private ActionListener listenerControl;
    private PanelSede pSede;
    private PanelPersona pPersona;
    public FrameAgregar(ActionListener control) {
        super("Agregar");
        listenerControl = (ActionListener) control;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE_X/2+50,SIZE_Y/2+50);
        this.setResizable(true);
        this.setLayout(null);
        
        panelPrincipal();
        
        this.setLocationRelativeTo(null);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case OP_MESA:
                panelMesa();
                break;
            case OP_SEDE:
                panelSede();
                break;
            case OP_PERS:
                panelPersona();
                break;
        }
    }

    private void panelPrincipal(){
        
        int pad = 5;
        
        this.getContentPane().removeAll();
        JLabel label1 = new JLabel("¿Que desea agregar?");
        label1.setBounds(X, 10 + Y + ( (pad+H) * 0)  ,L*2,H);
        this.add(label1);
        
        JButton bMesa = new JButton("Mesa");
        bMesa.setBounds(X,  Y + ((pad+H)* 1) ,L+25,H);
        bMesa.setActionCommand(OP_MESA);
        bMesa.addActionListener(this);
        this.add(bMesa);
        
        JButton bSede = new JButton("Sede");
        bSede.setBounds(X,  Y + ((pad+H) * 2) ,L+25,H);
        bSede.setActionCommand(OP_SEDE);
        bSede.addActionListener(this);
        this.add(bSede);
        
        JButton bPersona = new JButton("Persona");
        bPersona.setBounds(X, Y + ((pad+H) * 3) ,L+25,H);
        bPersona.setActionCommand(OP_PERS);
        bPersona.addActionListener(this);
        this.add(bPersona);
    
    }
    private void panelSede(){
        this.getContentPane().removeAll();
        pSede = new PanelSede();
        this.add(pSede);
        menuInferior(pSede);
        
        
        pSede.setVisible(true);
        this.setContentPane(pSede);
        
        // this.getContentPane().revalidate();
        this.getContentPane().repaint();
        this.pack();
    }

    private void panelMesa(){
        
    }
    private void panelPersona(){
        this.getContentPane().removeAll();
        pPersona = new PanelPersona();
        this.add(pPersona);
        menuInferior(pPersona);
        
        pPersona.setVisible(true);
        this.setContentPane(pPersona);
        
        this.getContentPane().repaint();
        this.pack();
    }
   

    private void menuInferior(PanelSede pSede) {
        Rectangle bounds = pSede.getBounds();
        JButton bVolver = new JButton("<");
        bVolver.setActionCommand(OP_VOLVER);
        bVolver.addActionListener(this.listenerControl);
        bVolver.setBounds(10, bounds.height - 10-H  , H+20 , H);
        pSede.add(bVolver);

        JButton bConf = new JButton("OK");
        bConf.setActionCommand(OP_ADDSEDE);
        bConf.addActionListener(this.listenerControl);
        bConf.setBounds( bounds.width - 10 -(H+30), bounds.height -10-H  , H+30 , H);
        pSede.add(bConf);
    }

    public String[] getFields(String op) {
        switch(op){
            case Gui.OP_ADDSEDE : return pSede.getFields();
            case Gui.OP_ADDPERS : return pPersona.getFields();
        }
        return null ;
    }

    private void menuInferior(PanelPersona pPersona) {
        Rectangle bounds = pPersona.getBounds();
        JButton bVolver = new JButton("<");
        bVolver.setActionCommand(OP_VOLVER);
        bVolver.addActionListener(this.listenerControl);
        bVolver.setBounds(10, bounds.height - 10-H  , H+20 , H);
        pPersona.add(bVolver);
        
        JButton bConf = new JButton("OK");
        bConf.setActionCommand(OP_ADDPERS);
        bConf.addActionListener(this.listenerControl);
        bConf.setBounds( bounds.width - 10 -(H+30), bounds.height -10-H  , H+30 , H);
        pPersona.add(bConf);
    }

    public void clearFields(String op) {
        switch(op){
            case Gui.OP_ADDSEDE :
                pSede.clearFields();
                break;
            case Gui.OP_ADDPERS :
                pPersona.clearFields();
                break;
        }
    }
}