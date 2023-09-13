package com.example.tpintegrador.DAOMySQL;

import com.example.tpintegrador.DAOInterface.DAO;
import com.example.tpintegrador.entidades.Cliente;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;


public class MySQL_ClienteDAO implements DAO<Cliente> {
    private static MySqlDAOFactory connectionMySQL;

    public MySQL_ClienteDAO(MySqlDAOFactory connectionMySQL) {
        this.connectionMySQL = connectionMySQL;
    }

    @Override
    public void crearTabla() throws Exception {
        connectionMySQL.conectar();
        String query = "CREATE TABLE Cliente(idCliente INT, nombre VARCHAR(500), email VARCHAR(150), PRIMARY KEY(idCliente))";
        PreparedStatement prepareStatement = connectionMySQL.conn().prepareStatement(query);
        prepareStatement.execute();
        connectionMySQL.cerrar();
    }

    @Override
    public void insertar(Cliente cliente) throws Exception {
        try {
            connectionMySQL.conectar();
            String insertSQL = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(insertSQL);
            preparedStatement.setInt(1, cliente.getIdCliente());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getEmail());
            preparedStatement.executeUpdate();
            connectionMySQL.cerrar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<Cliente> listar() throws Exception {
        LinkedList<Cliente> solucion = new LinkedList<>();
        connectionMySQL.conectar();
        PreparedStatement ps = connectionMySQL.conn().prepareStatement("SELECT * FROM Cliente");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Cliente cliente_lista = new Cliente(rs.getInt(1),rs.getString(2), rs.getString(3));
            solucion.add(cliente_lista);
        }
        connectionMySQL.cerrar();
        return solucion;
    }

    public void leerCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/main/java/com/example/tpintegrador/csvs/clientes.csv"));
            for(CSVRecord row: parser) {
                int idC = Integer.parseInt(row.get("idCliente"));
                Cliente cliente = new Cliente(idC,row.get("nombre"),row.get("email"));
                insertar(cliente);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static List<Object[]> obtenerClientesQueMasCompraron() {
        List<Object[]> resultados = new ArrayList<>();

        String query = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS compra_Total "
                + "FROM cliente c "
                + "JOIN factura f ON c.idCliente = f.idCliente "
                + "JOIN factura_producto fp ON f.idFactura = fp.idFactura "
                + "JOIN producto p ON fp.idProducto = p.idProducto "
                + "GROUP BY c.idCliente, c.nombre "
                + "ORDER BY compra_Total DESC";

        try {
            connectionMySQL.conectar();
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt("idCliente");
                String nombreCliente = rs.getString("nombre");
                double compraTotal = rs.getDouble("compra_Total");

                Object[] resultado = {idCliente, nombreCliente, compraTotal};
                resultados.add(resultado);
            }
            connectionMySQL.cerrar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultados;
    }


}
