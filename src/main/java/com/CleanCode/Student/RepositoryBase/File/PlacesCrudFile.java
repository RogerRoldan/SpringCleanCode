package com.CleanCode.Student.RepositoryBase.File;
import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Lugar;

import java.util.List;
import java.io.*;
public class PlacesCrudFile implements ICRUD<Lugar> {

    private List<Lugar> places;
    private static final String NAME_FILE = "places.txt";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Files/" + NAME_FILE;
    private File file = new File(ROUTE_FILE);


    @Override
    public List<Lugar> GetAll() {
        return places;
    }

    @Override
    public Lugar Save(Lugar entity) {
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            writer.write(entity.toString() + "\r\n");
            writer.close();

            System.out.println("Place added successfully");
            return entity;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error adding the place");

            throw new RuntimeException("Error al agregar el lugar");
        }
    }

    @Override
    public Lugar Update(Lugar entity) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            StringBuilder content = new StringBuilder();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Place" + "{id=" + entity.getId())) {
                    lineRead = entity.toString();
                }
                content.append(lineRead).append("\r\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();
            return entity;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating the place");

            throw new RuntimeException("Error al actualizar el lugar");
        }
    }

    @Override
    public void Delete(int id) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            StringBuilder content = new StringBuilder();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (!lineRead.contains("Place" + "{id=" + id)) {
                    content.append(lineRead).append("\r\n");
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();

            System.out.println("Place deleted successfully");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error deleting the place");

            throw new RuntimeException("Error eliminando el lugar");
        }
    }
}

