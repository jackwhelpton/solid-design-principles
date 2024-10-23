package twofeetahead.v5;

import java.io.PrintStream;

public class StoreGreeter implements Greeter {
    private final PrintStream out;

    public StoreGreeter(PrintStream out) {
        this.out = out;
    }

    public void greet(String name) {
        out.printf("Hello, %s. ", name);
    }
}
