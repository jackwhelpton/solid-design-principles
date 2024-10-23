package twofeetahead.v5;

public interface Cashier {
    void checkout(String name, PriceableCollection<Product> c);
}
