package purchase;

import pootv.CurrentUserData;

public final class MoviePurchase implements Strategy {

    /** Method that decrements user's tokens by two */
    @Override
    public void purchaseMovie() {
        final int moviePrice = 2;
        CurrentUserData.getCurrentUserData().getCurrentUser().setTokensCount(
                CurrentUserData.getCurrentUserData().getCurrentUser().
                        getTokensCount() - moviePrice);
    }
}
