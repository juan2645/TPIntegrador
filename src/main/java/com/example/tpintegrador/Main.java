package com.example.tpintegrador;

import com.example.tpintegrador.DAOMySQL.MySQL_ClienteDAO;
import com.example.tpintegrador.DAOMySQL.MySQL_FacturaDAO;
import com.example.tpintegrador.DAOMySQL.MySqlDAOFactory;
import com.example.tpintegrador.entidades.Cliente;
import com.example.tpintegrador.entidades.Factura;

import java.util.LinkedList;

public class Main {
    private static MySQL_ClienteDAO cliente_dao;
    private static MySQL_FacturaDAO factura_dao;
    public static void main(String[] args) throws Exception {

        MySqlDAOFactory mysql = MySqlDAOFactory.getInstance();

        cliente_dao = new MySQL_ClienteDAO(mysql);
        cliente_dao.crearTabla();
        cliente_dao.leerCSV();

        LinkedList<Cliente> clientes = cliente_dao.listar();
        for (Cliente c : clientes) {
            System.out.println(c);
        }

        factura_dao = new MySQL_FacturaDAO(mysql);
        factura_dao.crearTabla();
        factura_dao.leerCSV();

        LinkedList<Factura> facturas = factura_dao.listar();
        for (Factura f : facturas) {
            System.out.println(f);
        }

    }
}
