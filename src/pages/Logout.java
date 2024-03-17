package pages;

import pootv.CurrentUserData;

public final class Logout extends Page {
    public Logout() { }

    /** Can switch from Logout page to Unauthenticated Homepage */
    @Override
    public boolean changeToUnauthenticatedHomepage() {
        return true;
    }

    /** Can refresh Logout page */
    @Override
    public boolean changeToLogout() {
        return true;
    }

    /** The current page is the Logout one */
    @Override
    public boolean isLogoutPage() {
        return true;
    }

    /** Logout action returns to Unauthenticated Homepage */
    public Page logout() {
        CurrentUserData.getCurrentUserData().setCurrentUser(null);
        return new Unauthenticated();
    }
}
