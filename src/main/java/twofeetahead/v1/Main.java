package twofeetahead.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// v1 of the shopping app, Make It Work™ edition; one large method
// contains the entire application.
//
// There are multiple disadvantages to this approach, but the main ones
// are readability, maintainability and testability.
//
// When reading this sort of code, a common feature is that it is broken
// into blocks, with a single-line comment explaining the function of
// each block. Such descriptive comments are a sign of code that probably
// should be more self-describing.
//
// When maintaining this sort of code, multiple developers will
// frequently be "fighting" over the same file, with merge conflicts a
// common result.
//
// Testing such code (other than manually) is nigh on impossible, as the
// only entry point is the main method.

public class Main {
    public static void main(String[] args) {
        // Prepare command-line interface
        final var cli = new Scanner(System.in);

        // Prepare catalog
        final List<Product> catalog = List.of(
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

        // Identify shopper
        System.out.print("Welcome to the store, please enter your name: ");
        final String name = cli.next();

        // Greet shopper
        System.out.printf("Hello, %s.", name);

        // Give shopper an empty bag
        final var bag = new ArrayList<Product>();

        for (;;) {
            // Determine shopper's intent
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Browse by department");
            System.out.println("[2] Pay up and get out");

            switch (cli.next()) {
                case "1":
                    for (;;) {
                        // Show shopper the store map
                        System.out.println("Please choose a department from the following list:");
                        System.out.println("[1] Headgear");
                        System.out.println("[2] Footwear");
                        System.out.println("[3] Back to previous menu");

                        boolean done = false;
                        final Category category;

                        switch (cli.next()) {
                            case "1":
                                category = Category.Headgear;
                                break;
                            case "2":
                                category = Category.Footwear;
                                break;
                            case "3":
                                category = null;
                                done = true;
                                break;
                            default:
                                continue;
                        }

                        if (done) {
                            break;
                        }

                        final List<Product> products = catalog
                            .stream()
                            .filter(p -> p.category() == category)
                            .toList();

                        for (;;) {
                            // Show shopper the shelves
                            System.out.println("Please choose an item to add to your bag:");

                            for (int i = 0; i < products.size(); i++) {
                                System.out.printf("[%s] %s\n", i + 1, products.get(i).name());
                            }

                            System.out.printf("[%s] Back to previous menu\n", products.size() + 1);

                            if (!cli.hasNextInt()) {
                                continue;
                            }

                            final int choice = cli.nextInt();
                            if (choice == products.size() + 1) {
                                break;
                            }

                            if (choice < 1 || choice > products.size()) {
                                continue;
                            }

                            bag.add(products.get(choice - 1));
                        }
                    }

                    break;
                case "2":
                    // Charge shopper for contents of bag
                    final BigDecimal total = bag.stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add);
                    System.out.printf("Billing %s %.2f", name, total);
                    return;
            }
        }
    }
}
