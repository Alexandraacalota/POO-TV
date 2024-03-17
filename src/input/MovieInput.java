package input;

import java.util.ArrayList;

public class MovieInput {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;

    public MovieInput() { }

    /** Returns the name of the movie */
    public String getName() {
        return name;
    }

    /** Returns the year in which the movie was first released */
    public String getYear() {
        return year;
    }

    /** Returns the duration of the movie */
    public int getDuration() {
        return duration;
    }

    /** Returns the ArrayList of genres the movie has */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /** Returns the ArrayList of actors that play in the movie */
    public ArrayList<String> getActors() {
        return actors;
    }

    /** Returns the ArrayList of the countries in which the movie is unavailable */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /** Sets the name of the movie */
    public void setName(final String name) {
        this.name = name;
    }

    /** Sets the year in which the movie was first released */
    public void setYear(final String year) {
        this.year = year;
    }

    /** Sets the duration of the movie */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /** Sets the ArrayList of genres the movie has */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /** Sets the ArrayList of actors that play in the movie */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /** Sets the ArrayList of the countries in which the movie is banned */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }
}
