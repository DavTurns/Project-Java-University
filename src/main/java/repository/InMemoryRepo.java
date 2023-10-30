package repository;

import models.Idmethods;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepo<T extends Idmethods> implements RepoInterface<T> {
    private List<T> data = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void create(T item) {
        // Assign a unique identifier

        item.setId(nextId++);


        data.add(item);
    }

    @Override
    public T read(int id) throws Exception {
        for (T item : data) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new Exception("Item not found...");
    }

    public List<T> readAll() {
        return new ArrayList<>(data);
    }

    @Override
    public void update(T item) throws Exception {
        int id = item.getId();
        for (int i = 0; i < data.size(); i++) {
            T existingItem = data.get(i);
            if (existingItem.getId() == id) {
                data.set(i, item);
                return;
            }
        }
        throw new Exception("Item not found...");
    }

    @Override
    public void delete(int id) throws Exception {
        if (!data.removeIf(item -> item.getId() == id))
            throw new Exception("Item not found...");
    }
}
