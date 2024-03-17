package purchase;

public interface Strategy {

    /** Method about to be overridden in the FreeMovie and MoviePurchase Classes
     * for the payment method of the movie */
    void purchaseMovie();
}
