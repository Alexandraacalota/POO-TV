package input;

import java.util.ArrayList;

public class ContainsInput {
    private ArrayList<String> actors;
    private ArrayList<String> genre;

    /** Returns the actors that must appear in the filtered movies list */
    public ArrayList<String> getActors() {
        return actors;
    }

    /** Returns the genres that the filtered movies must contain */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /** Sets the actors that must appear in the filtered movies list */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /** Sets the genres that the filtered movies must contain */
    public void setGenre(final ArrayList<String> genre) {
        this.genre = genre;
    }
}
