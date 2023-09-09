package com.example.tpintegrador.dao;

import java.sql.SQLException;

public abstract class FactoryDAO {

    public static final int MySQL = 1;

    public abstract ClienteDAO getClienteDAO() throws SQLException;

    public static FactoryDAO getFactoryDAO (int dbDAO) throws ClassNotFoundException {
        switch (dbDAO) {
            case MySQL:
                return MySqlDAOFactory.getInstance();
            default:
                return null;
        }
    }


}
