package twofeetahead.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

// v2 of the shopping app begins to break up the monolithic Main method.
//
// The easiest way to do this is to create separate methods for each of
// the code blocks that were identifiable owing to the introductory
// comments.
//
// Note that those comments have now been removed, as the benefit they
// provided is fully replicated through the use of appropriately named
// comments.
//
// This is our first glimpse of the Single Responsibility Principle, the
// "S" in "SOLID"; each "unit" of code should perform a single function.
// The term "unit" is intentionally left ambiguous; that will be
// addressed further in v3.

public class Main {
    public static void main(String[] args) {
        final Scanner cli = getCLI();
        final Collection<Product> catalog = getCatalog();

        final String name = identifyShopper(cli);
        greet(name);

        final List<Product> bag = getBag();

        while (getIntent(cli).isPresent()) {
            for (Optional<Category> dept; (dept = getDepartment(cli)).isPresent(); ) {
                fill(bag, find(dept.get(), catalog), cli);
            }
        }

        if (!bag.isEmpty()) {
            checkout(name, bag);
        }
    }

    private static Scanner getCLI() {
        return new Scanner(System.in);
    }

    private static List<Product> getCatalog() {
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

    private static String identifyShopper(Scanner cli) {
        System.out.print("Welcome to the store, please enter your name: ");

        return cli.next();
    }

    private static void greet(String name) {
        System.out.printf("Hello, %s. ", name);
    }

    private static List<Product> getBag() {
        return new ArrayList<>();
    }

    private static Optional<Intent> getIntent(Scanner cli) {
        for (;;) {
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Browse by department");
            System.out.println("[2] Pay up and get out");

            switch (cli.next()) {
                case "1":
                    return Optional.of(Intent.Browse);
                case "2":
                    return Optional.empty();
            }
        }
    }

    private static void checkout(String name, Collection<Product> bag) {
        final BigDecimal total = bag.stream().map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Billing %s %.2f", name, total);
    }

    private static Optional<Category> getDepartment(Scanner cli) {
        for (;;) {
            System.out.println("Please choose a department from the following list:");
            System.out.println("[1] Headgear");
            System.out.println("[2] Footwear");
            System.out.println("[3] Back to previous menu");

            switch (cli.next()) {
                case "1":
                    return Optional.of(Category.Headgear);
                case "2":
                    return Optional.of(Category.Footwear);
                case "3":
                    return Optional.empty();
            }
        }
    }

    private static void fill(List<Product> bag, List<Product> products, Scanner cli) {
        for (;;) {
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

    private static List<Product> find(Category dept, Collection<Product> catalog) {
        return catalog
            .stream()
            .filter(p -> p.category() == dept)
            .toList();
    }

    private enum Intent { Browse }
}
