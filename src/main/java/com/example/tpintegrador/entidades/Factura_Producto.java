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

    public int getIdFactura() {
        return idFactura;
    }

<<<<<<< HEAD
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

=======
>>>>>>> 6cb343ef7878ca2a31b02f6235269da958017b58
    public int getIdProducto() {
        return idProducto;
    }

<<<<<<< HEAD
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Factura [" + idFactura + ", producto = " + idProducto + ", cantidad = " + cantidad + "]";
    }
=======
    public int getCantidad() {
        return cantidad;
    }
>>>>>>> 6cb343ef7878ca2a31b02f6235269da958017b58
}
