package com.example.tpintegrador;

import com.example.tpintegrador.DAOMySQL.MySQL_ClienteDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_FacturaDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_ProductoDAO;
import com.example.tpintegrador.DAOMySQL.MySqlDAOFactory;
import com.example.tpintegrador.entidades.Cliente;
import com.example.tpintegrador.entidades.Factura;
import com.example.tpintegrador.entidades.Producto;

import java.util.LinkedList;
import java.util.List;

public class Main {
    private static MySQL_ClienteDAO cliente_dao;
    private static MySQL_FacturaDAO factura_dao;
    public static void main(String[] args) throws Exception {

        MySqlDAOFactory mysql = MySqlDAOFactory.getInstance();

        /* TRANSACCIONES CON LA CLASE CLIENTE */

        cliente_dao = new MySQL_ClienteDAO(mysql);
        cliente_dao.crearTabla();
        cliente_dao.leerCSV();

        LinkedList<Cliente> clientes = cliente_dao.listar();
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        /* TRANSACCIONES CON LA CLASE FACTURA */

        factura_dao = new MySQL_FacturaDAO(mysql);
        factura_dao.crearTabla();
        factura_dao.leerCSV();

        LinkedList<Factura> facturas = factura_dao.listar();
        for (Factura f : facturas) {
            System.out.println(f);
        }

        /* TRANSACCIONES CON LA CLASE PRODUCTO */



     /* TRANSACCIONES CON LA CLASE FACTURA-PRODUCTO */
        factura_producto_dao = new MySQL_Factura_ProductoDAO(mysql);
        factura_producto_dao.crearTabla();
        factura_producto_dao.leerCSV();



    /* NO BORRRAR:  CUANDO SE IMPLEMENTEN PRODUCTO Y FACTURA-PRODUCTO SE HABILITAN PARA PROBAR.

        /* RETORNA EL PRODUCTO CON MAYOR RECAUDACION */

    /*    System.out.println(" ");

        MySQL_ProductoDAO productoDAO = new MySQL_ProductoDAO();
        Producto pmv = MySQL_ProductoDAO.obtenerProductoMasVendido();

        if (pmv != null) {
            System.out.println("PRODUCTO MAS VENDIDO:");
            System.out.println("Id: " + pmv.getIdProducto());
            System.out.println("Nombre: " + pmv.getNombre());
        } else {
            System.out.println("No existen productos registrados.");
        } */


        /* RETORNA LOS CLIENTES CON MAYOR COMPRA */

      /*  System.out.println(" ");

        System.out.println("LISTADO DE CLIENTES CON MAYORES COMPRAS:");
        List<Object[]> resultados = MySQL_ClienteDAO.obtenerClientesQueMasCompraron();

        for (Object[] resultado : resultados) {
            int idCliente = (int) resultado[0];
            String nombreCliente = (String) resultado[1];
            double compraTotal = (double) resultado[2];

            System.out.println("Id: " + idCliente + ", Nombre: " + nombreCliente + ", Compras : $" + compraTotal);
        }  */



    }
}
