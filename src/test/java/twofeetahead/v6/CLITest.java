package twofeetahead.v6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class CLITest {
    @Test
    public void testName() {
        final var buffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(buffer)) {
            final var in = new ByteArrayInputStream("Fi Barr\n".getBytes());
            final var cli = new CLI(in, out);

            final String name = cli.getName();

            assertEquals("Welcome to the store, please enter your name: ", buffer.toString());
            assertEquals("Fi Barr", name);
        }
    }

    @Test
    public void testIntent() {
        try (PrintStream out = new PrintStream(new ByteArrayOutputStream())) {
            final var in = new ByteArrayInputStream("1\n".getBytes());
            final var cli = new CLI(in, out);

            final Optional<CLI.Intent> intent = cli.getIntent();

            assertTrue(intent.isPresent());
            assertEquals(CLI.Intent.Browse, intent.get());
        }
    }

    @Test
    public void testProduct() {
        try (PrintStream out = new PrintStream(new ByteArrayOutputStream())) {
            final var in = new ByteArrayInputStream("1\n".getBytes());
            final var cli = new CLI(in, out);
            final List<Product> products = List.of(
                new Product("a", Category.Headgear, new BigDecimal("12.99")),
                new Product("b", Category.Footwear, new BigDecimal("112.99")),
                new Product("c", Category.Headgear, new BigDecimal("47.99"))
            );

            final Optional<Product> product = cli.getProduct(products);

            assertTrue(product.isPresent());
            assertEquals(products.get(0), product.get());
        }
    }

    @Test
    public void testAddTo() {
        try (PrintStream out = new PrintStream(new ByteArrayOutputStream())) {
            final var in = new ByteArrayInputStream("1\n2\n4\n".getBytes());
            final var cli = new CLI(in, out);
            final var bag = new ArrayList<Product>();

            final Iterator<Product> it = List.of(
                new Product("a", Category.Headgear, new BigDecimal("12.99")),
                new Product("b", Category.Footwear, new BigDecimal("112.99")),
                new Product("c", Category.Headgear, new BigDecimal("47.99"))
            ).iterator();

            cli.addTo(bag, () -> Optional.ofNullable(it.hasNext() ? it.next() : null));

            assertEquals(List.of(
                new Product("a", Category.Headgear, new BigDecimal("12.99")),
                new Product("b", Category.Footwear, new BigDecimal("112.99")),
                new Product("c", Category.Headgear, new BigDecimal("47.99"))
            ), bag);
        }
    }
}
