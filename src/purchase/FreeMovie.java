package purchase;

import pootv.CurrentUserData;

public final class FreeMovie implements Strategy {

    /** Method that decrements the premium user's free movies */
    @Override
    public void purchaseMovie() {
        CurrentUserData.getCurrentUserData().getCurrentUser().
                setNumFreePremiumMovies(CurrentUserData.getCurrentUserData().
                        getCurrentUser().getNumFreePremiumMovies() - 1);
    }
}
