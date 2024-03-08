package com.CleanCode.Student.Models;

public class Lugar {
    private int id;
    private String direccion;
    private Departamento departamento;
    private Municipio municipio;

    public Lugar(int id, String direccion, Departamento departamento, Municipio municipio) {
        this.id = id;
        this.direccion = direccion;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public Lugar() {
        id = 0;
        direccion = "";
        departamento = new Departamento();
        municipio = new Municipio();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Lugar{" + "id=" + id + ",direccion=" + direccion + ",departamento=" + departamento + ",municipio=" + municipio + '}';
    }

}
