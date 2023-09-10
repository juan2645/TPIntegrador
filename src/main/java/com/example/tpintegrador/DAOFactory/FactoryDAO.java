package com.example.tpintegrador.DAOFactory;

import com.example.tpintegrador.DAOMySQL.MySqlDAOFactory;

public abstract class FactoryDAO {

    public static final int MySQL = 1;
    public static final int Derby = 2;
    public static final int Jpa = 3;

    public static FactoryDAO getFactoryDAO(int identificador){
        switch (identificador){
            case 1: return MySqlDAOFactory.getInstance();
            default: return null;
        }
    }

}
