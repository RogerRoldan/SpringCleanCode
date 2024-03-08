package com.CleanCode.Student.Persistence;

import com.CleanCode.Student.Models.*;
import com.CleanCode.Student.Repository.*;

public class Seed {

    DepartmentRepository departmentRepository = new DepartmentRepository();
    StudentRepository studentRepository = new StudentRepository();
    MunicipalityRepository municipalityRepository = new MunicipalityRepository();
    ProgramRepository programRepository = new ProgramRepository();
    PlacesRepository placeRepository = new PlacesRepository();

    Departamento departmentMeta = new Departamento(1, "Meta");
    Departamento departmentCundinamarca = new Departamento(2, "Cundinamarca");
    Departamento departmentBoyaca = new Departamento(3, "Boyaca");
    Departamento departmentTolima = new Departamento(4, "Tolima");

    Departamento departmentMetaUpdate = new Departamento(1, "MetaUpdate");


    Municipio municipalityCumaral = new Municipio(1, "Cumaral", departmentMeta);
    Lugar lugarDistrital = new Lugar(1, "Universidad Distrital", departmentMeta, municipalityCumaral);
    Programa programaSistemas = new Programa(1, "Ingenieria de Sistemas", 10, lugarDistrital);
    Estudiante estudianteRoger = new Estudiante(1, "Roger Ricardo", "Roldan", 20, 160004400, programaSistemas, lugarDistrital);

    public void Execute() {
        departmentRepository.Create(departmentMeta);
        departmentRepository.Create(departmentCundinamarca);
        departmentRepository.Create(departmentBoyaca);
        departmentRepository.Create(departmentTolima);

        departmentRepository.Update(departmentMetaUpdate);
        departmentRepository.Delete(2);

        departmentRepository.GetAll();

        studentRepository.Create(estudianteRoger);

        municipalityRepository.Create(municipalityCumaral);

        programRepository.Create(programaSistemas);

        placeRepository.Create(lugarDistrital);


    }



}

