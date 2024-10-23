package twofeetahead.v3;

import java.util.List;
import java.util.stream.StreamSupport;

public class Catalog {
    private final List<Product> products;

    public Catalog(Iterable<Product> products) {
        this.products = StreamSupport.stream(products.spliterator(), false).toList();
    }

    public List<Product> find(Category dept) {
        return products
            .stream()
            .filter(p -> p.category() == dept)
            .toList();
    }
}
