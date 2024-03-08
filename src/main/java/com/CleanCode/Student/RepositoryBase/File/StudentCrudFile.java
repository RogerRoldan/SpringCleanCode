package com.CleanCode.Student.RepositoryBase.File;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Estudiante;

import java.io.*;
import java.util.List;

public class StudentCrudFile implements ICRUD<Estudiante> {

    private List<Estudiante> list;
    private static final String NAME_FILE = "students.txt";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Files/" + NAME_FILE;
    private File file = new File(ROUTE_FILE);

    @Override
    public List<Estudiante> GetAll() {
        return list;
    }

    @Override
    public Estudiante Save(Estudiante student) {
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            writer.write(student.toString() + "\r\n");
            writer.close();

            System.out.println("Student added successfully");
            return student;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error adding the student");

            throw new RuntimeException("Error al agregar el estudiante");
        }
    }

    @Override
    public Estudiante Update(Estudiante student) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            StringBuilder content = new StringBuilder();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Estudiante" + "{id=" + student.getId())) {
                    lineRead = student.toString();
                }
                content.append(lineRead).append("\r\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();

            System.out.println("Estudiante actualizado exitosamente");
            return student;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el estudiante");

            throw new RuntimeException("Error al actualizar el estudiante");
        }
    }

    @Override
    public void Delete(int id) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            StringBuilder content = new StringBuilder();

            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Estudiante" + "{id=" + id)) {
                    System.out.println(lineRead);
                    System.out.println("Estudiante eliminado exitosamente");
                }
                else {
                    content.append(lineRead).append("\r\n");
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el estudiante");

            throw new RuntimeException("Error al eliminar el estudiante");
        }
    }


}
