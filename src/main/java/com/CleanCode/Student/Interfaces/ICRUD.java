package com.CleanCode.Student.Interfaces;

import java.util.List;

public interface ICRUD<T> {
    public List<T> GetAll();
    public T Save(T entity);
    public T Update(T entity);
    public void Delete(int id);


}
