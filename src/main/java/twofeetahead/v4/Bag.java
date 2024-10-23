package twofeetahead.v4;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bag extends ArrayList<Product> {
    public BigDecimal getTotal() {
        return stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
