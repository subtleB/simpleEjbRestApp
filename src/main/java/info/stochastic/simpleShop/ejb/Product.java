package info.stochastic.simpleShop.ejb;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {

    protected int id;

    // Just a safe way to generate unique identifiers
    protected static AtomicInteger idGen = new AtomicInteger();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id == 0 ? idGen.incrementAndGet() : id;
    }
}
