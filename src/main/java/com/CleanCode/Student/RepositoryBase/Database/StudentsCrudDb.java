package com.CleanCode.Student.RepositoryBase.Database;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class StudentsCrudDb implements ICRUD<Estudiante>{

    private final Connection connection;

    public StudentsCrudDb(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Estudiante> GetAll() {
        return null;
    }

    @Override
    public Estudiante Save(Estudiante entity) {
        String query = "INSERT INTO estudiante (id, nombre, apellido, edad, idPrograma) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNombres());
            statement.setString(3, entity.getApellidos());
            statement.setInt(4, entity.getEdad());
            statement.setInt(5, entity.getPrograma().getId());
            statement.setInt(6, entity.getDireccion().getId());
            statement.executeUpdate();

            System.out.println("Estudiante creado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al crear el Estudiante.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Estudiante Update(Estudiante entity) {
        String query = "UPDATE estudiante SET nombre = ?, apellido = ?, edad = ?, idPrograma = ?, idDireccion = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombres());
            statement.setString(2, entity.getApellidos());
            statement.setInt(3, entity.getEdad());
            statement.setInt(4, entity.getPrograma().getId());
            statement.setInt(5, entity.getDireccion().getId());
            statement.setInt(6, entity.getId());
            statement.executeUpdate();

            System.out.println("Estudiante actualizado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al actualizar el Estudiante.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) {
        String query = "DELETE FROM estudiante WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Estudiante eliminado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el Estudiante.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
