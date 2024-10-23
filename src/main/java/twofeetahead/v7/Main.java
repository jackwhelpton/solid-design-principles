package twofeetahead.v7;

import java.math.BigDecimal;
import java.util.List;

// v7 of the app swaps in new CLI behavior and Cashier behavior to emulate
// a discount store.
//
// Everything is offered at a 10% discount, but there are no separate
// departments; all the stock is one big discount box, and it is up to
// customers to sort through it to find the good deals. There is no
// greeting, as peasants do not get treated with courtesy.
//
// The first attempt throws exceptions for unsupported methods, which
// would require application changes to handle successfully. Even once
// that is addressed, returning a default department from the CLI traps
// the shopper in an infinite loop, unable to leave the store.
//
// These problems are addressed by using the "well-behaved" versions of the
// classes, also included in the source.
//
// This is an example of the Liskov Substitution Principle, the "L" in
// "SOLID". This principle requires that replacing any class with a subclass
// (or, in this case, any interface with a particular implementation) should
// not result in adverse effects.

public class Main {
    public static void main(String[] args) {
        new Application(
            new NaughtyDiscountCLI(System.in, System.out),
            //new DiscountCLI(System.in, System.out),
            new NaughtyDiscountGreeter(),
            //new DiscountGreeter(),
            new Bag(),
            new DiscountCatalog(getProducts()),
            new DiscountCashier(System.out)
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
