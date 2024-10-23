package twofeetahead.v4;

import java.io.PrintStream;

public class StoreGreeter {
    private final PrintStream out;

    public StoreGreeter(PrintStream out) {
        this.out = out;
    }

    public void greet(String name) {
        out.printf("Hello, %s. ", name);
    }
}
