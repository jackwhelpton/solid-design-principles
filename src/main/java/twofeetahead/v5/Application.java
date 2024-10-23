package twofeetahead.v5;

public class Application {
    private final UI ui;
    private final Greeter greeter;
    private final PriceableCollection<Product> bag;
    private final Browser<Product, Category> catalog;
    private final Cashier cashier;

    public Application(
        UI ui,
        Greeter greeter,
        PriceableCollection<Product> bag,
        Browser<Product, Category> catalog,
        Cashier cashier
    ) {
        this.ui = ui;
        this.greeter = greeter;
        this.bag = bag;
        this.catalog = catalog;
        this.cashier = cashier;
    }

    public void Run() {
        final String name = ui.getName();
        greeter.greet(name);

        ui.stream(ui::getIntent).forEach(
            i -> ui.stream(ui::getDepartment).forEach(
                d -> ui.fill(bag, catalog.browse(d))
            )
        );

        if (!bag.isEmpty()) {
            cashier.checkout(name, bag);
        }
    }
}
