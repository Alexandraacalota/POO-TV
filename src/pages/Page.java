package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;

public class Page {

    /** Method that returns false if the current page is unable to switch to the Login one */
    public boolean changeToLogin() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Register one */
    public boolean changeToRegister() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Authenticated
     * Homepage one */
    public boolean changeToAuthenticatedHomepage() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Unauthenticated
     * Homepage one */
    public boolean changeToUnauthenticatedHomepage() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Movies one */
    public boolean changeToMovies() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the See Details one */
    public boolean changeToSeeDetails(final ActionInput action, final ObjectMapper objectMapper,
                                      final ArrayNode output) {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Login one */
    public boolean changeToUpgrades() {
        return false;
    }

    /** Method that returns false if the current page is unable to switch to the Logout one */
    public boolean changeToLogout() {
        return false;
    }

    /** Method that returns false if the current page is not the Authenticated Homepage */
    public boolean isAuthenticated() {
        return false;
    }

    /** Method that returns false if the current page is not the Login one */
    public boolean isLoginPage() {
        return false;
    }

    /** Method that returns false if the current page is not the Logout one */
    public boolean isLogoutPage() {
        return false;
    }

    /** Method that returns false if the current page is not the Movies one */
    public boolean isMoviesPage() {
        return false;
    }

    /** Method that returns false if the current page is not the Register one */
    public boolean isRegisterPage() {
        return false;
    }

    /** Method that returns false if the current page is not the See Details one */
    public boolean isSeeDetailsPage() {
        return false;
    }

    /** Method that returns false if the current page is not the Unauthenticated Homepage */
    public boolean isUnauthenticatedPage() {
        return false;
    }

    /** Method that returns false if the current page is not the Upgrades one */
    public boolean isUpgradesPage() {
        return false;
    }
}
