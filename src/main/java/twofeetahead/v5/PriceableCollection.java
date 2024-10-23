package twofeetahead.v5;

import java.math.BigDecimal;
import java.util.Collection;

public interface PriceableCollection<T> extends Collection<T> {
    BigDecimal getPrice();
}
