package info.stochastic.simpleShop.ejb;

import javax.ejb.Stateful;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Stateful
public class Repository<T extends Product> {

    // Using HashMap for O(1) getById operations
    private Map<Integer, T> items = new HashMap<Integer, T>();

    public void add(T item) {
        items.put(item.getId(), item);
    }

    public void remove(T item) {
        items.remove(item.getId());
    }

    public T getById(int id) {
        return items.get(id);
    }

    public boolean contains(T item) {
        return items.containsKey(item.getId());
    }

    public List<T> getItems() {
        return new ArrayList<T>(items.values());
    }
}

