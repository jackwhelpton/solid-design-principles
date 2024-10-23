package twofeetahead.v5;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface UI {
    String getName();

    Optional<Intent> getIntent();

    Optional<Category> getDepartment();

    void fill(Collection<Product> bag, List<Product> products);

    enum Intent { Unknown, Browse }

    default <T> Stream<T> stream(Supplier<Optional<T>> s) {
        return Stream.generate(s).takeWhile(Optional::isPresent).map(Optional::get);
    }
}
