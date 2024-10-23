package twofeetahead.v5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
    @Test
    public void testRun() {
        final var greeted = new ArrayList<String>();
        final var billed = new HashMap<String, BigDecimal>();

        final List<Product> shoes = List.of(
            new Product("a", Category.Footwear, new BigDecimal("123.12")),
            new Product("c", Category.Footwear, new BigDecimal("2.47"))
        );

        final List<Product> hats = List.of(new Product("b", Category.Headgear, new BigDecimal("100.99")));

        final var app = new Application(
            new ShoeBuyer(),
            greeted::add,
            new Bag(),
            dept -> dept == Category.Footwear ? shoes : hats,
            (name, bag) -> billed.put(name, bag.getPrice()));

        app.Run();

        assertEquals(List.of("Fi Barr"), greeted);
        assertEquals(Map.of("Fi Barr", new BigDecimal("125.59")), billed);
    }

    private static class ShoeBuyer implements UI {
        private boolean done;

        @Override
        public String getName() {
            return "Fi Barr";
        }

        @Override
        public Optional<Intent> getIntent() {
            return done ? Optional.empty() : Optional.of(Intent.Browse);
        }

        @Override
        public Optional<Category> getDepartment() {
            return done ? Optional.empty() : Optional.of(Category.Footwear);
        }

        @Override
        public void fill(Collection<Product> bag, List<Product> products) {
            bag.addAll(products);
            done = true;
        }
    }

    private static class Bag extends ArrayList<Product> implements PriceableCollection<Product> {
        @Override
        public BigDecimal getPrice() {
            return stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}
