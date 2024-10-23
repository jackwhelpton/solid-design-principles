package twofeetahead.v6;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bag extends ArrayList<Product> implements PriceableCollection<Product> {
    public BigDecimal getPrice() {
        return stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
