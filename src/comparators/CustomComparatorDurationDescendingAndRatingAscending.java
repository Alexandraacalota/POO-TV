package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorDurationDescendingAndRatingAscending implements Comparator<Movie> {
    /** Compare method used to sort movies by decreasing duration and, in case of equality,
     * by increasing rating */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        if (o2.getDuration() > o1.getDuration()) {
            return 1;
        } else if (o1.getDuration() == o2.getDuration()) {
            if (o2.getRating() > o1.getRating()) {
                return -1;
            } else if (o1.getRating() == o2.getRating()) {
                return 0;
            } else if (o1.getRating() > o2.getRating()) {
                return 1;
            }
        } else {
            return -1;
        }
        return 1;
    }
}
