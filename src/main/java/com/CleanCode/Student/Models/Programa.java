package com.CleanCode.Student.Models;

public class Programa {
    private int id;
    private String nombre;
    private int semestres;
    private Lugar direccion;

    public Programa(int id, String nombre, int semestres, Lugar direccion) {
        this.id = id;
        this.nombre = nombre;
        this.semestres = semestres;
        this.direccion = direccion;
    }

    public Programa() {
        id = 0;
        nombre = "";
        semestres = 0;
        direccion = new Lugar();
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

    public int getSemestres() {
        return semestres;
    }

    public void setSemestres(int semestres) {
        this.semestres = semestres;
    }

    public Lugar getDireccion() {
        return direccion;
    }

    public void setDireccion(Lugar direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Programa{" + "id=" + id + ",nombre=" + nombre + ",semestres=" + semestres + ",direccion=" + direccion + '}';
    }
}
