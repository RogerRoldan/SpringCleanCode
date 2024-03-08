package com.CleanCode.Student.Models;

public class Municipio {
    private int id;
    private String nombre;
    private Departamento departamento;

    public Municipio(int id, String nombre, Departamento departamento) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }

    public Municipio() {
        id = 0;
        nombre = "";
        departamento = new Departamento();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Municipio{" + "id=" + id + ",nombre=" + nombre + ",departamento=" + departamento + '}';
    }
}
