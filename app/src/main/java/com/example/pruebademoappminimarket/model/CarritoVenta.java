package com.example.pruebademoappminimarket.model;

public class CarritoVenta {

    Integer id_carritoven;
    String fechaven;
    Double subtotal;
    Double igv;
    Double total;
    String tipoentrega;
    Integer id_cliente;
    String nombrecliente;
    CarritoVentaDetalle ventadetalle;

    public CarritoVenta() {
    }

    public CarritoVenta(Integer id_carritoven, String fechaven, Double subtotal, Double igv, Double total, String tipoentrega, Integer id_cliente, String nombrecliente, CarritoVentaDetalle ventadetalle) {
        this.id_carritoven = id_carritoven;
        this.fechaven = fechaven;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.tipoentrega = tipoentrega;
        this.id_cliente = id_cliente;
        this.nombrecliente = nombrecliente;
        this.ventadetalle = ventadetalle;
    }

    public Integer getId_carritoven() {
        return id_carritoven;
    }

    public void setId_carritoven(Integer id_carritoven) {
        this.id_carritoven = id_carritoven;
    }

    public String getFechaven() {
        return fechaven;
    }

    public void setFechaven(String fechaven) {
        this.fechaven = fechaven;
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

    public String getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(String tipoentrega) {
        this.tipoentrega = tipoentrega;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public CarritoVentaDetalle getVentadetalle() {
        return ventadetalle;
    }

    public void setVentadetalle(CarritoVentaDetalle ventadetalle) {
        this.ventadetalle = ventadetalle;
    }
}
