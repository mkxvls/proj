package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class PanelSede extends PanelAbs implements Gui {
    private JLabel titulo;
    private JLabel lNombre;
    private JTextField tfNombre;
    
    public PanelSede(){
        this.setSize(SIZE_X, SIZE_Y);
        int mult = 0;
        
        titulo = new JLabel("Agregar Sede");
        titulo.setBounds(X*2,  H * mult++  ,L*2,H);
        this.add(titulo);
       
        lNombre = new JLabel("Nombre Sede:");
        lNombre.setBounds(X,   H * mult++  ,L*2,H);
        this.add(lNombre);
        
        tfNombre = new JTextField();
        tfNombre.setBounds(X,  H * mult++  ,L*2,H);
        fields.add(tfNombre);
        this.add(tfNombre);
        
        super.armarPanelDireccion(X,   H  ,L*2,H,mult);
        
    }
    
    @Override
    public String[] getFields(){
        mensaje("Sede Agregada");
        return new String[] {tfNombre.getText(),getDireccion()};
    }
    
}
