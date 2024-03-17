package input;

import java.util.ArrayList;

public class Input {
    private ArrayList<UserInput> users;
    private ArrayList<MovieInput> movies;
    private ArrayList<ActionInput> actions;

    /** Returns the ArrayList of users received in the input file */
    public ArrayList<UserInput> getUsers() {
        return users;
    }

    /** Returns the ArrayList of movies received in the input file */
    public ArrayList<MovieInput> getMovies() {
        return movies;
    }

    /** Returns the ArrayList of actions received in the input file */
    public ArrayList<ActionInput> getActions() {
        return actions;
    }

    /** Sets the ArrayList of users received in the input file */
    public void setUsers(final ArrayList<UserInput> users) {
        this.users = users;
    }

    /** Sets the ArrayList of movies received in the input file */
    public void setMovies(final ArrayList<MovieInput> movies) {
        this.movies = movies;
    }

    /** Sets the ArrayList of actions received in the input file */
    public void setActions(final ArrayList<ActionInput> actions) {
        this.actions = actions;
    }
}
