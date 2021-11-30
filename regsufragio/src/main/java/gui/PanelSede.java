package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class PanelSede extends JPanel implements Gui {
    private JLabel titulo;
    private JLabel lNombre;
    private JTextField tfNombre;
    private JLabel lCalle;
    private JTextField tfCalle;
    private JLabel lNumero;
    private JTextField tfNumero;
    private JLabel lCiudad;
    private JTextField tfCiudad;
    
    public PanelSede(){
        this.setLayout(null);
        this.setSize(SIZE_X, SIZE_Y);
        
        int pad = 1;
        int mult = 0;
        
        titulo = new JLabel("Agregar Sede");
        titulo.setBounds(X*2, ( (pad+H) * mult++)  ,L*2,H);
        this.add(titulo);
       
        lNombre = new JLabel("Nombre Sede:");
        lNombre.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(lNombre);
        
        tfNombre = new JTextField();
        tfNombre.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(tfNombre);
        
        lCiudad = new JLabel("Ciudad:");
        lCiudad.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(lCiudad);
        
        tfCiudad = new JTextField();
        tfCiudad.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(tfCiudad);
        
        lCalle = new JLabel("Calle:");
        lCalle.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(lCalle);
        
        tfCalle = new JTextField();
        tfCalle.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(tfCalle);
        
        lNumero = new JLabel("Numero:");
        lNumero.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(lNumero);
        
        tfNumero = new JTextField();
        tfNumero.setBounds(X, Y + ( (pad+H) * mult++)  ,L*2,H);
        this.add(tfNumero);
        
    }
    
    public String[] getFields(){
        return new String[] {tfNombre.getText(), tfCalle.getText()+" "+ tfNumero.getText() + ","+tfCiudad.getText() };
    }
    
}
