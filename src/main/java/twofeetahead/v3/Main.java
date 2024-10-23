package twofeetahead.v3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// v3 of the shopping app begins to break apart the Main class.
//
// For Java applications, the fundamental "unit" is the class, rather
// than the method. This explains why "unit tests" are written around a
// single class, covering its entire public interface.
//
// Each class lives in its own dedicated file, which also reduces merge
// conflicts; if a developer is working on a single piece of
// functionality, they will often not need to touch other files.

public class Main {
    public static void main(String[] args) {
        final var cli = new CLI();
        final String name = cli.getName();

        final var greeter = new StoreGreeter();
        greeter.greet(name);

        final List<Product> bag = getBag();
        final var catalog = new Catalog(getProducts());

        while (cli.getIntent().isPresent()) {
            for (Optional<Category> dept; (dept = cli.getDepartment()).isPresent(); ) {
                cli.fill(bag, catalog.find(dept.get()));
            }
        }

        if (!bag.isEmpty()) {
            final var cashier = new StoreCashier();

            cashier.checkout(name, bag);
        }
    }

    private static List<Product> getProducts() {
        return List.of(
            new Product("trilby", Category.Headgear, new BigDecimal("49.99")),
            new Product("baseball cap", Category.Headgear, new BigDecimal("22.00")),
            new Product("top hat", Category.Headgear, new BigDecimal("160.00")),
            new Product("fez", Category.Headgear, new BigDecimal("45.00")),
            new Product("deerstalker", Category.Headgear, new BigDecimal("100.00")),

            new Product("plimsolls", Category.Footwear, new BigDecimal("69.99")),
            new Product("sneakers", Category.Footwear, new BigDecimal("140.00")),
            new Product("wellington boots", Category.Footwear, new BigDecimal("100.00")),
            new Product("walking boots", Category.Footwear, new BigDecimal("209.95")),
            new Product("penny loafers", Category.Footwear, new BigDecimal("316.00"))
        );
    }

    private static List<Product> getBag() {
        return new ArrayList<>();
    }
}
