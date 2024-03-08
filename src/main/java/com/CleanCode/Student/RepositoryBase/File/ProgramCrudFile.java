package com.CleanCode.Student.RepositoryBase.File;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Programa;

import java.io.*;
import java.util.List;

public class ProgramCrudFile implements ICRUD<Programa> {

    private List<Programa> list;
    private static final String NAME_FILE = "programs.txt";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Files/" + NAME_FILE;
    private File file = new File(ROUTE_FILE);

    @Override
    public List<Programa> GetAll() {
        return list;
    }

    @Override
    public Programa Save(Programa entity) {
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            writer.write(entity.toString() + "\r\n");
            writer.close();

            System.out.println("Program added successfully");
            return entity;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding the program");

            throw new RuntimeException("Error adding the program");
        }
    }

    @Override
    public Programa Update(Programa entity) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            StringBuilder content = new StringBuilder();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Program" + "{id=" + entity.getId())) {
                    lineRead = entity.toString();
                }
                content.append(lineRead).append("\r\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error updating the program");

            throw new RuntimeException("Error updating the program");
        }
    }

    @Override
    public void Delete(int id) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            StringBuilder content = new StringBuilder();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (!lineRead.contains("Program" + "{id=" + id)) {
                    content.append(lineRead).append("\r\n");
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();

            System.out.println("Program deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error deleting the program");

            throw new RuntimeException("Error eliminando el programa");
        }
    }
}
