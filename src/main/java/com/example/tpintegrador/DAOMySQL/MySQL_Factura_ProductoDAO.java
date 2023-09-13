package com.example.tpintegrador.DAOMySQL;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.example.tpintegrador.DAOInterface.DAO;
import com.example.tpintegrador.entidades.Factura_Producto;

public class MySQL_Factura_ProductoDAO implements DAO<Factura_Producto> {
    private static MySqlDAOFactory connectionMySQL;

    public MySQL_Factura_ProductoDAO(MySqlDAOFactory connectionMySQL) {
        this.connectionMySQL = connectionMySQL;
    }

    @Override
    public void crearTabla() throws Exception {
        connectionMySQL.conectar();
        String query = "CREATE TABLE Factura_Producto ("
                + "idFactura INT,"
                + "idProducto INT,"
                + "cantidad INT,"
                + "PRIMARY KEY (idProducto, idFactura)"
                + ")";
        PreparedStatement prepareStatement = connectionMySQL.conn().prepareStatement(query);
        prepareStatement.execute();
        createRelationFacturaProductoFactura(connectionMySQL.conn());
        createRelationFacturaProductoProducto(connectionMySQL.conn());
        connectionMySQL.cerrar();
    }


    public void createRelationFacturaProductoFactura(Connection connection) throws Exception {
        String query = "ALTER TABLE Factura_Producto "
                + "ADD CONSTRAINT FK_Factura_Producto_Factura "
                + "FOREIGN KEY (idFactura) "
                + "REFERENCES Factura(idFactura)";
        connection.prepareStatement(query).execute();
    }

    public void createRelationFacturaProductoProducto(Connection connection) throws Exception {
        String query = "ALTER TABLE Factura_Producto "
                + "ADD CONSTRAINT FK_Factura_Producto_Producto "
                + "FOREIGN KEY (idProducto) "
                + "REFERENCES Producto(idProducto)";
        connection.prepareStatement(query).execute();
    }


    @Override
    public void insertar(Factura_Producto t) throws Exception {
        try {
            connectionMySQL.conectar();
            String insertSql = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(insertSql);
            preparedStatement.setInt(1, t.getIdFactura());
            preparedStatement.setInt(2, t.getIdProducto());
            preparedStatement.setInt(3, t.getCantidad());
            preparedStatement.executeUpdate();
            connectionMySQL.cerrar();
        } catch (Exception e) {

        }

    }

    @Override
    public LinkedList<Factura_Producto> listar() throws Exception {
        LinkedList<Factura_Producto> solucion = new LinkedList<>();
        connectionMySQL.conectar();
        PreparedStatement ps = connectionMySQL.conn().prepareStatement("SELECT * FROM Factura_Producto");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Factura_Producto factura_producto_lista = new Factura_Producto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            solucion.add(factura_producto_lista);
        }
        connectionMySQL.cerrar();
        return solucion;
    }

    @Override
    public void leerCSV() {
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader()
                    .parse(new FileReader("src/main/java/com/example/tpintegrador/csvs/facturas-productos.csv"));
            for (CSVRecord row : parser) {
                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));
                Factura_Producto factura_Producto = new Factura_Producto(idFactura, idProducto, cantidad);
                insertar(factura_Producto);
            }
        } catch (Exception e) {

        }

    }

}
