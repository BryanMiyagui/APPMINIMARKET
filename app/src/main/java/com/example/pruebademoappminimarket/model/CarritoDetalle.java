package com.example.pruebademoappminimarket.model;

public class CarritoDetalle {

    Integer id_carritoven;
    String fechaven;
    Double subtotal;
    Double igv;
    Double total;
    String tipoentrega;
    Integer id_cliente;
    String nombrecliente;

    Integer id_carritovendeta;
    Integer cantidad_detalle;
    Double precio_detalle;
    Double subtotal_detalle;
    Double igv_detalle;
    Double total_detalle;
    Integer id_producto;
    String nombreproducto;
    String imagen;

    public CarritoDetalle() {
    }

    public CarritoDetalle(Integer id_carritoven, String fechaven, Double subtotal, Double igv, Double total, String tipoentrega, Integer id_cliente, String nombrecliente, Integer id_carritovendeta, Integer cantidad_detalle, Double precio_detalle, Double subtotal_detalle, Double igv_detalle, Double total_detalle, Integer id_producto, String nombreproducto, String imagen) {
        this.id_carritoven = id_carritoven;
        this.fechaven = fechaven;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
        this.tipoentrega = tipoentrega;
        this.id_cliente = id_cliente;
        this.nombrecliente = nombrecliente;
        this.id_carritovendeta = id_carritovendeta;
        this.cantidad_detalle = cantidad_detalle;
        this.precio_detalle = precio_detalle;
        this.subtotal_detalle = subtotal_detalle;
        this.igv_detalle = igv_detalle;
        this.total_detalle = total_detalle;
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

    public Integer getId_carritovendeta() {
        return id_carritovendeta;
    }

    public void setId_carritovendeta(Integer id_carritovendeta) {
        this.id_carritovendeta = id_carritovendeta;
    }

    public Integer getCantidad_detalle() {
        return cantidad_detalle;
    }

    public void setCantidad_detalle(Integer cantidad_detalle) {
        this.cantidad_detalle = cantidad_detalle;
    }

    public Double getPrecio_detalle() {
        return precio_detalle;
    }

    public void setPrecio_detalle(Double precio_detalle) {
        this.precio_detalle = precio_detalle;
    }

    public Double getSubtotal_detalle() {
        return subtotal_detalle;
    }

    public void setSubtotal_detalle(Double subtotal_detalle) {
        this.subtotal_detalle = subtotal_detalle;
    }

    public Double getIgv_detalle() {
        return igv_detalle;
    }

    public void setIgv_detalle(Double igv_detalle) {
        this.igv_detalle = igv_detalle;
    }

    public Double getTotal_detalle() {
        return total_detalle;
    }

    public void setTotal_detalle(Double total_detalle) {
        this.total_detalle = total_detalle;
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
