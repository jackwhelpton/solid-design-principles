package twofeetahead.v4;

import java.io.PrintStream;

public class StoreCashier {
    private final PrintStream out;

    public StoreCashier(PrintStream out) {
        this.out = out;
    }

    public void checkout(String name, Bag bag) {
        out.printf("Billing %s %.2f", name, bag.getTotal());
    }
}
