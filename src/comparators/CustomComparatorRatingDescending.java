package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorRatingDescending implements Comparator<Movie> {
    /** Compare method used to sort movies by decreasing rating */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        return Double.compare(o2.getRating(), o1.getRating());
    }
}
