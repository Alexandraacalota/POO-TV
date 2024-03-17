package pootv;


import java.util.ArrayList;

public final class CurrentUserData {
    private User currentUser;
    private ArrayList<Movie> currentMoviesList;
    private static final CurrentUserData CURRENTUSERDATA = new CurrentUserData();
    private CurrentUserData() { }

    /** Return the only current user data available for the current page */
    public static CurrentUserData getCurrentUserData() {
        return CURRENTUSERDATA;
    }

    /** Returns the current user */
    public User getCurrentUser() {
        return currentUser;
    }

    /** Returns the current list of movies shown on screen */
    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    /** Sets the current user */
    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    /** Sets the current movies list */
    public void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }
}
