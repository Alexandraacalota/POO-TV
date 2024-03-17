package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorRatingAscending implements Comparator<Movie> {
    /** Compare method used to sort movies by increasing rating */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        return Double.compare(o1.getRating(), o2.getRating());
    }
}
