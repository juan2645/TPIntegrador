package com.example.tpintegrador;

import com.example.tpintegrador.DAOMySQL.MySQL_ClienteDAO;
import com.example.tpintegrador.DAOMySQL.MySqlDAOFactory;
import com.example.tpintegrador.entidades.Cliente;
import java.util.LinkedList;

public class Main {
    private static MySQL_ClienteDAO cliente_dao;

    public static void main(String[] args) throws Exception {

        MySqlDAOFactory mysql = MySqlDAOFactory.getInstance();

        cliente_dao = new MySQL_ClienteDAO(mysql);
        cliente_dao.crearTabla();
        cliente_dao.leerCSV();

        LinkedList<Cliente> clientes = cliente_dao.listar();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}
