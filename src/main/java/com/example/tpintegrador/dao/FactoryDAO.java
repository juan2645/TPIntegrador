package com.example.tpintegrador.dao;

import java.sql.SQLException;

public abstract class FactoryDAO {

    public static final int MySQL = 1;
    public static final int Derby = 2;
    public static final int Jpa = 3;

    public static FactoryDAO getFactoryDAO (String dbDAO, String fileCSV) throws Exception {
        switch (dbDAO) {
            case "MySQL": return MySqlDAOFactory.getInstance();
            case "Derby": return null; // acá iría la instanciación de Derby
            case "Jpa": return null; // acá iría la instanciación de JPA
            default: return null;
        }
//        return (dao != null) ? dao.getFactoryDAO(fileCSV) : null;
    }

//    public abstract DAO<?> getFactoryDAO (String fileCSV) throws Exception;

}
