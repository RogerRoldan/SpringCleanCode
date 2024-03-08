package com.CleanCode.Student.RepositoryBase.Database;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Departamento;
import com.CleanCode.Student.Models.Municipio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MunicipalityCrudDb implements ICRUD<Municipio> {

    private final Connection connection;

    public MunicipalityCrudDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Municipio> GetAll() {
        String query = "SELECT * FROM municipio";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(); // Execute the query

            List<Municipio> municipios = new ArrayList<>(); // Create an empty list
            while (resultSet.next()) {  // Iterate through the results
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                Departamento idDepartamento = new Departamento(resultSet.getInt("idDepartamento"), ""); // Create a Departamento object
                Municipio municipio = new Municipio(id, nombre, idDepartamento); // Create a Municipio object
                municipios.add(municipio); // Add it to the list
            }

            return municipios; // Return the list of departments
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Municipio Save(Municipio entity) {
        String query = "INSERT INTO municipio (id, nombre, idDepartamento) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getNombre());
            statement.setInt(3, entity.getDepartamento().getId());
            statement.executeUpdate();

            System.out.println("Municipio created successfully.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al crear el Municipio.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Municipio Update(Municipio entity) {
        String query = "UPDATE municipio SET nombre = ?, idDepartamento = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setInt(2, entity.getDepartamento().getId());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();

            System.out.println("Municipio updated successfully.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al actualizar el Municipio.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) {
        String query = "DELETE FROM municipio WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Municipio deleted successfully.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el Municipio.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
