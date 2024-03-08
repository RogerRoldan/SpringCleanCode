package com.CleanCode.Student.RepositoryBase.Database;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Programa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ProgramCrudDb implements ICRUD<Programa>{

    private final Connection connection;

    public ProgramCrudDb(Connection connection) {
        this.connection = connection;
}
    @Override
    public List<Programa> GetAll() {
        return null;
    }

    @Override
    public Programa Save(Programa entity) {
        String query = "INSERT INTO programa (id, nombre, idFacultad) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setInt(3, entity.getSemestres());
            statement.setInt(4, entity.getDireccion().getId());
            statement.executeUpdate();

            System.out.println("Programa creado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al crear el Programa.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Programa Update(Programa entity) {
        String query = "UPDATE programa SET nombre = ?, idFacultad = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setInt(2, entity.getSemestres());
            statement.setInt(3, entity.getDireccion().getId());
            statement.executeUpdate();

            System.out.println("Programa actualizado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al actualizar el Programa.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) {
        String query = "DELETE FROM programa WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Programa eliminado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el Programa.");
            e.printStackTrace();
            throw new RuntimeException(e);

    }}
}
