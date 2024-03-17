package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import comparators.CustomComparatorDurationAscending;
import comparators.CustomComparatorDurationAscendingAndRatingAscending;
import comparators.CustomComparatorDurationAscendingAndRatingDescending;
import comparators.CustomComparatorDurationDescending;
import comparators.CustomComparatorDurationDescendingAndRatingAscending;
import comparators.CustomComparatorDurationDescendingAndRatingDescending;
import comparators.CustomComparatorRatingAscending;
import comparators.CustomComparatorRatingDescending;

import input.ActionInput;
import pootv.CurrentUserData;
import pootv.PooTv;
import pootv.Movie;
import pootv.User;
import pootv.Output;

import java.util.ArrayList;

public final class MoviesPage extends Page {
    public MoviesPage() { }


    /** Can switch from Movies page to Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        CurrentUserData.getCurrentUserData().setCurrentMoviesList(new ArrayList<>());
        return true;
    }

    /** Can switch from Movies page to See Details page */
    @Override
    public boolean changeToSeeDetails(final ActionInput action, final ObjectMapper objectMapper,
                                      final ArrayNode output) {
        ArrayList<Movie> movie = new ArrayList<>();
        for (int i = 0; i < CurrentUserData.getCurrentUserData().
                getCurrentMoviesList().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentMoviesList().get(i).
                    getName().equals(action.getMovie())) {
                Movie copyMovie = new Movie(CurrentUserData.getCurrentUserData().
                        getCurrentMoviesList().get(i));
                movie.add(0, copyMovie);
                CurrentUserData.getCurrentUserData().setCurrentMoviesList(movie);
                Output message = new Output();
                PooTv pooTv = new PooTv();
                User copyUser = new User(CurrentUserData.getCurrentUserData().getCurrentUser());
                message.generalOutput(objectMapper, output, pooTv.
                        copyMoviesArrayList(CurrentUserData.getCurrentUserData().
                                getCurrentMoviesList()), copyUser, true);
                return true;
            }
        }
        return false;
    }

    /** Can switch from Movies page to the Logout page */
    @Override
    public boolean changeToLogout() {
        return true;
    }

    /** Can refresh Movies page */
    @Override
    public boolean changeToMovies() {
        return true;
    }

    @Override
    public boolean changeToUpgrades() {
        return true;
    }

    /** The current page is the Movies one */
    @Override
    public boolean isMoviesPage() {
        return true;
    }

    /** Search action shows the movies whose titles start with a given string */
    public void search(final CurrentUserData currentUserData, final ActionInput action,
                       final ObjectMapper objectMapper, final ArrayNode output) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < currentUserData.getCurrentMoviesList().size(); i++) {
            // check for each movie in the list of movies displayed on screen if their name
            // matches the startsWith field condition
            if (currentUserData.getCurrentMoviesList().get(i).getName().startsWith(action.
                    getStartsWith())) {
                if (checkCountryAvailability(currentUserData.getCurrentMoviesList().get(i),
                        currentUserData.getCurrentUser().getCredentials().getCountry())) {
                    Movie copyMovie = new Movie(currentUserData.getCurrentMoviesList().get(i));
                    movies.add(copyMovie);
                }
            }
        }
        Output message = new Output();
        PooTv pooTv = new PooTv();
        User copyUser = new User(currentUserData.getCurrentUser());
        message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(movies),
                copyUser, true);
    }

    /** Filter action shows the movies with the given sort/ contains filters */
    public void filter(final CurrentUserData currentUserData, final ActionInput action,
                       final ObjectMapper objectMapper, final ArrayNode output) {
        // The filter action command has a contains field
        if (action.getFilters().getContains() != null) {
            contains(currentUserData, action);
        }
        // The filter action command has a sort field
        if (action.getFilters().getSort() != null) {
            sortMovies(currentUserData, action);
        }
        Output message = new Output();
        PooTv pooTv = new PooTv();
        User copyUser = new User(currentUserData.getCurrentUser());
        message.generalOutput(objectMapper, output, pooTv.copyMoviesArrayList(currentUserData.
                getCurrentMoviesList()), copyUser, true);
    }

    /** Method that filters movies by the actors and genres contained */
    public void contains(final CurrentUserData currentUserData, final ActionInput action) {
        // The contains field has an actors filter
        if (action.getFilters().getContains().getActors() != null) {
            for (int i = 0; i < action.getFilters().getContains().getActors().size(); i++) {
                for (int j = 0; j < currentUserData.getCurrentMoviesList().size(); j++) {
                    int ok = 0;
                    for (int k = 0; k < currentUserData.getCurrentMoviesList().get(j).
                            getActors().size(); k++) {
                        // check if each actor in the action command appears in each movie from
                        // the movies list
                        if (action.getFilters().getContains().getActors().get(i).
                                equals(currentUserData.getCurrentMoviesList().get(j).
                                        getActors().get(k))) {
                            ok = 1;
                            break;
                        }
                    }
                    // remove the movies in which an actor does not appear
                    if (ok == 0) {
                        currentUserData.getCurrentMoviesList().remove(j);
                        // check the same position in the list again after removing one movie
                        j--;
                    }
                }
            }
        }
        // The contains field has a genre filter
        if (action.getFilters().getContains().getGenre() != null) {
            for (int i = 0; i < action.getFilters().getContains().getGenre().size(); i++) {
                for (int j = 0; j < currentUserData.getCurrentMoviesList().size(); j++) {
                    int ok = 0;
                    for (int k = 0; k < currentUserData.getCurrentMoviesList().get(j).
                            getGenres().size(); k++) {
                        // check if each genre in the action command is among each movie from
                        // the movies list's genres
                        if (action.getFilters().getContains().getGenre().get(i).
                                equals(currentUserData.getCurrentMoviesList().get(j).
                                        getGenres().get(k))) {
                            ok = 1;
                            break;
                        }
                    }
                    if (ok == 0) {
                        currentUserData.getCurrentMoviesList().remove(j);
                        // check the same position in the list again after removing one movie
                        j--;
                    }
                }
            }
        }
    }

    /** Method that sorts movies by rating and duration */
    public void sortMovies(final CurrentUserData currentUserData, final ActionInput action) {
        // The sort field has both rating and duration filters
        if (action.getFilters().getSort().getRating() != null && action.getFilters().getSort().
                getDuration() != null) {
            if (action.getFilters().getSort().getRating().equals("increasing") && action.
                    getFilters().getSort().getDuration().equals("increasing")) {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationAscendingAndRatingAscending());
            } else if (action.getFilters().getSort().getRating().equals("decreasing")
                    && action.getFilters().getSort().getDuration().equals("increasing")) {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationAscendingAndRatingDescending());
            } else if (action.getFilters().getSort().getRating().equals("increasing")
                    && action.getFilters().getSort().getDuration().equals("decreasing")) {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationDescendingAndRatingAscending());
            } else {
                // rating decreasing and duration decreasing
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationDescendingAndRatingDescending());
            }
            // the sort field only has a rating filter
        } else if (action.getFilters().getSort().getRating() != null && action.getFilters().
                getSort().getDuration() == null) {
            if (action.getFilters().getSort().getRating().equals("decreasing")) {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorRatingDescending());
            } else {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorRatingAscending());
            }
            // the sort field only has a duration filter
        } else if (action.getFilters().getSort().getRating() == null && action.getFilters().
                getSort().getDuration() != null) {
            if (action.getFilters().getSort().getDuration().equals("decreasing")) {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationDescending());
            } else {
                currentUserData.getCurrentMoviesList().sort(new
                        CustomComparatorDurationAscending());
            }
        }
    }

    /** Method that checks for the availability of the movies in the current user's country */
    public boolean checkCountryAvailability(final Movie movie, final String country) {
        for (int i = 0; i < movie.getCountriesBanned().size(); i++) {
            if (movie.getCountriesBanned().get(i).equals(country)) {
                return false;
            }
        }
        return true;
    }
}
