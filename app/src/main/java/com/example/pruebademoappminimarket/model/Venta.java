package com.example.pruebademoappminimarket.model;

public class Venta {

    Integer id_venta;
    String  fechaven;
    double subtotal;
    double igv;
    double total;
    String tipoentrega;
    String direccionentrega;
    String tipopago;
    String telefonoentrega;
    String estadoventa;


    public Venta() {
    }

    public Venta(Integer id_venta, String fechaven, double subtotal, double igv, double total, String tipoentrega, String direccionentrega, String tipopago, String telefonoentrega, String estadoventa) {
        this.id_venta = id_venta;
        this.fechaven = fechaven;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.tipoentrega = tipoentrega;
        this.direccionentrega = direccionentrega;
        this.tipopago = tipopago;
        this.telefonoentrega = telefonoentrega;
        this.estadoventa = estadoventa;
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public String getFechaven() {
        return fechaven;
    }

    public void setFechaven(String fechaven) {
        this.fechaven = fechaven;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(String tipoentrega) {
        this.tipoentrega = tipoentrega;
    }

    public String getDireccionentrega() {
        return direccionentrega;
    }

    public void setDireccionentrega(String direccionentrega) {
        this.direccionentrega = direccionentrega;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getTelefonoentrega() {
        return telefonoentrega;
    }

    public void setTelefonoentrega(String telefonoentrega) {
        this.telefonoentrega = telefonoentrega;
    }

    public String getEstadoventa() {
        return estadoventa;
    }

    public void setEstadoventa(String estadoventa) {
        this.estadoventa = estadoventa;
    }
}
