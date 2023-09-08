package com.example.tpintegrador.dao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public interface DAO<T> {
        void crearTabla();

        void insertar(T t) throws SQLException;
}

