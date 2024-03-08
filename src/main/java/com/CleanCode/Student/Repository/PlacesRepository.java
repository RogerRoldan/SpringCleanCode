package com.CleanCode.Student.Repository;

import com.CleanCode.Student.Interfaces.IRespository;
import com.CleanCode.Student.Models.Lugar;
import com.CleanCode.Student.RepositoryBase.File.PlacesCrudFile;

import java.util.List;

public class PlacesRepository implements IRespository<Lugar> {

    PlacesCrudFile placesCrudFile = new PlacesCrudFile();

    @Override
    public Lugar Create(Lugar entity) {
        placesCrudFile.Save(entity);
        return entity;
    }

    @Override
    public List<Lugar> GetAll() {
        return placesCrudFile.GetAll();
    }

    @Override
    public Lugar Update(Lugar entity) {
        placesCrudFile.Update(entity);
        return entity;
    }

    @Override
    public void Delete(int id) {
        placesCrudFile.Delete(id);
    }
}
