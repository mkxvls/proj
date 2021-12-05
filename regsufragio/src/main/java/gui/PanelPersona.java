package gui;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import modelo.Persona;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public class PanelPersona extends PanelAbs implements Gui,ItemListener {
    private JLabel titulo,lRut,lNombres,lApellidos,lTipo;
    private JTextField tfRut,tfNombres,tfApellidos,tfPartido;
    private JCheckBox cbPartido;
    
    private ButtonGroup grupoTipoPersona;
    private JRadioButton tipoVotante;
    private JRadioButton tipoVocal;
    private JRadioButton tipoApoderade;
    
    public PanelPersona(){
        this.setSize((int) (SIZE_X+L), (int) (SIZE_Y));
        int mult = 0;
        
        titulo = new JLabel("Agregar Persona");
        titulo.setBounds(X*2+L, H * mult++  ,L*2,H);
        this.add(titulo);
        
        lNombres = new JLabel("Nombres:");
        lNombres.setBounds(X-15,  H * mult++  ,L*2,H);
        this.add(lNombres);
        
        tfNombres = new JTextField();
        tfNombres.setBounds(X-15, H * mult++  ,L*2,H);
        this.add(tfNombres);
        fields.add(tfNombres);
        
        lApellidos = new JLabel("Apellidos:");
        lApellidos.setBounds(X-15, H * mult++  ,L*2,H);
        this.add(lApellidos);
        tfApellidos = new JTextField();
        tfApellidos.setBounds(X-15, H * mult++  ,L*2,H);
        this.add(tfApellidos);
        fields.add(tfApellidos);
        
        lRut = new JLabel("RUT:");
        lRut.setBounds(X-15, H * mult++  ,L*2,H);
        this.add(lRut);
        tfRut = new JTextField();
        tfRut.setBounds(X-15, H * mult++  ,L*2,H);
        this.add(tfRut);
        fields.add(tfRut);
        
        cbPartido = new JCheckBox("Partido");
        cbPartido.setSelected(false);
        cbPartido.addItemListener(this);
        cbPartido.setBounds(X-15, H * mult  ,L*2,H);
        this.add(cbPartido);
        
        lTipo = new JLabel("Tipo:");
        lTipo.setBounds((X+L-15)*2, H * mult++  ,L*2,H);
        this.add(lTipo);
        grupoTipoPersona = new ButtonGroup();
        tipoVotante = new JRadioButton("Votante");
        tipoVotante.setActionCommand(Persona.VOTANTE);
        tipoVotante.setBounds((X+L-15)*2, H * mult++  ,L*2,H);
        tipoVocal = new JRadioButton("Vocal");
        tipoVocal.setActionCommand(Persona.VOCAL);
        tipoVocal.setBounds((X+L-15)*2, H * mult++  ,L*2,H);
        tipoApoderade = new JRadioButton("Apoderade");
        tipoVocal.setActionCommand(Persona.APODERADE);
        tipoApoderade.setBounds((X+L-15)*2, H * mult++  ,L*2,H);
        grupoTipoPersona.add(tipoVotante);
        grupoTipoPersona.add(tipoVocal);
        grupoTipoPersona.add(tipoApoderade);
        this.add(tipoVotante);
        this.add(tipoVocal);
        this.add(tipoApoderade);
        
        super.armarPanelDireccion((X+L-15)*2, H, L*2, H, 1);
    }    
    @Override
    public String[] getFields() {
        String tipo=null;
        switch(grupoTipoPersona.getSelection().getActionCommand()){
            case(Persona.VOTANTE): tipo = Persona.VOTANTE;
            break;
            case(Persona.VOCAL): tipo = Persona.VOCAL;
            break;
            case(Persona.APODERADE): tipo = Persona.APODERADE;
            break;
        }
        String partido = Persona.SINPARTIDO;
        if(tfPartido != null && !tfPartido.getText().equals("") && tfPartido.getText()!=null ) partido=tfPartido.getText();
        String resp[] =new String[]{tfNombres.getText(),tfApellidos.getText(),tfRut.getText(),getDireccion(),partido,tipo};
        return resp;
    
    }
    
    @Override
    public Dimension getPreferredSize(){
        return this.getSize();
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        JCheckBox source = (JCheckBox) ie.getSource();
        if( source.isSelected() ){
            tfPartido = new JTextField();
            tfPartido.setBounds(X-15, H * 8  ,L*2,H);
            this.add(tfPartido);
            fields.add(tfPartido);
            
        }else if( !source.isSelected()){
            this.remove(tfPartido);
            fields.remove(tfPartido);
        }
        this.repaint();
    }

}
