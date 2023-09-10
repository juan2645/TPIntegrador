package com.example.tpintegrador.DAOMySQL;

import com.example.tpintegrador.entidades.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MySQL_ProductoDAO {
    private static MySqlDAOFactory connectionMySQL;

    public static Producto obtenerProductoMasVendido() throws Exception {
        String query = "SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS venta " +
                "FROM producto p " +
                "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
                "GROUP BY p.idProducto, p.nombre " +
                "ORDER BY venta DESC " +
                "LIMIT 1";

        try {
            connectionMySQL.conectar();
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombreProducto = rs.getString("nombre");
                double venta = rs.getDouble("venta");
                float valor = rs.getFloat("valor");
                Producto producto = new Producto(idProducto, nombreProducto, valor);
                return producto;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connectionMySQL.cerrar();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }



}
