package pages;

import input.ActionInput;
import pootv.CurrentUserData;

public final class Upgrades extends Page {
    public Upgrades() { }

    /** The user can switch from the Upgrades page to the Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        return true;
    }

    /** The user can switch from the Upgrades page to the Movies one */
    @Override
    public boolean changeToMovies() {
        return true;
    }

    /** The user can refresh the Upgrades page */
    @Override
    public boolean changeToUpgrades() {
        return true;
    }

    /** The user can switch from the Upgrades page to the Logout one */
    @Override
    public boolean changeToLogout() {
        return true;
    }

    /** The current page is the Upgrades one */
    @Override
    public boolean isUpgradesPage() {
        return true;
    }

    /** Method that changes a standard user's account to a premium one */
    public void buyPremiumAccount(final CurrentUserData currentUserData) {
        final int price = 10;
        if (currentUserData.getCurrentUser().getCredentials().getAccountType().
                equals("standard")) {
            if (currentUserData.getCurrentUser().getTokensCount() >= price) {
                currentUserData.getCurrentUser().setTokensCount(currentUserData.getCurrentUser().
                        getTokensCount() - price);
                currentUserData.getCurrentUser().getCredentials().setAccountType("premium");
            }
        }
    }

    /** Method that adds to the tokens count the same amount it extracts from the user's balance */
    public void buyTokens(final CurrentUserData currentUserData, final ActionInput action) {
        int balance = Integer.parseInt(currentUserData.getCurrentUser().getCredentials().
                getBalance());
        int count = Integer.parseInt(action.getCount());
        if (balance >= count) {
            currentUserData.getCurrentUser().getCredentials().setBalance(String.valueOf(
                    balance - count));
            currentUserData.getCurrentUser().setTokensCount(currentUserData.getCurrentUser().
                    getTokensCount() + count);
        }
    }
}
