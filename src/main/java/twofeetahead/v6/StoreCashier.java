package twofeetahead.v6;

import java.io.PrintStream;

public class StoreCashier implements Cashier {
    private final PrintStream out;

    public StoreCashier(PrintStream out) {
        this.out = out;
    }

    public void checkout(String name, Priceable purchase) {
        out.printf("Billing %s %.2f", name, purchase.getPrice());
    }
}
