package com.CleanCode.Student.RepositoryBase.Database;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Departamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCrudDb implements ICRUD<Departamento> {

    private final Connection connection;


    public DepartmentCrudDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Departamento> GetAll() {
        String query = "SELECT * FROM departamento";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(); // Execute the query

            List<Departamento> departamentos = new ArrayList<>(); // Create an empty list
            while (resultSet.next()) {  // Iterate through the results
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                Departamento departamento = new Departamento(id, nombre); // Create a Departamento object
                departamentos.add(departamento); // Add it to the list
            }

            return departamentos; // Return the list of departments
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public Departamento Save(Departamento entity) {
        String query = "INSERT INTO departamento (id, nombre) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();

            System.out.println("Departamento created successfully.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al crear el Departamento.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Departamento Update(Departamento entity) {
        String query = "UPDATE departamento SET nombre = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();

            System.out.println("Departamento updated successfully.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al actualizar el Departamento.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) {
        String query = "DELETE FROM departamento WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Departamento deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el Departamento.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
