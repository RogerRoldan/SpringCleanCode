package com.CleanCode.Student.Repository;

import com.CleanCode.Student.Interfaces.IRespository;
import com.CleanCode.Student.Models.Estudiante;
import com.CleanCode.Student.RepositoryBase.File.StudentCrudFile;

import java.util.List;

public class StudentRepository implements IRespository<Estudiante> {

    StudentCrudFile estudentCrudFile = new StudentCrudFile();

    @Override
    public Estudiante Create(Estudiante entity) {
        estudentCrudFile.Save(entity);
        return entity;
    }

    @Override
    public List<Estudiante> GetAll() {
        return estudentCrudFile.GetAll();
    }

    @Override
    public Estudiante Update(Estudiante entity) {
        estudentCrudFile.Update(entity);
        return entity;
    }

    @Override
    public void Delete(int id) {
        estudentCrudFile.Delete(id);
    }
}
