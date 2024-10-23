package twofeetahead.v6;

import java.math.BigDecimal;
import java.util.List;

// v6 of the app begins to break apart the interfaces.
//
// This ensures that clients are not compelled to implement methods they
// do not require. It will substantially simplify the testing code for the
// bag, which can then use lambda expressions rather than defining a
// dedicated class.
//
// This is an example of the "Interface Segregation Principle", the "I" in
// "SOLID". It can be thought of as an extension of the Single
// Responsibility Principle to interfaces; every interface should define
// a minimal set of functionality.

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
