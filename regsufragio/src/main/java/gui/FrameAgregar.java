package gui;

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
    
    public FrameAgregar(ActionListener control) {
        super("Agregar");
        listenerControl = (ActionListener) control;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE_X,SIZE_Y);
        this.setResizable(false);
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
        bVolver.setActionCommand(OP_VOLVER);
        bVolver.addActionListener(this.listenerControl);
        this.add(bVolver);
        //boton confirmar
        JButton bConfirmar = new JButton("Confirmar");
        //TODO BOUNDS
        bConfirmar.setActionCommand(OP_CONF);
        bConfirmar.addActionListener(this);
        this.add(bConfirmar);
    }

    private void menuInferior(PanelSede pSede) {
        JButton bVolver = new JButton("<");
        bVolver.setActionCommand(OP_VOLVER);
        bVolver.addActionListener(this.listenerControl);
        bVolver.setBounds(10, SIZE_Y - (Y+H)*2  , H+20 , H);
        pSede.add(bVolver);

        JButton bConf = new JButton("OK");
        bConf.setActionCommand(OP_ADDSEDE);
        bConf.addActionListener(this.listenerControl);
        bConf.setBounds(SIZE_X - (Y+H)*2 , SIZE_Y -(Y+H)*2  , H+30 , H);
        pSede.add(bConf);

    }

    @Override
    public String[] getFields() {
        
        return pSede.getFields();
    }
}
