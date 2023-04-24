package controlador;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.DB;
import modelo.Inventario;
import modelo.Producto;
import vista.VistaProductos;

public class ControladorVistaProductos {
    static VistaProductos v = new VistaProductos();
    
    public static void mostrar(){
        v.setVisible(true);
        DB db = new DB();
        ArrayList<Inventario> listaInventario = db.obtenerInventario();
        
        DefaultTableModel model = (DefaultTableModel) v.getTblProductos().getModel();
        model.setNumRows(0);
        for (Inventario inventario : listaInventario) {
            Object[] fila = new Object[5];
            int idProducto = inventario.getProducto().getIdProducto();
            String nombre = inventario.getProducto().getNombre();
            Double precio = inventario.getProducto().getPrecio();
            int cantidadActual = inventario.getCantidadActual();
            int stockSeguridad = inventario.getStockSeguridad();
            
            fila[0] = idProducto;
            fila[1] = nombre;
            fila[2] = precio;
            fila[3] = cantidadActual;
            fila[4] = stockSeguridad;
            
            model.addRow(fila);
        }
    }
    
    public static void ocultar(){
        v.setVisible(false);
    }
    
    public static void click(){
        int nfila = v.getTblProductos().getSelectedRow();
        
        if(nfila >= 0){
            String idProducto = v.getTblProductos().getValueAt(nfila, 0).toString();
            String nombre = v.getTblProductos().getValueAt(nfila, 1).toString();
            String precio = v.getTblProductos().getValueAt(nfila, 2).toString();
            String cantidadActual = v.getTblProductos().getValueAt(nfila, 3).toString();
            String stockSeguridad = v.getTblProductos().getValueAt(nfila, 4).toString();
            
            v.getTxtCodigo().setText(idProducto);
            v.getTxtNombre().setText(nombre);
            v.getTxtPrecio().setText(precio);
            v.getTxtCantidadDisponible().setText(cantidadActual);
            v.getTxtStockSeguridad().setText(stockSeguridad);
        }
    }
    
    public static void btnAgregar(){
        System.out.println("Se ejecuto el boton agregar");
        
        int idProducto = Integer.parseInt(v.getTxtCodigo().getText());
        String nombre = v.getTxtNombre().getText();
        Double precio = Double.parseDouble(v.getTxtPrecio().getText());
        int cantidadActual = Integer.parseInt(v.getTxtCantidadDisponible().getText());
        int stockSeguridad = Integer.parseInt(v.getTxtStockSeguridad().getText());
        
        Producto p = new Producto(idProducto,nombre,precio);
        Inventario i = new Inventario(stockSeguridad, cantidadActual, p);
        
        DB db = new DB();
        db.agregarProducto(p);
        db.agregarItemInventario(i);
        
        mostrar();
    }
    
    public static void btnModificar(){
        System.out.println("Se ejecuto el boton modificar");
        
        int idProducto = Integer.parseInt(v.getTxtCodigo().getText());
        String nombre = v.getTxtNombre().getText();
        Double precio = Double.parseDouble(v.getTxtPrecio().getText());
        int cantidadActual = Integer.parseInt(v.getTxtCantidadDisponible().getText());
        int stockSeguridad = Integer.parseInt(v.getTxtStockSeguridad().getText());
        
        Producto p = new Producto(idProducto,nombre,precio);
        Inventario i = new Inventario(stockSeguridad, cantidadActual, p);
        
        DB db = new DB();
        db.modificarProducto(p);
        db.modificarItemInventario(i);
        
        mostrar();
    }
    
    public static void btnEliminar(){
        System.out.println("Se ejecuto el boton eliminar");
        
        int idProducto = Integer.parseInt(v.getTxtCodigo().getText());
        
        DB db = new DB();
        db.eliminarItemInventario(idProducto);
        db.eliminarProducto(idProducto);
        mostrar();
    }
    
    public static void btnLimpiar(){
        System.out.println("Se ejecuto el boton limpiar");
        
        v.getTxtCodigo().setText("");
        v.getTxtNombre().setText("");
        v.getTxtPrecio().setText("");
        v.getTxtCantidadDisponible().setText("");
        v.getTxtStockSeguridad().setText("");
    }
    
    public static void btnRealizarVenta(){
        System.out.println("Se ejecuto el boton realizar venta");
    }
    
    public static void volver(){
        ocultar();
        ControladorVistaMenu.mostrar();
    }
}
