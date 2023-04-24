
package modelo;

import java.time.Instant;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private int idVenta;
    private ArrayList<LineaVenta> lineasVenta = new ArrayList<>();
    private Fecha f = new Fecha();
    
    public Venta() {
    }

    public Venta(int idVenta, ArrayList<LineaVenta> lineasVenta, Fecha f) {
        this.idVenta = idVenta;
        this.lineasVenta = lineasVenta;
        this.f = f;
    }
    
    

    public ArrayList<LineaVenta> getLineasVenta() {
        return lineasVenta;
    }

    public void setLineasVenta(ArrayList<LineaVenta> lineasVenta) {
        this.lineasVenta = lineasVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }
    
    public Fecha getF() {
        return f;
    }

    public void setF(Fecha f) {
        this.f = f;
    }
    
    public double getTotal(){
        double total = 0;
        for (LineaVenta lineaVenta : lineasVenta) {
            total += lineaVenta.getSubtotal();
        }
        return total;
    }
}
