package twofeetahead.v5;

import java.util.List;
import java.util.stream.StreamSupport;

public class Catalog implements Browser<Product, Category> {
    private final List<Product> products;

    public Catalog(Iterable<Product> products) {
        this.products = StreamSupport.stream(products.spliterator(), false).toList();
    }

    public List<Product> browse(Category c) {
        return products
            .stream()
            .filter(p -> p.category() == c)
            .toList();
    }
}
