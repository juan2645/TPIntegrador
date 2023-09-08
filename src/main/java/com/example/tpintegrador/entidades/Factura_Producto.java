package com.example.tpintegrador.entidades;

public class Factura_Producto {
    private int idFactura;
    private int idProducto;
    private int cantidad;

    public Factura_Producto(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }
}
