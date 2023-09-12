package com.example.tpintegrador.DAOMySQL;

import com.example.tpintegrador.DAOFactory.FactoryDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDAOFactory extends FactoryDAO {
    private Connection conn;
    private MySqlDAOFactory c;
    public static MySqlDAOFactory dbMySqlDao;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URI = "jdbc:mysql://localHost:3306/integrador1";
    public static final String USER = "root";
    public static final String PASS = "";

    private MySqlDAOFactory() {
    }

    public Connection conn() {
        return conn;
    }

    public static MySqlDAOFactory getInstance() {
        if (dbMySqlDao == null) {
            dbMySqlDao = new MySqlDAOFactory();
        }
        return dbMySqlDao;
    }

    public void conectar() throws Exception{
        Connection connection = DriverManager.getConnection(URI, USER, PASS);
        Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        this.conn = connection;
    }

    public void cerrar() throws Exception{
        if(conn != null){
            if(!conn.isClosed()){
                conn.close();
            }
        }
    }
}
