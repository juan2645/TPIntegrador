package com.example.tpintegrador;

import com.example.tpintegrador.DAOMySQL.MySQL_ClienteDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_FacturaDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_ProductoDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_Factura_ProductoDAO;
import com.example.tpintegrador.DAOMySQL.MySqlDAOFactory;
import com.example.tpintegrador.entidades.Cliente;
import com.example.tpintegrador.entidades.Factura;
import com.example.tpintegrador.entidades.Producto;
import com.example.tpintegrador.entidades.Factura_Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.example.tpintegrador.DAOMySQL.MySQL_ProductoDAO.obtenerProductoMasVendido;

public class Main {
    private static MySQL_ClienteDAO cliente_dao;
    private static MySQL_FacturaDAO factura_dao;
    private static MySQL_ProductoDAO producto_dao;
    private static MySQL_Factura_ProductoDAO factura_producto_dao;

    public static void main(String[] args) throws Exception {

        MySqlDAOFactory mysql = MySqlDAOFactory.getInstance();

        /* TRANSACCIONES CON LA CLASE CLIENTE */

        cliente_dao = new MySQL_ClienteDAO(mysql);
        cliente_dao.crearTabla();
        cliente_dao.leerCSV();

        /* TRANSACCIONES CON LA CLASE FACTURA */

        factura_dao = new MySQL_FacturaDAO(mysql);
        factura_dao.crearTabla();
        factura_dao.leerCSV();

        /* TRANSACCIONES CON LA CLASE PRODUCTO */

        producto_dao = new MySQL_ProductoDAO(mysql);
        producto_dao.crearTabla();
        producto_dao.leerCSV();

     /* TRANSACCIONES CON LA CLASE FACTURA-PRODUCTO */
        factura_producto_dao = new MySQL_Factura_ProductoDAO(mysql);
        factura_producto_dao.crearTabla();
        factura_producto_dao.leerCSV();

    /* RETORNA EL PRODUCTO CON MAYOR RECAUDACION */

        System.out.println(" ");

        System.out.println("PRODUCTO MAS VENDIDO:");
        Map<String, Object> resultadoProductoMasVendido = obtenerProductoMasVendido();
        if (resultadoProductoMasVendido != null) {
            Producto productoMasVendido = (Producto) resultadoProductoMasVendido.get("producto");
            double ventaMasVendida = (double) resultadoProductoMasVendido.get("venta");

            System.out.println("Nombre: " + productoMasVendido.getNombre());
            System.out.println("Venta: " + ventaMasVendida);
        } else {
            System.out.println("No se encontró ningún producto más vendido.");
        }


        /* RETORNA LOS CLIENTES CON MAYOR COMPRA */

        System.out.println(" ");

        System.out.println("LISTADO DE CLIENTES CON MAYORES COMPRAS:");
        List<Object[]> resultados = MySQL_ClienteDAO.obtenerClientesQueMasCompraron();

        System.out.printf("%-5s %-30s %10s%n", "ID", "NOMBRE", "COMPRA");
        for (Object[] resultado : resultados) {
            int idCliente = (int) resultado[0];
            String nombreCliente = (String) resultado[1];
            double compraTotal = (double) resultado[2];
            System.out.printf("%-5d %-30s %10.2f%n", idCliente, nombreCliente, compraTotal);
        }
    }

}
