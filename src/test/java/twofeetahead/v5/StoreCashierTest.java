package twofeetahead.v5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class StoreCashierTest {
    @Test
    public void testCheckout() {
        final var buffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(buffer)) {
            final var cashier = new StoreCashier(out);

            cashier.checkout("Fi Barr", new FixedPriceBag());
        }

        assertEquals("Billing Fi Barr 99.99", buffer.toString());
    }

    private static class FixedPriceBag extends ArrayList<Product> implements PriceableCollection<Product> {
        @Override
        public BigDecimal getPrice() {
            return new BigDecimal("99.99");
        }
    }
}
