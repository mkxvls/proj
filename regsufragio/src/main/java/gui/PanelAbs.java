package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public abstract class PanelAbs extends JPanel {
    
    protected JLabel lCalle;
    protected JTextField tfCalle;
    protected JLabel lNumero;
    protected JTextField tfNumero;
    protected JLabel lCiudad;
    protected JTextField tfCiudad;
    protected List<JTextField> fields;
    
    public PanelAbs() {
        fields = new ArrayList<>();
        this.setLayout(null);

    }

    
    public abstract String[] getFields();

    @Override
    public Dimension getPreferredSize() {
        return this.getSize();
    }

    public void clearFields(){
        for(JTextField field : fields){
            field.setText("");
        }   
    }
    
    protected void mensaje(String msg){
        JOptionPane.showMessageDialog(this,msg);
    }
    protected void armarPanelDireccion(int x,int y,int w, int h, int mult){
        lCiudad = new JLabel("Ciudad:");
        lCiudad.setBounds(x, y * mult++  ,w,h);
        this.add(lCiudad);
        tfCiudad = new JTextField();
        tfCiudad.setBounds(x, y * mult++  ,w,h);
        this.add(tfCiudad);
        fields.add(tfCiudad);
        
        lCalle = new JLabel("Calle:");
        lCalle.setBounds(x, y * mult++  ,w,h);
        this.add(lCalle);
        tfCalle = new JTextField();
        tfCalle.setBounds(x, y * mult++  ,w,h);
        this.add(tfCalle);
        fields.add(tfCalle);
        
        lNumero = new JLabel("Numero:");
        lNumero.setBounds(x, y * mult++  ,w,h);
        this.add(lNumero);
        tfNumero = new JTextField();
        tfNumero.setBounds(x, y * mult++  ,w,h);
        this.add(tfNumero);
        fields.add(tfNumero);
    }
    protected String getDireccion(){
        return tfCalle.getText()+" "+tfNumero.getText()+","+tfCiudad.getText();
    }
}
