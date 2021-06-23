package com.example.pruebademoappminimarket.model;

public class TipoDocumento {

    private int id_tipo_doc;
    private String descripcion;

    public TipoDocumento() {
    }

    public TipoDocumento(int id_tipo_doc, String descripcion) {
        this.id_tipo_doc = id_tipo_doc;
        this.descripcion = descripcion;
    }

    public int getId_tipo_doc() {
        return id_tipo_doc;
    }

    public void setId_tipo_doc(int id_tipo_doc) {
        this.id_tipo_doc = id_tipo_doc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String toString() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
