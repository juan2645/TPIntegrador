package com.example.tpintegrador.DAOMySQL;

import com.example.tpintegrador.entidades.Producto;
import java.sql.PreparedStatement;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

public class MySQL_ProductoDAO {
    private static MySqlDAOFactory connectionMySQL;

    public MySQL_ProductoDAO(MySqlDAOFactory connectionMySQL) {
        this.connectionMySQL = connectionMySQL;
    }

    //@Override
    public void crearTabla() throws Exception {
        connectionMySQL.conectar();
        String query = "CREATE TABLE Producto(idProducto INT, nombre VARCHAR(500), valor float, PRIMARY KEY(idProducto))";
        PreparedStatement prepareStatement = connectionMySQL.conn().prepareStatement(query);
        prepareStatement.execute();
        connectionMySQL.cerrar();
    }
    
    //@Override
    public void insertar(Producto producto) throws Exception {
        try {
            connectionMySQL.conectar();
            String insertSQL = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(insertSQL);
            preparedStatement.setInt(1, producto.getIdProducto());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setFloat(3, producto.getValor());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   // @Override
    public LinkedList<Producto> listar() throws Exception {
        LinkedList<Producto> solucion = new LinkedList<>();
        connectionMySQL.conectar();
        PreparedStatement ps = connectionMySQL.conn().prepareStatement("SELECT * FROM Producto");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Producto producto_lista = new Producto(rs.getInt(1),rs.getString(2), rs.getFloat(3));
            solucion.add(producto_lista);
        }
        connectionMySQL.cerrar();
        return solucion;
    }
    
    public void leerCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/main/java/com/example/tpintegrador/csvs/productos.csv"));
            for(CSVRecord row: parser) {
                int idP = Integer.parseInt(row.get("idProducto"));
                Producto producto = new Producto(idP, row.get("nombre"), Float.parseFloat((row.get("valor"))));
                insertar(producto);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
        
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
                System.out.println("PRODUCTO MAS VENDIDO:");
                System.out.println("Venta: " + venta);
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

