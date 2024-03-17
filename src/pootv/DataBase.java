package pootv;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import notifications.Notification;
import notifications.Observable;

import java.util.ArrayList;

public final class DataBase {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private static final DataBase DATABASE = new DataBase();
    private DataBase() { }

    /** Returns the only database available */
    public static DataBase getDataBase() {
        return DATABASE;
    }

    /** Returns the users in the database */
    public ArrayList<User> getUsers() {
        return users;
    }

    /** Returns the movies in the database */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /** Sets the users in the database */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    /** Sets the movies in the database */
    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /** Method that adds a new movie to the database */
    public void addMovie(final ActionInput action, final ObjectMapper objectMapper,
                         final ArrayNode output) {
        for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
            if (DataBase.getDataBase().getMovies().get(i).getName().equals(action.
                    getAddedMovie().getName())) {
                Output message = new Output();
                message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                return;
            }
        }
        DataBase.getDataBase().getMovies().add(new Movie(action.getAddedMovie()));
        // send a notification to each user subscribed to one of the added movie's genres
        Observable.getObservable().notifyAllObservers(action);
    }

    /** Method that deletes a movie from the database */
    public void deleteMovie(final ActionInput action, final ObjectMapper objectMapper,
                            final ArrayNode output) {
        int ok = 0;
        for (int i = 0; i < DataBase.getDataBase().getMovies().size(); i++) {
            if (DataBase.getDataBase().getMovies().get(i).getName().equals(action.
                    getDeletedMovie())) {
                giveTokensBack(action);
                // send a notification to each user that has previously purchased the movie
                // about to be deleted
                updateDeleteMovie(action);
                DataBase.getDataBase().getMovies().remove(i);
                ok = 1;
                break;
            }
        }
        if (ok == 0) {
            Output message = new Output();
            message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
        }
        for (int i = 0; i < CurrentUserData.getCurrentUserData().
                getCurrentMoviesList().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentMoviesList().get(i).getName().
                    equals(action.getDeletedMovie())) {
                CurrentUserData.getCurrentUserData().getCurrentMoviesList().remove(i);
                break;
            }
        }

    }

    /** Method that gives back the tokens used to buy the movie that has just been deleted */
    public void giveTokensBack(final ActionInput action) {
        for (int i = 0; i < DataBase.getDataBase().getUsers().size(); i++) {
            for (int j = 0; j < DataBase.getDataBase().getUsers().get(i).
                    getPurchasedMovies().size(); j++) {
                if (DataBase.getDataBase().getUsers().get(i).getPurchasedMovies().get(j).getName().
                        equals(action.getDeletedMovie())) {
                    if (DataBase.getDataBase().getUsers().get(i).getCredentials().getAccountType().
                            equals("standard")) {
                        DataBase.getDataBase().getUsers().get(i).setTokensCount(DataBase.
                                getDataBase().getUsers().get(i).getTokensCount() + 2);
                    } else {
                        DataBase.getDataBase().getUsers().get(i).setNumFreePremiumMovies(DataBase.
                                getDataBase().getUsers().get(i).getNumFreePremiumMovies() + 1);
                    }
                }
            }
        }
    }

    /** Method that sends notifications to all users that have previously bought the
     * deleted movie */
    public void updateDeleteMovie(final ActionInput action) {
        for (int i = 0; i < DataBase.getDataBase().getUsers().size(); i++) {
            for (int j = 0; j < DataBase.getDataBase().getUsers().get(i).
                    getPurchasedMovies().size(); j++) {
                if (DataBase.getDataBase().getUsers().get(i).getPurchasedMovies().get(j).getName().
                        equals(action.getDeletedMovie())) {
                    Notification notification = new Notification(action.
                            getDeletedMovie(), "DELETE");
                    DataBase.getDataBase().getUsers().get(i).getNotifications().add(notification);
                }
            }
        }
    }
}
