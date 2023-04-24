/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Valentin
 */
public class Inventario {
    private int stockSeguridad;
    private int cantidadActual;
    private Producto producto;

    public Inventario(){
    }

    public Inventario(int stockSeguridad, int cantidadActual, Producto producto) {
        this.stockSeguridad = stockSeguridad;
        this.cantidadActual = cantidadActual;
        this.producto = producto;
    }
    
    public int getStockSeguridad() {
        return stockSeguridad;
    }

    public void setStockSeguridad(int stockSeguridad) {
        this.stockSeguridad = stockSeguridad;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Inventario{" + "stockSeguridad=" + stockSeguridad + ", cantidadActual=" + cantidadActual + ", producto=" + producto + '}';
    }
}
