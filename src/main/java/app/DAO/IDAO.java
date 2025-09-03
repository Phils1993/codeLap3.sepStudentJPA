package app.DAO;

import app.entities.Teacher;

import java.util.List;

public interface IDAO<T,I> {

    T create(T t);

    T getById(I id);

    T update(T t);

    boolean delete(I id);

    List<T> getAll();
}
