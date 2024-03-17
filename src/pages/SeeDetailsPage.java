package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import pootv.CurrentUserData;
import pootv.DataBase;
import pootv.PooTv;
import pootv.User;
import pootv.Output;
import notifications.Observable;
import purchase.Context;
import purchase.FreeMovie;
import purchase.MoviePurchase;

import java.util.ArrayList;

public final class SeeDetailsPage extends Page {
    public SeeDetailsPage() { }

    /** The user can switch from the current page to Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        return true;
    }

    /** The user can switch from See Details page to Movies */
    @Override
    public boolean changeToMovies() {
        return true;
    }

    /** The user can refresh See Details page */
    @Override
    public boolean changeToSeeDetails(final ActionInput action, final ObjectMapper objectMapper,
                                      final ArrayNode output) {
        return true;
    }

    /** The user can switch from See Details page to Upgrades one */
    @Override
    public boolean changeToUpgrades() {
        return true;
    }

    /** The user can switch to the Logout page from the current page */
    @Override
    public boolean changeToLogout() {
        return true;
    }

    /** The current page is the See Details one */
    @Override
    public boolean isSeeDetailsPage() {
        return true;
    }

    /** Method that adds the movie shown on the current See Details page to the user's purchased
     * movies ArrayList */
    public void purchase(final ObjectMapper objectMapper, final ArrayNode output) {
        final int moviePrice = 2;
        Output message = new Output();
        // if the movie hasn't already been purchased by the current user
        if (!checkIfPurchased()) {
            // if the user is a premium one && he still has free movies
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getNumFreePremiumMovies() > 0
                    && CurrentUserData.getCurrentUserData().getCurrentUser().getCredentials().
                    getAccountType().equals("premium")) {
                for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
                    if (DataBase.getDataBase().getMovies().get(i).getName().
                            equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                                    get(0).getName())) {
                        // add the movie to the user's purchased movies list
                        CurrentUserData.getCurrentUserData().getCurrentUser().getPurchasedMovies().
                                add(DataBase.getDataBase().getMovies().get(i));
                        // use the movie purchase strategy that decrements his free movies
                        Context context = new Context(new FreeMovie());
                        context.executeStrategy();
                    }
                }
                PooTv pooTv = new PooTv();
                User copyUser = new User(CurrentUserData.getCurrentUserData().
                        getCurrentUser());
                message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(
                                CurrentUserData.getCurrentUserData().getCurrentMoviesList()),
                        copyUser, true);
            } else {
                // if the user has enough tokens to buy the movie
                if (CurrentUserData.getCurrentUserData().getCurrentUser().getTokensCount()
                        >= moviePrice) {
                    for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
                        if (DataBase.getDataBase().getMovies().get(i).getName().
                                equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                                        get(0).getName())) {
                            CurrentUserData.getCurrentUserData().getCurrentUser().
                                    getPurchasedMovies().add(DataBase.getDataBase().
                                            getMovies().get(i));
                            break;
                        }
                    }
                    // use the movie purchase strategy with tokens
                    Context context = new Context(new MoviePurchase());
                    context.executeStrategy();
                    PooTv pooTv = new PooTv();
                    User copyUser = new User(CurrentUserData.getCurrentUserData().
                            getCurrentUser());
                    message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(
                                    CurrentUserData.getCurrentUserData().getCurrentMoviesList()),
                            copyUser, true);
                }
            }
        } else {
            message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
        }
    }

    /** Method that checks if the movie has been purchased */
    public boolean checkIfPurchased() {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getPurchasedMovies().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getPurchasedMovies().get(i).
                    getName().equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                            get(0).getName())) {
                return true;
            }
        }
        return false;
    }

    /** Method that adds the movie shown on the See Details page to the user's
     * watched movies ArrayList */
    public void watch(final ObjectMapper objectMapper,
                      final ArrayNode output) {
        Output message = new Output();
        if (checkIfPurchased()) {
            successfullyPurchasedWatch(objectMapper, output);
            return;
        }
        message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
    }

    /** Method that prints the data shown on screen after a movie has been watched */
    public void successfullyPurchasedWatch(final ObjectMapper objectMapper,
                                            final ArrayNode output) {
        Output message = new Output();
//        if (!checkIfWatched()) {
            for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
                if (DataBase.getDataBase().getMovies().get(i).getName().
                        equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                                get(0).getName()) && !checkIfWatched()) {
                    CurrentUserData.getCurrentUserData().getCurrentUser().getWatchedMovies().add(
                            DataBase.getDataBase().getMovies().get(i));
                }
            }
            PooTv pooTv = new PooTv();
            User copyUser = new User(CurrentUserData.getCurrentUserData().getCurrentUser());
            message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(CurrentUserData.
                    getCurrentUserData().getCurrentMoviesList()), copyUser, true);
    }

    /** Method that checks if a movie has been watched by the current user */
    public boolean checkIfWatched() {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getWatchedMovies().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getWatchedMovies().get(i).
                    getName().equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                            get(0).getName())) {
                return true;
            }
        }
        return false;
    }

    /** Method that implements the like action of a movie that has been previously watched */
    public void likeAction(final ObjectMapper objectMapper, final ArrayNode output) {
        Output message = new Output();
        if (checkIfWatched()) {
            successfullyWatchedLike(objectMapper, output);
            return;
        }
        message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
    }

    /** Method that adds the movie shown on the See Details page to the user's liked movies and
     * increases movie's number of likes in the database */
    public void successfullyWatchedLike(final ObjectMapper objectMapper, final ArrayNode output) {
        Output message = new Output();
            if (!checkIfAlreadyLiked()) {
                for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
                    if (DataBase.getDataBase().getMovies().get(i).getName().
                            equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                                    get(0).getName())) {
                        DataBase.getDataBase().getMovies().get(i).setNumLikes(DataBase.
                                getDataBase().getMovies().get(i).getNumLikes() + 1);
                        CurrentUserData.getCurrentUserData().getCurrentMoviesList().get(0).
                                setNumLikes(DataBase.getDataBase().getMovies().
                                        get(i).getNumLikes());
                        CurrentUserData.getCurrentUserData().getCurrentUser().getLikedMovies().
                                add(DataBase.getDataBase().getMovies().get(i));
                    }
                }
                PooTv pooTv = new PooTv();
                User copyUser = new User(CurrentUserData.getCurrentUserData().
                        getCurrentUser());
                message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(
                                CurrentUserData.getCurrentUserData().getCurrentMoviesList()),
                        copyUser, true);
            }
    }

    /** Method that checks if a movie has already been liked by the current user */
    public boolean checkIfAlreadyLiked() {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getLikedMovies().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getLikedMovies().get(i).
                    getName().equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                            get(0).getName())) {
                return true;
            }
        }
        return false;
    }

    /** Method that implements the rate action of a movie that has been previously watched */
    public void rate(final CurrentUserData currentUserData, final ActionInput action, final
    ObjectMapper objectMapper, final ArrayNode output) {
        final int maxRate = 5;
        Output message = new Output();
        if (checkIfWatched() && (action.getRate() >= 0) && (action.getRate() <= maxRate)) {
            // if the movie hasn't already been rated by the current user
            currentUserData.getCurrentMoviesList().get(0).setSumRatings(currentUserData.
                    getCurrentMoviesList().get(0).getSumRatings() + action.getRate());
            currentUserData.getCurrentMoviesList().get(0).setRealNumRatings(currentUserData.
                    getCurrentMoviesList().get(0).getRealNumRatings() + 1);
            currentUserData.getCurrentMoviesList().get(0).setRating(currentUserData.
                    getCurrentMoviesList().get(0).getSumRatings() / currentUserData.
                    getCurrentMoviesList().get(0).getRealNumRatings());
            if ((!(alreadyRated()))) {
                currentUserData.getCurrentMoviesList().get(0).setNumRatings(currentUserData.
                        getCurrentMoviesList().get(0).getNumRatings() + 1);
            }
            for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
                if (DataBase.getDataBase().getMovies().get(i).getName().equals(currentUserData.
                        getCurrentMoviesList().get(0).getName())) {
                    DataBase.getDataBase().getMovies().get(i).setSumRatings(currentUserData.
                            getCurrentMoviesList().get(0).getSumRatings());
                    DataBase.getDataBase().getMovies().get(i).setRealNumRatings(currentUserData.
                            getCurrentMoviesList().get(0).getRealNumRatings());
                    DataBase.getDataBase().getMovies().get(i).setRating(currentUserData.
                            getCurrentMoviesList().get(0).getRating());
                    DataBase.getDataBase().getMovies().get(i).setNumRatings(currentUserData.
                            getCurrentMoviesList().get(0).getNumRatings());
                    if (!alreadyRated()) {
                        currentUserData.getCurrentUser().getRatedMovies().add(DataBase.
                                getDataBase().getMovies().get(i));
                    }
                }
            }
            PooTv pooTv = new PooTv();
            User copyUser = new User(CurrentUserData.getCurrentUserData().
                    getCurrentUser());
            message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(
                            CurrentUserData.getCurrentUserData().getCurrentMoviesList()),
                    copyUser, true);
        } else {
            message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
        }
    }

    /** Method that checks if the movie displayed on the See Details page has already been rated by
     * the current user */
    public boolean alreadyRated() {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getRatedMovies().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getRatedMovies().get(i).
                    getName().equals(CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                            get(0).getName())) {
                return true;
            }
        }
        return false;
    }

    /** Method that adds a genre to the current user's subscriptions */
    public void subscribe(final CurrentUserData currentUserData, final ActionInput action, final
    ObjectMapper objectMapper, final ArrayNode output) {
        int ok = 0;
        Output message = new Output();
        for (int i = 0; i < currentUserData.getCurrentMoviesList().get(0).getGenres().size(); i++) {
            if (currentUserData.getCurrentMoviesList().get(0).getGenres().get(i).equals(action.
                    getSubscribedGenre())) {
                ok = 1;
                break;
            }
        }
        if (ok == 1) {
            if (!alreadySubscribed(action)) {
                CurrentUserData.getCurrentUserData().getCurrentUser().getSubscribedGenres().
                        add(action.getSubscribedGenre());
                Observable.getObservable().attach(CurrentUserData.getCurrentUserData().
                        getCurrentUser(), action.getSubscribedGenre());
                return;
            }
        }
        message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
    }

    /** Method that checks if a user is already subscribed to a genre */
    public boolean alreadySubscribed(final ActionInput action) {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getSubscribedGenres().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getSubscribedGenres().
                    get(i).equals(action.getSubscribedGenre())) {
                return true;
            }
        }
        return false;
    }
}
