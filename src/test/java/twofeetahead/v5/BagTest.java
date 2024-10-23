package twofeetahead.v5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class BagTest {
    @Test
    public void testTotal() {
        final var bag = new Bag();
        bag.add(new Product("a", Category.Footwear, new BigDecimal("123.12")));
        bag.add(new Product("b", Category.Headgear, new BigDecimal("100.99")));
        bag.add(new Product("c", Category.Footwear, new BigDecimal("2.47")));

        final BigDecimal total = bag.getPrice();

        assertEquals(new BigDecimal("226.58"), total);
    }
}
