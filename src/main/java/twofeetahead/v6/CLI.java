package twofeetahead.v6;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CLI implements UI {
    private final Scanner scanner;
    private final PrintStream out;

    public CLI(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public String getName() {
        out.print("Welcome to the store, please enter your name: ");

        return scanner.nextLine();
    }

    public Optional<Intent> getIntent() {
        out.println("Please choose from the following options:");
        out.println("[1] Browse by department");
        out.println("[2] Pay up and get out");

        return switch (scanner.next()) {
            case "1" -> Optional.of(Intent.Browse);
            case "2" -> Optional.empty();
            default -> throw new InputMismatchException();
        };
    }

    public Optional<Category> getDepartment() {
        out.println("Please choose a department from the following list:");
        out.println("[1] Headgear");
        out.println("[2] Footwear");
        out.println("[3] Back to previous menu");

        return switch (scanner.next()) {
            case "1" -> Optional.of(Category.Headgear);
            case "2" -> Optional.of(Category.Footwear);
            case "3" -> Optional.empty();
            default -> throw new InputMismatchException();
        };
    }

    public Optional<Product> getProduct(List<Product> products) {
        out.println("Please choose an item to add to your bag:");

        for (int i = 0; i < products.size(); i++) {
            out.printf("[%s] %s\n", i + 1, products.get(i).name());
        }

        out.printf("[%s] Back to previous menu\n", products.size() + 1);

        final int choice = scanner.nextInt();
        if (choice == products.size() + 1) {
            return Optional.empty();
        }

        if (choice < 1 || choice > products.size()) {
            throw new InputMismatchException();
        }

        return Optional.of(products.get(choice - 1));
    }
}
