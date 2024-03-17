package input;

public class SortInput {
    private String rating;
    private String duration;

    /** Returns the property of the sort action by rating (increasing/ decreasing) */
    public String getRating() {
        return rating;
    }

    /** Returns the property of the sort action by duration (increasing/ decreasing) */
    public String getDuration() {
        return duration;
    }

    /** Sets the property of the sort action by rating */
    public void setRating(final String rating) {
        this.rating = rating;
    }

    /** Sets the property of the sort action by duration */
    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
