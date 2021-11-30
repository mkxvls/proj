package gui;

/**
 *
 * @author Maximiliano Valencia Saez
 */
public interface Gui {

    public static final String OP_AGREGAR = "Agregar";
    public static final String OP_CONF = "Confirmar";
    public static final String OP_LISTAR = "Listar";
    public static final String OP_MESA = "Mesa";
    public static final String OP_PERS = "Persona";
    public static final String OP_SEDE = "Sede";
    public static final String OP_VOLVER = "Volver";
    public static final String OP_ADDSEDE = "Agregar Sede";
    public static final int X = 50;
    public static final int Y = 10;
    public static final int H = 30;
    public static final int L = 100;
    public static final int SIZE_X = 400;
    public static final int SIZE_Y = 400;
    
    public abstract String[] getFields();
    
}
