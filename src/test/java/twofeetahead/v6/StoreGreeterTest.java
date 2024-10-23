package twofeetahead.v6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class StoreGreeterTest {
    @Test
    public void testGreet() {
        final var buffer = new ByteArrayOutputStream();
        try (PrintStream out = new PrintStream(buffer)) {
            final var greeter = new StoreGreeter(out);

            greeter.greet("Fi Barr");

            assertEquals("Hello, Fi Barr. ", buffer.toString());
        }
    }
}
