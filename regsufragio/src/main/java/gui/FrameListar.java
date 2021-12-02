package gui;

import static gui.Gui.H;
import static gui.Gui.OP_VOLVER;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class FrameListar extends JFrame implements Gui{
    private ActionListener listener;
    private List<String[]> sedes;
    public FrameListar(List sedes,ActionListener listener){
        super("Listar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SIZE_X,  SIZE_Y);
        this.setResizable(false);
        this.setLayout(null);
        this.listener = listener;
        this.sedes = sedes;
        listarSedes();
        botonVolver();
        this.setLocationRelativeTo(null);
    }
    
    
    private void botonVolver(){
        Rectangle bounds = this.getBounds();
        JButton bVolver = new JButton("<");
        bVolver.setActionCommand(OP_VOLVER);
        bVolver.addActionListener(this.listener);
        bVolver.setBounds(10, bounds.height - 10-(H+35)  , H+20 , H);
        this.add(bVolver);
    }
 
    
    @Override
    public Dimension getPreferredSize() {
        return this.getSize();
    }

    private void listarSedes() {
        JLabel titulo = new JLabel("Sedes:");
        titulo.setBounds(X,Y/2 ,L,H);
        this.add(titulo);
        int mult = 1;
        int idx = 1;
        for(String[] sede : sedes){
            JLabel nombreSede = new JLabel(idx + ".- "+sede[0]);
            nombreSede.setBounds(X+H,  H * mult  ,L*3,H);
            this.add(nombreSede);
            armarBotonVer(H*mult++,idx++);
        }
    }
    
    private void armarBotonVer(int y,int idx){
        JButton botonVer = new JButton("Ver");
        botonVer.setBounds(X-10-H,y,H*2,H);
        botonVer.addActionListener(listener);
        botonVer.setActionCommand(Gui.OP_VER+"-"+idx);
        this.add(botonVer);
    }
    
    public void mostrarDatosSede(List<String[]> datosSede, int nroSede){
        this.getContentPane().removeAll();
        
        JLabel nombreSede = new JLabel(sedes.get(nroSede)[0] +":");
        nombreSede.setBounds(X,Y/2 ,L*3,H);
        this.add(nombreSede);
        
        int mult = 1;
        int idx = 1;
        for(String[] pers : datosSede){
            JLabel label = new JLabel(idx++ + ".-"+ pers[0]+ " , " +pers[1]+ " , " +pers[2]+ " , " +pers[3]+ " , " +pers[4]);
            label.setBounds(5, H*mult++, L*6, H);
            this.add(label);
        }
        this.setSize((int) (L*6),H*mult+85);
        
        botonVolver();
        
        this.getContentPane().repaint();
        this.pack();
        
    }
}
