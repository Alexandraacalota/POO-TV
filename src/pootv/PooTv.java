package pootv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import input.Input;
import notifications.Recommendation;
import pages.Authenticated;
import pages.Login;
import pages.Logout;
import pages.MoviesPage;
import pages.Page;
import pages.Register;
import pages.SeeDetailsPage;
import pages.Unauthenticated;
import pages.Upgrades;

import java.util.ArrayList;
import java.util.Stack;

public final class PooTv {
    private Page page = new Page();

    /** Method that returns the current page */
    public Page getPage() {
        return page;
    }

    /** Method that sets the current page */
    public void setPage(final Page page) {
        this.page = page;
    }

    /** Method that implements the on page and change page actions */
    public void start(final Input inputData, final ObjectMapper objectMapper,
                      final ArrayNode output) {
        final int freeMovies = 15;
        ArrayList<ActionInput> actions = inputData.getActions();
        page = new Unauthenticated();
        // initialize the users and the movies arraylists in the database
        DataBase.getDataBase().setUsers(new ArrayList<>());
        DataBase.getDataBase().setMovies(new ArrayList<>());
        // add all the users from the input file to the database
        for (int i = 0; i < inputData.getUsers().size(); i++) {
            DataBase.getDataBase().getUsers().add(i, new User(inputData.getUsers().get(i).
                    getCredentials(), 0, freeMovies, new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            if (DataBase.getDataBase().getUsers().get(i).getCredentials().getAccountType().
                    equals("premium")) {
                DataBase.getDataBase().getUsers().get(i).setNumFreePremiumMovies(freeMovies);
            }
        }
        // add all the movies from the input file to the database
        for (int i = 0; i < inputData.getMovies().size(); i++) {
            DataBase.getDataBase().getMovies().add(i, new Movie(inputData.getMovies().get(i)));
        }

        // initialize the stack of pages the user navigated through
        Stack<String> pastPages = new Stack<>();
        // initialize the stack of movies from the See Details page
        Stack<String> pastSeeDetails = new Stack<>();

        // do the actions received in the input file
        for (ActionInput action : actions) {
            switch (action.getType()) {
                case "on page" -> onPage(action, objectMapper, output);
                case "change page" -> page = changePage(action, objectMapper, output, pastPages,
                        pastSeeDetails, true);
                case "back" -> {
                    if (!pastPages.empty()) {
                        String pageName = pastPages.pop();
                        action.setPage(pageName);
                        if (pageName.equals("see details")) {
                            action.setMovie(pastSeeDetails.pop());
                        }
                        page = changePage(action, objectMapper, output, pastPages,
                                pastSeeDetails, false);
                    } else {
                        Output message = new Output();
                        message.generalOutput(objectMapper, output, new ArrayList<>(),
                                null, false);
                    }
                }
                case "database" -> {
                    if (action.getFeature().equals("add")) {
                        DataBase.getDataBase().addMovie(action, objectMapper, output);
                    } else {
                        DataBase.getDataBase().deleteMovie(action, objectMapper, output);
                    }
                }
            }
        }
        if (CurrentUserData.getCurrentUserData().getCurrentUser() != null) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().getCredentials().
                    getAccountType().equals("premium")) {
                Recommendation recommendation = new Recommendation();
                recommendation.generate(objectMapper, output);
            }
        }
    }

    /** Method that calls the "on page" actions */
    public void onPage(final ActionInput action, final ObjectMapper objectMapper,
                       final ArrayNode output) {
        int ok = 0;
        Output message = new Output();
        switch (action.getFeature()) {
            case "register":
                if (page.isRegisterPage()) {
                    page = ((Register) page).register(DataBase.getDataBase(), action,
                            CurrentUserData.getCurrentUserData(), objectMapper, output);
                    ok = 1;
                }
                break;
            case "login":
                if (page.isLoginPage()) {
                    page = ((Login) page).login(DataBase.getDataBase(), action.getCredentials().
                                    getName(), action.getCredentials().getPassword(), objectMapper,
                            output, CurrentUserData.getCurrentUserData());
                    ok = 1;
                }
                break;
            case "search":
                if (page.isMoviesPage()) {
                    ((MoviesPage) page).search(CurrentUserData.getCurrentUserData(), action,
                            objectMapper, output);
                    ok = 1;
                }
                break;
            case "filter":
                if (page.isMoviesPage()) {
                    ((MoviesPage) page).filter(CurrentUserData.getCurrentUserData(), action,
                            objectMapper, output);
                    ok = 1;
                }
                break;
            case "buy tokens":
                if (page.isUpgradesPage()) {
                    ((Upgrades) page).buyTokens(CurrentUserData.getCurrentUserData(), action);
                    ok = 1;
                }
                break;
            case "buy premium account":
                if (page.isUpgradesPage()) {
                    ((Upgrades) page).buyPremiumAccount(CurrentUserData.getCurrentUserData());
                    ok = 1;
                }
                break;
            case "purchase":
                if (page.isSeeDetailsPage()) {
                    ((SeeDetailsPage) page).purchase(objectMapper, output);
                    ok = 1;
                }
                break;
            case "watch":
                if (page.isSeeDetailsPage()) {
                    ((SeeDetailsPage) page).watch(objectMapper, output);
                    ok = 1;
                }
                break;
            case "like":
                if (page.isSeeDetailsPage()) {
                    ((SeeDetailsPage) page).likeAction(objectMapper, output);
                    ok = 1;
                }
                break;
            case "rate":
                if (page.isSeeDetailsPage()) {
                    ((SeeDetailsPage) page).rate(CurrentUserData.getCurrentUserData(), action,
                            objectMapper, output);
                    ok = 1;
                }
                break;
            case "subscribe":
                if (page.isSeeDetailsPage()) {
                    ((SeeDetailsPage) page).subscribe(CurrentUserData.getCurrentUserData(), action,
                            objectMapper, output);
                    ok = 1;
                }
                break;
            default :
                message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                break;
        }
        if (ok == 0) {
            message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
        }
    }

    /** Method that calls the "change page" actions */
    public Page changePage(final ActionInput action, final ObjectMapper objectMapper,
                           final ArrayNode output, final Stack<String> pastPages,
                           final Stack<String> pastSeeDetails, final boolean ok) {
        Output message = new Output();
        switch (action.getPage()) {
            case "login" -> {
                if (page.changeToLogin()) {
                    return new Login();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
            case "register" -> {
                if (page.changeToRegister()) {
                    return new Register();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
            case "movies" -> {
                if (page.changeToMovies()) {
                    getMoviesAvailableForUser();
                    User copyUser = new User(CurrentUserData.getCurrentUserData().getCurrentUser());
                    message.generalOutput(objectMapper, output, copyMoviesArrayList(
                            CurrentUserData.getCurrentUserData().getCurrentMoviesList()), copyUser,
                            true);
                    pushPastPageOnStack(pastPages, ok);
                    return new MoviesPage();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
            case "see details" -> {
                if (page.changeToSeeDetails(action, objectMapper, output)) {
                    pushPastPageOnStack(pastPages, ok);
                    pastSeeDetails.push(action.getMovie());
                    return new SeeDetailsPage();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                    getMoviesAvailableForUser();
                    return page;
                }
            }
            case "upgrades" -> {
                if (page.changeToUpgrades()) {
                    pushPastPageOnStack(pastPages, ok);
                    CurrentUserData.getCurrentUserData().setCurrentMoviesList(new ArrayList<>());
                    return new Upgrades();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
            case "authenticated homepage" -> {
                if (page.changeToAuthenticatedHomepage()) {
                    return new Authenticated();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
            case "logout" -> {
                if (page.changeToLogout()) {
                    pastPages.clear();
                    // set all fields of the current user back to null
                    CurrentUserData.getCurrentUserData().setCurrentUser(new User());
                    CurrentUserData.getCurrentUserData().setCurrentMoviesList(new ArrayList<>());
                    return new Logout().logout();
                } else {
                    message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                }
            }
        }
        return page;
    }

    /** Method that checks if a movie has been banned in the current user's country */
    public boolean checkForAvailability(final Movie movie) {
        for (int i = 0; i < movie.getCountriesBanned().size(); i++) {
            if (movie.getCountriesBanned().get(i).equals(CurrentUserData.getCurrentUserData().
                    getCurrentUser().getCredentials().getCountry())) {
                return false;
            }
        }
        return true;
    }

    /** Method that returns a deep copy of a movies ArrayList */
    public ArrayList<Movie> copyMoviesArrayList(final ArrayList<Movie> movies) {
        ArrayList<Movie> copyMovies = new ArrayList<>();
        for (Movie movie : movies) {
            Movie copyMovie = new Movie(movie);
            copyMovies.add(copyMovie);
        }
        return copyMovies;
    }

    /** Method that deletes the unavailable movies for the current user from the
     * movies list */
    public void getMoviesAvailableForUser() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
            PooTv pooTv = new PooTv();
            if (pooTv.checkForAvailability(DataBase.getDataBase().getMovies().get(i))) {
                movies.add(DataBase.getDataBase().getMovies().get(i));
            }
        }
        CurrentUserData.getCurrentUserData().setCurrentMoviesList(movies);
    }

    /** Method that pushes the previous page on stack */
    public void pushPastPageOnStack(final Stack<String> pastPages, final boolean ok) {
        if (ok) {
            if (page.isMoviesPage()) {
                pastPages.push("movies");
            } else if (page.isSeeDetailsPage()) {
                pastPages.push("see details");
            } else if (page.isUpgradesPage()) {
                pastPages.push("upgrades");
            } else if (page.isAuthenticated()) {
                pastPages.push("authenticated homepage");
            }
        }
    }
}
