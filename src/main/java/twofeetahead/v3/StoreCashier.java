package twofeetahead.v3;

import java.math.BigDecimal;
import java.util.Collection;

public class StoreCashier {
    public void checkout(String name, Collection<Product> bag) {
        final BigDecimal total = bag.stream()
            .map(Product::price)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("Billing %s %.2f", name, total);
    }
}
