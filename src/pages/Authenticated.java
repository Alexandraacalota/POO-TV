package pages;

public final class Authenticated extends Page {
    public Authenticated() { }

    /** User can refresh Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        return true;
    }

    /** User can switch from Authenticated Homepage to Movies page */
    @Override
    public boolean changeToMovies() {
        return true;
    }

    /** User can switch from Authenticated Homepage to Upgrades page */
    @Override
    public boolean changeToUpgrades() {
        return true;
    }

    /** User can switch from Authenticated Homepage to Logout page */
    @Override
    public boolean changeToLogout() {
        return true;
    }

    /** Returns true if called from the Authenticated Homepage */
    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
