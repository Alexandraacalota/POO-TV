package notifications;

public interface Observer {
    /** Method that updates the observers when a new movie is added to the database */
    void update(Observer observer, String movieName);
}
