
package modelo;

public class LineaVenta {
    private int idLineaVenta;
    private int cantidad;
    private Producto p = new Producto();

    public LineaVenta() {
    }

    public LineaVenta(int idLineaVenta, Producto p,int cantidad) {
        this.idLineaVenta = idLineaVenta;
        this.cantidad = cantidad;
        this.p = p;
    }
    
    public int getIdLineaVenta() {
        return idLineaVenta;
    }

    public void setIdLineaVenta(int idLineaVenta) {
        this.idLineaVenta = idLineaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }
    
    public double getSubtotal(){
        double subtotal;
        subtotal = p.getPrecio() * cantidad;
        
        return subtotal;
    }

    @Override
    public String toString() {
        return "LineaVenta{" + "idLineaVenta=" + idLineaVenta + ", cantidad=" + cantidad + ", p=" + p + '}';
    }
    
    
}
