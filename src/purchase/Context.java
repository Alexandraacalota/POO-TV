package purchase;

public class Context {
    private final Strategy strategy;

    public Context(final Strategy strategy) {
        this.strategy = strategy;
    }

    /** Method that executes the payment method of the movie */
    public void executeStrategy() {
        strategy.purchaseMovie();
    }
}
