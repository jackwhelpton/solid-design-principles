package twofeetahead.v7;

import java.io.PrintStream;
import java.math.BigDecimal;

public class DiscountCashier implements Cashier {
    private final PrintStream out;

    public DiscountCashier(PrintStream out) {
        this.out = out;
    }

    public void checkout(String name, Priceable purchase) {
        out.printf("Billing %.2f", purchase.getPrice().multiply(new BigDecimal("0.9")));
    }
}
