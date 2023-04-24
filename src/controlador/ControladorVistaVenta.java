
package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DB;
import modelo.Fecha;
import modelo.LineaVenta;
import modelo.Producto;
import modelo.Venta;
import vista.VistaVenta;

public class ControladorVistaVenta {
    static VistaVenta v = new VistaVenta();
    
    public static void mostrar(){
        v.setVisible(true);
        DB db = new DB();
        ArrayList<Producto> listaProductos = db.obtenerProductos();
        DefaultTableModel modelo = (DefaultTableModel) v.getTblProductosVenta().getModel();
        modelo.setNumRows(0);
        
        for (Producto listaProducto : listaProductos) {
            Object[] fila = new Object[3];
            
            fila [0] = listaProducto.getIdProducto();
            fila [1] = listaProducto.getNombre();
            fila [2] = listaProducto.getPrecio();
            
            modelo.addRow(fila);
        }
    }
    
    public static void click(){
        int nfila = v.getTblProductosVenta().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) v.getTblProductosVenta().getModel();
        
        if(nfila >= 0){
            String idProducto = modelo.getValueAt(nfila, 0).toString();
            String nombre = modelo.getValueAt(nfila, 1).toString();
            String precio = modelo.getValueAt(nfila, 2).toString();
            
            v.getTxtIdProducto().setText(idProducto);
            v.getTxtNombre().setText(nombre);
            v.getTxtPrecio().setText(precio);
            v.getTxtCantidad().setText("");
        }
    }
    
    public static void click2(){
        int nfila = v.getTblVenta().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) v.getTblVenta().getModel();
        
        if(nfila >= 0){
            String idProducto = modelo.getValueAt(nfila, 0).toString();
            String nombre = modelo.getValueAt(nfila, 1).toString();
            String precio = modelo.getValueAt(nfila, 2).toString();
            
            v.getTxtIdProducto().setText(idProducto);
            v.getTxtNombre().setText(nombre);
            v.getTxtPrecio().setText(precio);
        }
    }
    
    public static void agregarAVenta(){
        int idProducto = Integer.parseInt(v.getTxtIdProducto().getText());
        String nombre = v.getTxtNombre().getText();
        Double precio = Double.parseDouble(v.getTxtPrecio().getText());
        int cantidad = Integer.parseInt(v.getTxtCantidad().getText());
        
        Object[] fila = new Object[5];
        fila[0] = idProducto;
        fila[1] = nombre;
        fila[2] = precio;
        fila[3] = cantidad;
        fila[4] = precio * cantidad;
        
        DefaultTableModel modelo = (DefaultTableModel) v.getTblVenta().getModel();
        
        modelo.addRow(fila);
    }   
    
    public static void eliminarDeVenta(){
        int nfila = v.getTblVenta().getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) v.getTblVenta().getModel();
        
        if(nfila>=0){
            modelo.removeRow(nfila);
        }
    }
    
    public static void calcularTotal(){
        DefaultTableModel modelo = (DefaultTableModel) v.getTblVenta().getModel();
        int numeroFilas = modelo.getRowCount();
        double total = 0;
        for (int i = 0; i < numeroFilas; i++) {
            total +=  (double) modelo.getValueAt(i, 4);
        }
        
        v.getTxtTotal().setText(""+total);
    }
    
    public static void volver(){
        v.setVisible(false);
        ControladorVistaMenu.mostrar();        
    }
    
    public static void realizarVenta(){
        Venta venta = new Venta();
        Fecha f = new Fecha();
        ArrayList<LineaVenta> lineasVenta = new ArrayList<>();
        
        
        DefaultTableModel model = (DefaultTableModel) v.getTblVenta().getModel();
        int nfilas = model.getRowCount();
        
        for (int i = 0; i < nfilas; i++) {
            LineaVenta l = new LineaVenta();
            l.getP().setIdProducto(Integer.parseInt(model.getValueAt(i, 0).toString()));
            l.getP().setNombre(model.getValueAt(i, 1).toString());
            l.getP().setPrecio(Double.parseDouble(model.getValueAt(i, 2).toString()));
            
            l.setCantidad(Integer.parseInt(model.getValueAt(i, 3).toString()));
            l.setIdLineaVenta(i);
            
            lineasVenta.add(l);
        }
        
        venta.setF(f);

        venta.setLineasVenta(lineasVenta);
        
        DB db = new DB();
        
        db.cargarVenta(venta);
    }
}
