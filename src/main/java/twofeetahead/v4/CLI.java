package twofeetahead.v4;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class CLI {
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

    public Stream<Intent> getIntents() {
        return Stream.generate(this::getIntent).takeWhile(Optional::isPresent).map(Optional::get);
    }

    public Stream<Category> getDepartments() {
        return Stream.generate(this::getDepartment).takeWhile(Optional::isPresent).map(Optional::get);
    }

    public Optional<Intent> getIntent() {
        out.println("Please choose from the following options:");
        out.println("[1] Browse by department");
        out.println("[2] Pay up and get out");

        return switch (scanner.next()) {
            case "1" -> Optional.of(Intent.Browse);
            case "2" -> Optional.empty();
            default -> Optional.of(Intent.Unknown);
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
            default -> Optional.of(Category.None);
        };
    }

    public void fill(List<Product> bag, List<Product> products) {
        for (;;) {
            out.println("Please choose an item to add to your bag:");

            for (int i = 0; i < products.size(); i++) {
                out.printf("[%s] %s\n", i + 1, products.get(i).name());
            }

            out.printf("[%s] Back to previous menu\n", products.size() + 1);

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

    public enum Intent { Unknown, Browse }
}
