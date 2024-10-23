package twofeetahead.v4;

public class Application {
    private final CLI cli;
    private final StoreGreeter greeter;
    private final Bag bag;
    private final Catalog catalog;
    private final StoreCashier storeCashier;

    public Application(
        CLI cli,
        StoreGreeter greeter,
        Bag bag,
        Catalog catalog,
        StoreCashier storeCashier
    ) {
        this.cli = cli;
        this.greeter = greeter;
        this.bag = bag;
        this.catalog = catalog;
        this.storeCashier = storeCashier;
    }

    public void Run() {
        final String name = cli.getName();
        greeter.greet(name);

        cli.getIntents().forEach(
            i -> cli.getDepartments().forEach(d -> cli.fill(bag, catalog.find(d))));

        if (!bag.isEmpty()) {
            storeCashier.checkout(name, bag);
        }
    }
}
