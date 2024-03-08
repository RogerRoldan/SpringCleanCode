package com.CleanCode.Student.Models;

import java.util.List;

public class MunicipiosCreados implements VisualizarInformacion<List<Municipio>>{
    private List<Municipio> lista;

    public MunicipiosCreados(List<Municipio> lista) {
        this.lista = lista;
    }

    public List<Municipio> getLista() {
        return lista;
    }

    public void setLista(List<Municipio> lista) {
        this.lista = lista;
    }


    @Override
    public String nombreClase() {
        return "Clase MunicipiosCreados";
    }

    @Override
    public List<Municipio> informacionObjeto() {
        return lista;
    }
}
