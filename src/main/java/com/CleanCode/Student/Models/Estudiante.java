package com.CleanCode.Student.Models;

public class Estudiante {
    private int id;
    private String nombres;
    private String apellidos;
    private int edad;
    private double codigo;
    private Programa programa;
    private Lugar direccion;

    public Estudiante(int id, String nombres, String apellidos, int edad, double codigo, Programa programa, Lugar direccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.codigo = codigo;
        this.programa = programa;
        this.direccion = direccion;
    }

    public Estudiante(){
        this.id = 0;
        this.nombres = "";
        this.apellidos = "";
        this.edad = 0;
        this.codigo = 0;
        this.programa = new Programa();
        this.direccion = new Lugar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Lugar getDireccion() {
        return direccion;
    }

    public void setDireccion(Lugar direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ",nombres=" + nombres + ",apellidos=" + apellidos + ",edad=" + edad + ",codigo=" + codigo + ",programa=" + programa + ",direccion=" + direccion + '}';
    }
}

