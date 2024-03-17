package pages;

public final class Unauthenticated extends Page {
    public Unauthenticated() { }

    /** The user can switch from the Unauthenticated Homepage to the Login one */
    @Override
    public boolean changeToLogin() {
        return true;
    }

    /** The user can switch from the Unauthenticated Homepage to the Register one */
    @Override
    public boolean changeToRegister() {
        return true;
    }

    /** The user can refresh the Unauthenticated Homepage */
    @Override
    public boolean changeToUnauthenticatedHomepage() {
        return true;
    }

    /** The current page is the Unauthenticated Homepage */
    @Override
    public boolean isUnauthenticatedPage() {
        return true;
    }
}
