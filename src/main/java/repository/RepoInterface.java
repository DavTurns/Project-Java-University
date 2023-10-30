package repository;

import java.util.List;

public interface RepoInterface<T> {
    public void create(T item);
    public T read(int id) throws Exception;
    public void update(T item) throws Exception;
    public void delete(int id) throws Exception;
    public List<T> readAll();
}
