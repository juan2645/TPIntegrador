package com.example.tpintegrador.dao;

public abstract class FactoryDAO {
    // el factory debe ser singleton
    public static final int MySQL = 1;

    public abstract ClienteDAO getClienteDAO();

}
