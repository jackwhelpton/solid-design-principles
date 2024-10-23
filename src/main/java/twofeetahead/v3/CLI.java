package twofeetahead.v3;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CLI {
    private final Scanner scanner;

    public CLI() {
        scanner = new Scanner(System.in);
    }

    public String getName() {
        System.out.print("Welcome to the store, please enter your name: ");

        return scanner.next();
    }

    public Optional<Intent> getIntent() {
        for (;;) {
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Browse by department");
            System.out.println("[2] Pay up and get out");

            switch (scanner.next()) {
                case "1":
                    return Optional.of(Intent.Browse);
                case "2":
                    return Optional.empty();
            }
        }
    }

    public Optional<Category> getDepartment() {
        for (;;) {
            System.out.println("Please choose a department from the following list:");
            System.out.println("[1] Headgear");
            System.out.println("[2] Footwear");
            System.out.println("[3] Back to previous menu");

            switch (scanner.next()) {
                case "1":
                    return Optional.of(Category.Headgear);
                case "2":
                    return Optional.of(Category.Footwear);
                case "3":
                    return Optional.empty();
            }
        }
    }

    public void fill(List<Product> bag, List<Product> products) {
        for (;;) {
            System.out.println("Please choose an item to add to your bag:");

            for (int i = 0; i < products.size(); i++) {
                System.out.printf("[%s] %s\n", i + 1, products.get(i).name());
            }

            System.out.printf("[%s] Back to previous menu\n", products.size() + 1);

            if (!scanner.hasNextInt()) {
                continue;
            }

            final int choice = scanner.nextInt();
            if (choice == products.size() + 1) {
                break;
            }

            if (choice < 1 || choice > products.size()) {
                continue;
            }

            bag.add(products.get(choice - 1));
        }
    }

    public enum Intent { Browse }
}
