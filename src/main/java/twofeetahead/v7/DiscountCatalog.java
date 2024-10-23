package twofeetahead.v7;

import java.util.List;
import java.util.stream.StreamSupport;

public class DiscountCatalog implements Browser<Product, Category> {
    private final List<Product> products;

    public DiscountCatalog(Iterable<Product> products) {
        this.products = StreamSupport.stream(products.spliterator(), false).toList();
    }

    public List<Product> browse(Category dept) {
        return products;
    }
}
