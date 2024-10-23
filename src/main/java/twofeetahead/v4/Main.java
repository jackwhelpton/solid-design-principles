package twofeetahead.v4;

import java.math.BigDecimal;
import java.util.List;

// v4 of the app continues the march to testability.
//
// By adding arguments to the class constructors that allow us to supply
// their dependencies, we can set the classes up as required in order to
// write unit tests.
//
// This is our first glimpse of the "Dependency Inversion Principle", the
// "D" in "SOLID"; rather than higher-level classes relying on low-level
// dependencies, they are passed in ("injected") at construction time, so
// they can be swapped out for testing purposes.

// At the end of this refactor, unit tests are still missing from the
// Application and Cashier classes; this is because although their
// dependencies have been "inverted", they are still taking concrete
// implementations that are not easily mocked.
//
// v5 will address this by defining interfaces that can be used to mock
// their dependencies.
//
// Eagle-eyed readers will note the Main class is also uncovered by tests.
// This is referred to as the "composition root"; its sole function should
// be "wiring up" of dependencies, with no business logic, so this is not
// necessarily a problem. If Main coverage is desired, this should be done
// by integration tests.

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
