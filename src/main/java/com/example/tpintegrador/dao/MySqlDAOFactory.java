package com.example.tpintegrador.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDAOFactory extends FactoryDAO {

    private static MySqlDAOFactory instance;
    private static MySQL_ClienteDAO cliente;
    private Connection conn;
    public static MySqlDAOFactory dbMySqlDao;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localHost:3306/integrador";
    public static final String USER = "root";
    public static final String PASS = "";

    private MySqlDAOFactory() {
    }

    public static Connection connect() throws Exception {
        Connection connection = DriverManager.getConnection(URI, USER, PASS);
        Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        return connection;
    }

//    public Connection conn() {
//        return conn;
//    }

    public static MySqlDAOFactory getInstance() {
        if (dbMySqlDao == null) {
            dbMySqlDao = new MySqlDAOFactory();
        }
        return dbMySqlDao;
    }

//    public ClienteDAO getClienteDAO() {
//        return new MySQL_ClienteDAO();
//    }



    public DAO<?> getFactoryDAO(String fileCSV) throws Exception {
        switch (fileCSV){
            case "clientes.csv":
//                return new ClienteDAO(fileCSV, conn);
                return null;
        }
        return null;
    }
}
