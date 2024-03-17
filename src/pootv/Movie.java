package pootv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.MovieInput;

import java.util.ArrayList;

public final class Movie {
    private String name;
    private String year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes;
    private double rating;
    private int numRatings;
    @JsonIgnore
    private double sumRatings;
    @JsonIgnore
    private int realNumRatings;

    public Movie() { }

    public Movie(final MovieInput movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = new ArrayList<>(movie.getGenres());
        this.actors = new ArrayList<>(movie.getActors());
        this.countriesBanned = new ArrayList<>(movie.getCountriesBanned());
        this.numLikes = 0;
        this.rating = 0.00;
        this.numRatings = 0;
        this.sumRatings = 0;
        this.realNumRatings = 0;
    }

    public Movie(final Movie movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = new ArrayList<>(movie.getGenres());
        this.actors = new ArrayList<>(movie.getActors());
        this.countriesBanned = new ArrayList<>(movie.getCountriesBanned());
        this.numLikes = movie.getNumLikes();
        this.rating = movie.getRating();
        this.numRatings = movie.getNumRatings();
        this.sumRatings = movie.getSumRatings();
        this.realNumRatings = movie.getRealNumRatings();
    }

    /** Returns the name of the movie */
    public String getName() {
        return name;
    }

    /** Returns the year of the movie's first release */
    public String getYear() {
        return year;
    }

    /** Returns the duration of the movie */
    public int getDuration() {
        return duration;
    }

    /** Returns the genres of the movie */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /** Returns the actors that play in the movie */
    public ArrayList<String> getActors() {
        return actors;
    }

    /** Returns the countries in which the movie is banned */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /** Returns the number of likes the movie has */
    public int getNumLikes() {
        return numLikes;
    }

    /** Returns the movie's rating */
    public double getRating() {
        return rating;
    }

    /** Returns the movie's number of ratings */
    public int getNumRatings() {
        return numRatings;
    }

    /** Returns the sum of ratings the movie has */
    public double getSumRatings() {
        return sumRatings;
    }

    /** Returns the number of ratings including the ones of the users that have
     * rated the same movie multiple times */
    public int getRealNumRatings() {
        return realNumRatings;
    }

    /** Sets the name of the movie */
    public void setName(final String name) {
        this.name = name;
    }

    /** Sets the year of the movie's first release */
    public void setYear(final String year) {
        this.year = year;
    }

    /** Sets the duration of the movie */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /** Sets the genres of the movie */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /** Sets the actors of the movie */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    /** Sets the countries in which the movie is banned */
    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    /** Sets the movie's number of ratings */
    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    /** Sets the rating the movie has */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /** Sets the number of ratings the movie has */
    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }

    /** Sets the sum of ratings the movie has */
    public void setSumRatings(final double sumRatings) {
        this.sumRatings = sumRatings;
    }

    /** Sets the number of ratings including the ones of the users that have
     * rated the same movie multiple times */
    public void setRealNumRatings(final int realNumRatings) {
        this.realNumRatings = realNumRatings;
    }
}
