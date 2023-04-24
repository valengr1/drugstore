package controlador;

import vista.VistaMenu;
import vista.VistaProductos;
import vista.VistaVenta;

public class ControladorVistaMenu {
    static VistaMenu v = new VistaMenu();
    
    public static void mostrar(){
        v.setVisible(true);
    }
    
    public static void administrarProductos(){
        ControladorVistaProductos.mostrar();
        v.setVisible(false);
    }
    
    public static void realizarVenta(){
        ControladorVistaVenta.mostrar();
        v.setVisible(false);
    }
}
