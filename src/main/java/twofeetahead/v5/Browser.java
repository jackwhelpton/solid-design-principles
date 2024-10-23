package twofeetahead.v5;

import java.util.List;

public interface Browser<T, U> {
    List<T> browse(U u);
}
