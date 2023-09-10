package com.example.tpintegrador.DAOInterface;
import com.example.tpintegrador.entidades.Cliente;

import java.util.LinkedList;

public interface DAO<T> {
        void crearTabla() throws Exception;
        void insertar(T t) throws Exception;
        LinkedList<Cliente> listar() throws Exception;
        public void leerCSV();

}

