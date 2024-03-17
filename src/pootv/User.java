package pootv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import input.CredentialsInput;
import notifications.Notification;
import notifications.Observer;

import java.util.ArrayList;

public final class User implements Observer {
    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Notification> notifications;
    @JsonIgnore
    private ArrayList<String> subscribedGenres;

    public User() {
    }

    public User(final CredentialsInput credentials, final int tokensCount, final int
            numFreePremiumMovies, final ArrayList<Movie> purchasedMovies, final ArrayList<Movie>
            watchedMovies, final ArrayList<Movie> likedMovies,
                final ArrayList<Movie> ratedMovies, final ArrayList<Notification> notifications,
                final ArrayList<String> subscribedGenres) {
        this.credentials = new CredentialsInput(credentials);
        this.tokensCount = tokensCount;
        this.numFreePremiumMovies = numFreePremiumMovies;
        if (purchasedMovies != null) {
            this.purchasedMovies = new ArrayList<>(purchasedMovies);
        }
        if (watchedMovies != null) {
            this.watchedMovies = watchedMovies;
        }
        if (likedMovies != null) {
            this.likedMovies = likedMovies;
        }
        if (ratedMovies != null) {
            this.ratedMovies = ratedMovies;
        }
        if (notifications != null) {
            this.notifications = notifications;
        }
        if (subscribedGenres != null) {
            this.subscribedGenres = subscribedGenres;
        }
    }

    /** Copy constructor for user */
    public User(final User user) {
        this.credentials = new CredentialsInput(user.credentials);
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        ArrayList<Movie> purchased = new ArrayList<>();
        for (int i = 0; i < user.getPurchasedMovies().size(); i++) {
            Movie purchasedMovie = new Movie(user.getPurchasedMovies().get(i));
            purchased.add(purchasedMovie);
        }
        this.purchasedMovies = purchased;
        ArrayList<Movie> watched = new ArrayList<>();
        for (int i = 0; i < user.getWatchedMovies().size(); i++) {
            Movie watchedMovie = new Movie(user.getWatchedMovies().get(i));
            watched.add(watchedMovie);
        }
        this.watchedMovies = watched;
        ArrayList<Movie> liked = new ArrayList<>();
        for (int i = 0; i < user.getLikedMovies().size(); i++) {
            Movie likedMovie = new Movie(user.getLikedMovies().get(i));
            liked.add(likedMovie);
        }
        this.likedMovies = liked;
        ArrayList<Movie> rated = new ArrayList<>();
        for (int i = 0; i < user.getRatedMovies().size(); i++) {
            Movie ratedMovie = new Movie(user.getRatedMovies().get(i));
            rated.add(ratedMovie);
        }
        this.ratedMovies = rated;
        ArrayList<Notification> notificationsCopy = new ArrayList<>();
        for (int i = 0; i < user.getNotifications().size(); i++) {
            Notification notification  = new Notification(user.getNotifications().get(i));
            notificationsCopy.add(notification);
        }
        this.notifications = notificationsCopy;
        ArrayList<String> subscribedCopy = new ArrayList<>();
        for (int i = 0; i < user.getSubscribedGenres().size(); i++) {
            subscribedCopy.add(user.getSubscribedGenres().get(i));
        }
        this.subscribedGenres = subscribedCopy;
    }

    /** Returns user's credentials */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /** Returns user's tokens count */
    public int getTokensCount() {
        return tokensCount;
    }

    /** Returns user's number of free premium movies */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /** Returns user's purchased movies */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /** Returns user's watched movies */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /** Returns the movies that the user liked */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    /** Returns the movies that the user rated */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    /** Sets user's credentials */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    /** Sets user's tokens count */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /** Sets user's number of free premium movies */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /** Sets user's purchased movies */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /** Sets user's watched movies */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /** Sets user's liked movies */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /** Sets user's rated movies */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public void setSubscribedGenres(final ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    @Override
    public void update(final Observer observer, final String movieName) {
        Notification notification = new Notification(movieName, "ADD");
        for (int i = 0; i < DataBase.getDataBase().getUsers().size(); i++) {
            if (DataBase.getDataBase().getUsers().get(i).getCredentials().getName().
                    equals(((User) observer).getCredentials().getName())) {
                DataBase.getDataBase().getUsers().get(i).getNotifications().
                        add(notification);
            }
        }
    }
}
