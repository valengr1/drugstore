/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Valentin
 */
public class DB {

    Connection conexion;
    String usuario = "root";
    String password = "root";
    String cadenaConexion = "jdbc:mysql://localhost/sistemaventas";

    public DB() {
        try {
            conexion = DriverManager.getConnection(cadenaConexion, usuario, password);
            System.out.println("Conexion con exito");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void agregarProducto(Producto prod) {
        try {
            PreparedStatement s = conexion.prepareStatement("INSERT INTO producto (idProducto,nombre,precio) values (?,?,?)");
            s.setString(2, prod.getNombre());
            s.setDouble(3, prod.getPrecio());
            s.setInt(1, prod.getIdProducto());

            s.executeUpdate();

            System.out.println("Producto agregado correctamente");
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void agregarItemInventario(Inventario i) {
        try {
            PreparedStatement s = conexion.prepareStatement("INSERT INTO inventario (cantidadActual,stockSeguridad,idProducto) values (?,?,?)");
            s.setInt(1, i.getCantidadActual());
            s.setInt(2, i.getStockSeguridad());
            s.setInt(3, i.getProducto().getIdProducto());

            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

    }

    public void eliminarProducto(int codigoProducto) {
        try {
            Statement s = conexion.createStatement();
            s.executeUpdate("DELETE FROM PRODUCTO WHERE idProducto =" + codigoProducto);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void eliminarItemInventario(int idProducto) {
        try {
            Statement s = conexion.createStatement();
            s.executeUpdate("DELETE FROM inventario as i WHERE i.idProducto = " + idProducto);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void modificarProducto(Producto prod) {
        try {
            PreparedStatement s = conexion.prepareStatement("UPDATE PRODUCTO SET nombre = ?, precio = ? WHERE idProducto = ?");
            s.setString(1, prod.getNombre());
            s.setDouble(2, prod.getPrecio());
            s.setInt(3, prod.getIdProducto());

            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void modificarItemInventario(Inventario i) {
        try {
            PreparedStatement s = conexion.prepareStatement("UPDATE inventario SET cantidadActual = ?, stockSeguridad = ? WHERE idProducto = ?");
            s.setInt(1, i.getCantidadActual());
            s.setInt(2, i.getStockSeguridad());
            s.setInt(3, i.getProducto().getIdProducto());

            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();

        try {
            Statement s = conexion.createStatement();
            ResultSet res = s.executeQuery("SELECT * FROM PRODUCTO");

            while (res.next()) {
                Producto p = new Producto(res.getInt("idProducto"), res.getString("nombre"), res.getDouble("precio"));
                listaProductos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return listaProductos;
    }

    public ArrayList<Inventario> obtenerInventario() {
        ArrayList<Inventario> listaInventario = new ArrayList<>();

        try {
            Statement s = conexion.createStatement();
            ResultSet res = s.executeQuery("SELECT p.idProducto, p.nombre, p.precio,i.cantidadActual,i.stockSeguridad FROM INVENTARIO as i JOIN PRODUCTO as p ON i.idProducto = p.idProducto");

            while (res.next()) {
                Inventario i = new Inventario();
                Producto p = new Producto();

                p.setIdProducto(res.getInt("idProducto"));
                p.setNombre(res.getString("nombre"));
                p.setPrecio(res.getDouble("precio"));

                i.setProducto(p);
                i.setCantidadActual(res.getInt("cantidadActual"));
                i.setStockSeguridad(res.getInt("stockSeguridad"));

                listaInventario.add(i);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return listaInventario;
    }

    public void cargarVenta(Venta venta) {
        int i = 1;
        int idventa = 0;
        ArrayList<LineaVenta> lineasVenta = venta.getLineasVenta();

        try {
            PreparedStatement s = conexion.prepareStatement("INSERT INTO venta (total,dia,mes,anio) values (?,?,?,?)");
            s.setDouble(1, venta.getTotal());
            s.setInt(2, venta.getF().getDia());
            s.setInt(3, venta.getF().getMes());
            s.setInt(4, venta.getF().getAnio());

            s.executeUpdate();
            try {
                Statement q = conexion.createStatement();
                ResultSet res = q.executeQuery("SELECT idventa FROM venta");
                while(res.next()){
                    idventa = res.getInt("idventa");
                    System.out.println(idventa);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener idVenta: " + e);
            }
            try {
                for (LineaVenta lineaVenta : lineasVenta) {
                    PreparedStatement p = conexion.prepareStatement("INSERT INTO lineaventa (cantidad,subtotal,idventa,idproducto) values (?,?,?,?)");
                    p.setInt(1, lineaVenta.getCantidad());
                    p.setDouble(2, lineaVenta.getSubtotal());
                    p.setInt(3, idventa);
                    p.setInt(4, lineaVenta.getP().getIdProducto());

                    p.executeUpdate();

                    System.out.println("Linea " + i);
                    i++;
                }

            } catch (SQLException e) {
                System.out.println("Eror lineaventa: " + e);
            }
            System.out.println("Venta agregada correctamente");
        } catch (SQLException e) {
            System.out.println("Error venta: " + e);
        }
    }

    public void cargarLineasVenta(Venta venta) {

    }
}
