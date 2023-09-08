package com.example.tpintegrador.dao;

import com.example.tpintegrador.dao.DAO;
import com.example.tpintegrador.entidades.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.IOException;

public class ClienteDAO implements DAO<Cliente> {
    private CSVParser parser;
    private Connection connection;

    public ClienteDAO (String csv, Connection connection) {
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("productos.csv"));
            connection = connection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void crearTabla() {
        // SQL y Java (si o si dentro del DAO)
    }

    @Override
    public void insertar(Cliente c) {

    }
}
