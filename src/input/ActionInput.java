package input;

public final class ActionInput {
    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private String count;
    private FiltersInput filters;
    private String movie;
    private String startsWith;
    private int rate;
    private String subscribedGenre;
    private MovieInput addedMovie;
    private String deletedMovie;

    /** Returns the type of action requested */
    public String getType() {
        return type;
    }

    /** Returns the type of page on which the action takes place */
    public String getPage() {
        return page;
    }

    /** Returns the "on page" action's name */
    public String getFeature() {
        return feature;
    }

    /** Returns the credentials of a new about to be registered user */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /** Returns the amount of tokens requested to be bought */
    public String getCount() {
        return count;
    }

    /** Returns the filters chosen in the current action */
    public FiltersInput getFilters() {
        return filters;
    }

    /** Returns the title of the movie whose details are requested */
    public String getMovie() {
        return movie;
    }

    /** Returns the string which must represent the beginning of the movies
     * shown in the search command */
    public String getStartsWith() {
        return startsWith;
    }

    /** Returns the rate a user gives to a movie */
    public int getRate() {
        return rate;
    }

    /** Returns the genre the user wants to subscribe to */
    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    /** Returns the movie about to be added in the database */
    public MovieInput getAddedMovie() {
        return addedMovie;
    }

    /** Returns the movie about to be deleted in the database */
    public String getDeletedMovie() {
        return deletedMovie;
    }

    /** Sets the type of action requested */
    public void setType(final String type) {
        this.type = type;
    }

    /** Sets the type of page on which the action takes place */
    public void setPage(final String page) {
        this.page = page;
    }

    /** Sets the "on page" action's name */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /** Sets the credentials of a new about to be registered user */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    /** Sets the amount of tokens requested to be bought */
    public void setCount(final String count) {
        this.count = count;
    }

    /** Sets the filters chosen in the current action */
    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    /** Sets the title of the movie whose details are requested */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /** Sets the string which must represent the beginning of the movies
     * shown in the search command */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /** Sets the rate a user gives to a movie */
    public void setRate(final int rate) {
        this.rate = rate;
    }

    /** Sets the genre the user wants to subscribe to */
    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }

    /** Sets the movie about to be added in the database */
    public void setAddedMovie(final MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }

    /** Sets the movie about to be deleted in the database */
    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }
}
