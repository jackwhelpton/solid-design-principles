package twofeetahead.v1;

import java.math.BigDecimal;

public record Product(String name, Category category, BigDecimal price) {}
