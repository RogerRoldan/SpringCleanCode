package com.CleanCode.Student.Models;

import com.CleanCode.Student.Models.VisualizarInformacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListaObjetosInformacion {
    private List<VisualizarInformacion> lista;

    public void  adicionar(VisualizarInformacion objeto) {
            lista.add(objeto);
    }

    public void remover(VisualizarInformacion objeto) {
        lista.remove(objeto);
    }

    public static <T> List<T> Cargar(String rutaArchivo, Class<T> clase) {

        List<String> lineas = leerArchivo(rutaArchivo);

        List<T> objetos = new ArrayList<T>();

        for (String linea : lineas) {
            T objeto = convertirLineaObjeto(linea, clase);
            objetos.add(objeto);
        }

        return objetos;
    }

    private static <T> T convertirLineaObjeto(String linea, Class<T> clase) {

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

            //Quitar llaves al inicio y final de la cadena si existen
            if(campo.startsWith("{")){
                campo = campo.substring(1);
            }
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

    private static List<String> leerArchivo(String rutaArchivo) {

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(rutaArchivo));

            List<String> lineas = new ArrayList<String>();
            String linea;

            while ((linea = buffer.readLine()) != null) {
                lineas.add(linea);
            }

            buffer.close();

            return lineas;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
