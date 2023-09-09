package com.example.tpintegrador;

import com.example.tpintegrador.dao.ClienteDAO;
import com.example.tpintegrador.dao.DAO;
import com.example.tpintegrador.dao.FactoryDAO;
import com.example.tpintegrador.dao.MySqlDAOFactory;



public class Main {
    public static void main(String[] args) throws Exception {
        DAO dao1 = FactoryDAO.getFactoryDAO(1).getClienteDAO();
        MySqlDAOFactory mysql = MySqlDAOFactory.getInstance();
        ClienteDAO daoCliente = mysql.getClienteDAO();

    }


}
