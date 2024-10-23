package twofeetahead.v4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CatalogTest {
    @Test
    public void testFind() {
        final var catalog = new Catalog(List.of(
            new Product("a", Category.Footwear, new BigDecimal("123.12")),
            new Product("b", Category.Headgear, new BigDecimal("100.99")),
            new Product("c", Category.Footwear, new BigDecimal("2.47"))
        ));

        final List<Product> results = catalog.find(Category.Footwear);

        assertEquals(List.of(
            new Product("a", Category.Footwear, new BigDecimal("123.12")),
            new Product("c", Category.Footwear, new BigDecimal("2.47"))
            ), results);
    }
}
