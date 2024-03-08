package com.CleanCode.Student.Models;

import java.util.List;

public class DepartamentosCreados implements  VisualizarInformacion<List<Departamento>>  {

    private List<Departamento> list;

    // Start Class Information
    @Override
    public String nombreClase() {
        return "Clase DepartamentosCreados";
    }

    @Override
    public List<Departamento> informacionObjeto() {
        return list;
    }

    //Finish Class Information


}
