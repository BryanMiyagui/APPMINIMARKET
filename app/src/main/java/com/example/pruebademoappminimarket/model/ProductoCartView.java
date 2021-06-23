package com.example.pruebademoappminimarket.model;

public class ProductoCartView {

    private int id_categoria;
    private int id_producto;
    private String descripcion;
    private double precio;
    private String imagenproducto;

    public ProductoCartView() {

    }

    public ProductoCartView(int id_categoria, int id_producto, String descripcion, double precio, String imagenproducto) {
        this.id_categoria = id_categoria;
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenproducto = imagenproducto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenproducto() {
        return imagenproducto;
    }

    public void setImagenproducto(String imagenproducto) {
        this.imagenproducto = imagenproducto;
    }
}
