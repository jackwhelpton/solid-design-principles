package twofeetahead.v5;

import java.math.BigDecimal;
import java.util.List;

// v5 of the app defines interfaces for the application classes.
//
// This allows the behavior of the application to be changed by swapping in
// alternative implementations; for example, a Cashier that applies a 33%
// discount to every purchase. No changes to existing classes are needed,
// excepting only Main (the composition root).
//
// In addition, the test suite can now be completed, as those alternative
// implementations can include mocks created for the purpose of testing.
//
// This is an example of the "Open-Closed Principle", the "O" in "SOLID".
// The Open-Closed Principle states that code should be open for
// extension, but closed for modification.

public class Main {
    public static void main(String[] args) {
        new Application(
            new CLI(System.in, System.out),
            new StoreGreeter(System.out),
            new Bag(),
            new Catalog(getProducts()),
            new StoreCashier(System.out)
        ).Run();
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
}
