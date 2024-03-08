package com.CleanCode.Student.RepositoryBase.Database;

import com.CleanCode.Student.Interfaces.ICRUD;
import com.CleanCode.Student.Models.Lugar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlaceCrudDb implements ICRUD<Lugar> {

    private final Connection connection;

    public PlaceCrudDb(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Lugar> GetAll() {
        String query = "SELECT * FROM lugar";
        return null;
    }

    @Override
    public Lugar Save(Lugar entity) {
        String query = "INSERT INTO lugar (id, direccion, idDepartamento, idMunicipio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getDireccion());
            statement.setInt(3, entity.getDepartamento().getId());
            statement.setInt(4, entity.getMunicipio().getId());
            statement.executeUpdate();

            System.out.println("Lugar creado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al crear el Lugar.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Lugar Update(Lugar entity) {
        String query = "UPDATE lugar SET direccion = ?, idDepartamento = ?, idMunicipio = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getDireccion());
            statement.setInt(2, entity.getDepartamento().getId());
            statement.setInt(3, entity.getMunicipio().getId());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();

            System.out.println("Lugar actualizado con éxito.");

            return entity;

        } catch (Exception e) {
            System.out.println("Error al actualizar el Lugar.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Delete(int id) {
        String query = "DELETE FROM lugar WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();

            System.out.println("Lugar eliminado con éxito.");

        } catch (Exception e) {
            System.out.println("Error al eliminar el Lugar.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
