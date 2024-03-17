package notifications;

public final class Notification {
    private String movieName;
    private String message;
    public Notification(final Notification notification) {
        this.movieName = notification.getMovieName();
        this.message = notification.getMessage();
    }

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
