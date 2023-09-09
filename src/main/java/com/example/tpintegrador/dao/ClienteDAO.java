package com.example.tpintegrador.dao;

import com.example.tpintegrador.dao.DAO;
import com.example.tpintegrador.entidades.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.IOException;

public interface ClienteDAO {
    public int insertar();

//    private Connection conn;
//
//    public ClienteDAO (Connection conn) {
//        this.conn = conn;
//    }
//    @Override
//    public void crearTabla() {
//        // SQL y Java (si o si dentro del DAO)
//    }
//
//    @Override
//    public void insertar(Cliente cliente) {
//
//    }
}
