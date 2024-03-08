package com.CleanCode.Student.RepositoryBase.File;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Departamento;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCrudFile implements ICRUD<Departamento> {

    private List<Departamento> list;
    private static final String NAME_FILE = "departments.txt";
    private static final String PATH_RELATIVE_PROJECT = System.getProperty("user.dir");
    private static final String ROUTE_FILE = PATH_RELATIVE_PROJECT + "/src/main/resources/Files/" + NAME_FILE;
    private File file = new File(ROUTE_FILE);

    @Override
    public List<Departamento> GetAll() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            List<Departamento> list = new ArrayList<>();
            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                Departamento department = convertLineToObject(lineRead, Departamento.class);
                list.add(department);
            }

            for (Departamento departamento : list) {
                System.out.println(departamento.getId() + " " + departamento.getNombre());
            }

            return list;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the file: " + e.getMessage());
            throw new RuntimeException("Error al leer el archivo");
        }




    }

    @Override
    public Departamento Save(Departamento department) {

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
            writer.write(department.toString() + "\r\n");
            writer.close();

            System.out.println("Department added successfully");
            return department;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error adding the department");
            throw new RuntimeException("Error al agregar el departamento");
        }

    }

    @Override
    public Departamento Update(Departamento department) {

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            StringBuilder content = new StringBuilder();

            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Department" + "{id=" + department.getId())) {
                    lineRead = department.toString();
                }
                content.append(lineRead).append("\r\n");
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            writer.write(content.toString());
            writer.close();

            System.out.println("Department updated successfully");
            return department;

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Error updating the department");

            throw new RuntimeException("Error al actualizar el departamento");
        }

    }

    @Override
    public void Delete(int id) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            StringBuilder content = new StringBuilder();

            String lineRead;

            while ((lineRead = reader.readLine()) != null) {
                if (lineRead.contains("Department" + "{id=" + id)) {
                    System.out.println(lineRead);
                    System.out.println("Department deleted successfully");
                }
                else {
                    content.append(lineRead).append("\r\n");
                }
            }

            reader.close();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            writer.write(content.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

    }

    private static <T> T convertLineToObject(String linea, Class<T> clase) {

        // Crear una nueva instancia de la clase
        T objeto = null;
        try {
            objeto = clase.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        // Dividir la línea en campos separados por comas
        String[] campos = linea.split(",");

        // Recorrer los campos y asignarlos a los atributos del objeto
        for (String campo : campos) {

            //Quitar prefijo Department{
            if(campo.startsWith("Department{")){
                campo = campo.substring(11);
            }

            //Quitarllave final
            if(campo.endsWith("}")){
                campo = campo.substring(0, campo.length()-1);
            }

            // Obtener el nombre del atributo
            String nombreAtributo = campo.split("=")[0];

            // Obtener el valor del atributo
            String valorAtributo = campo.split("=")[1];

            // Buscar el setter del atributo
            try {

                //convertir el primer caracter a mayuscula
                String nombreAtributoUpper = nombreAtributo.substring(0, 1).toUpperCase() + nombreAtributo.substring(1);

                //si el atributo es de tipo entero o string
                if(valorAtributo.matches("[0-9]+")){
                    Method setterInt = clase.getMethod("set" + nombreAtributoUpper, int.class);
                    setterInt.invoke(objeto, Integer.parseInt(valorAtributo));
                }else if(valorAtributo.matches("[a-zA-Z]+")){
                    Method setter = clase.getMethod("set" + nombreAtributoUpper, String.class);
                    setter.invoke(objeto, valorAtributo);
                }

            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException |
                     InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return objeto;

    }

}
