package com.CleanCode.Student.RepositoryBase.File;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Municipio;

import java.io.*;
import java.util.List;

public class MunicipalityCrudFile implements ICRUD<Municipio> {

    private List<Municipio> list;
    private static final String NAME_FILE = "municipalities.txt";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Files/" + NAME_FILE;
    private File file = new File(ROUTE_FILE);

    @Override
    public List<Municipio> GetAll() {
        return list;
    }

    @Override
    public Municipio Save(Municipio entity) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            writer.write(entity.toString() + "\r\n");
            writer.close();

            System.out.println("Municipality added successfully");
            return entity;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error adding the municipality");

            throw new RuntimeException("Error al agregar el municipio");
        }
    }

    @Override
    public Municipio Update(Municipio entity) {
        return null;
    }

    @Override
    public void Delete(int id) {

    }
}
