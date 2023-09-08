package com.example.tpintegrador.dao;

import java.sql.Connection;

public class MySqlDAOFactory extends FactoryDAO {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localHost:3306/db_ventas";
    public static final String CONN;
    public MySqlDAOFactory dbMySqlDao;
    public static Connection createConnection();

    public MySqlDAOFactory getInstance() {
        if (dbMySqlDao == null) {
            dbMySqlDao = new MySqlDAOFactory();
        }
        return dbMySqlDao;
    }

    public ClienteDAO getClienteDAO() {
        return new ClienteDAO();
    }

}
