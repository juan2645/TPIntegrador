package com.example.tpintegrador.DAOMySQL;

import com.example.tpintegrador.DAOInterface.DAO;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.example.tpintegrador.entidades.Factura;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;


public class MySQL_FacturaDAO implements DAO<Factura> {
    private MySqlDAOFactory connectionMySQL;

    public MySQL_FacturaDAO(MySqlDAOFactory connectionMySQL) {
        this.connectionMySQL = connectionMySQL;
    }

    @Override
    public void crearTabla() throws Exception {
        connectionMySQL.conectar();
        String query = "CREATE TABLE Factura(idFactura INT, idCliente INT, PRIMARY KEY(idFactura))";
        PreparedStatement prepareStatement = connectionMySQL.conn().prepareStatement(query);
        prepareStatement.execute();
        createRelationFacturaCliente(connectionMySQL.conn());
        connectionMySQL.cerrar();
    }

    @Override
    public void insertar(Factura factura) throws Exception {
        try {
            connectionMySQL.conectar();
            String insertSQL = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
            PreparedStatement preparedStatement = connectionMySQL.conn().prepareStatement(insertSQL);
            preparedStatement.setInt(1, factura.getIdFactura());
            preparedStatement.setInt(2, factura.getIdCliente());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionMySQL.cerrar();
    }

    @Override
    public LinkedList<Factura> listar() throws Exception {
        LinkedList<Factura> solucion = new LinkedList<>();
        connectionMySQL.conectar();
        PreparedStatement ps = connectionMySQL.conn().prepareStatement("SELECT * FROM Factura");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Factura factura_lista = new Factura(rs.getInt(1),rs.getInt(2));
            solucion.add(factura_lista);
        }
        connectionMySQL.cerrar();
        return solucion;
    }

    public void leerCSV(){
        try {
            CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                    FileReader("src/main/java/com/example/tpintegrador/csvs/facturas.csv"));
            for(CSVRecord row: parser) {
                int idF = Integer.parseInt(row.get("idFactura"));
                int idC = Integer.parseInt(row.get("idCliente"));
                Factura factura = new Factura(idF,idC);
                insertar(factura);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createRelationFacturaCliente(Connection connection) throws Exception {
        String query = "ALTER TABLE Factura "
                + "ADD CONSTRAINT FK_cliente_factura "
                + "FOREIGN KEY (idCliente) "
                + "REFERENCES Cliente(idCliente)";
        connection.prepareStatement(query).execute();
    }

}