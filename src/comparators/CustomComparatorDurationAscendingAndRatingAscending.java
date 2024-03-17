package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorDurationAscendingAndRatingAscending implements Comparator<Movie> {
    /** Compare method used to sort movies by increasing duration and, in case of equality,
     * by increasing rating */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        if (o1.getDuration() > o2.getDuration()) {
            return 1;
        } else if (o1.getDuration() == o2.getDuration()) {
            return Double.compare(o1.getRating(), o2.getRating());
        } else {
            return -1;
        }
    }
}
