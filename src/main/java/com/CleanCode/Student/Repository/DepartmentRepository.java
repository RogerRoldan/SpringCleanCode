package com.CleanCode.Student.Repository;

import com.CleanCode.Student.Interfaces.IRespository;
import com.CleanCode.Student.Models.Departamento;
import com.CleanCode.Student.Persistence.Database.ConnectionDatabase;
import com.CleanCode.Student.RepositoryBase.Database.DepartmentCrudDb;
import com.CleanCode.Student.RepositoryBase.File.DepartmentCrudFile;

import java.sql.Connection;
import java.util.List;

public class DepartmentRepository implements IRespository<Departamento> {

    ConnectionDatabase connectionDatabase = ConnectionDatabase.getInstance();
    Connection connection = connectionDatabase.getConnection();
    DepartmentCrudFile departmentCrudFile = new DepartmentCrudFile();
    DepartmentCrudDb departmentCrudDb = new DepartmentCrudDb(connection);

    @Override
    public Departamento Create(Departamento entity) {

        departmentCrudFile.Save(entity);
        departmentCrudDb.Save(entity);

        return entity;
    }

    @Override
    public List<Departamento> GetAll() {
        List<Departamento> departamentos = departmentCrudFile.GetAll();
        return departamentos;
    }

    @Override
    public Departamento Update(Departamento entity) {
        departmentCrudFile.Update(entity);
        return entity;
    }

    @Override
    public void Delete(int id) {
        departmentCrudFile.Delete(id);
    }
}
