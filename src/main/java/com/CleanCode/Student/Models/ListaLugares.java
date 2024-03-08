package com.CleanCode.Student.Models;

import java.util.List;

public class ListaLugares implements VisualizarInformacion<List<Lugar>>{
    private List<Lugar> lista;

    public ListaLugares(List<Lugar> lista) {
        this.lista = lista;
    }

    public List<Lugar> getLista() {
        return lista;
    }

    public void setLista(List<Lugar> lista) {
        this.lista = lista;
    }

    @Override
    public String nombreClase() {
        return "Clase ListaLugares";
    }

    @Override
    public List<Lugar> informacionObjeto() {
        return lista;
    }
}
