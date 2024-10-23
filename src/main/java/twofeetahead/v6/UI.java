package twofeetahead.v6;

import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface UI {
    String getName();

    Optional<Intent> getIntent();

    Optional<Category> getDepartment();

    Optional<Product> getProduct(List<Product> products);

    enum Intent { Unknown, Browse }

    default <T> Stream<T> stream(Supplier<Optional<T>> s) {
        return Stream.generate(() -> retry(s)).takeWhile(Optional::isPresent).map(Optional::get);
    }

    default <T> void addTo(Collection<T> l, Supplier<Optional<T>> s) {
        stream(s).forEach(l::add);
    }

    private <T> T retry(Supplier<T> s) {
        try {
            return s.get();
        } catch (InputMismatchException ex) {
            return s.get();
        }
    }
}
