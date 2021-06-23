package com.example.pruebademoappminimarket.model;

public class CarritoVentaDetalle {
    Integer id_carritoven;
    Integer id_carritovendeta;

    Integer cantidad;
    Double precio;
    Double subtotal;
    Double igv;
    Double total;

    Integer id_producto;
    String nombreproducto;
    String imagen;

    public CarritoVentaDetalle() {
    }

    public CarritoVentaDetalle(Integer id_carritoven, Integer id_carritovendeta, Integer cantidad, Double precio, Double subtotal, Double igv, Double total, Integer id_producto, String nombreproducto, String imagen) {
        this.id_carritoven = id_carritoven;
        this.id_carritovendeta = id_carritovendeta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.id_producto = id_producto;
        this.nombreproducto = nombreproducto;
        this.imagen = imagen;
    }

    public Integer getId_carritoven() {
        return id_carritoven;
    }

    public void setId_carritoven(Integer id_carritoven) {
        this.id_carritoven = id_carritoven;
    }

    public Integer getId_carritovendeta() {
        return id_carritovendeta;
    }

    public void setId_carritovendeta(Integer id_carritovendeta) {
        this.id_carritovendeta = id_carritovendeta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
