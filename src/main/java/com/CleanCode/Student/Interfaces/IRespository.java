package com.CleanCode.Student.Interfaces;

import java.util.List;

public interface IRespository<T> {
    T Create(T entity);
    public List<T> GetAll();
    public T Update(T entity);
    public void Delete(int id);
}
