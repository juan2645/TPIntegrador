package com.example.tpintegrador.dao;

public abstract class FactoryDAO {
    // el factory debe ser singleton
    public static final int MySQL = 1;

    public static FactoryDAO getFactoryDAO (int dbDAO) throws ClassNotFoundException {
        switch (dbDAO) {
            case MySQL:
                return MySqlDAOFactory.getInstance();
        }
    }

    public abstract ClienteDAO getClienteDAO();

}
