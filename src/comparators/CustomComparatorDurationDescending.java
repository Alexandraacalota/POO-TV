package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorDurationDescending implements Comparator<Movie> {
    /** Compare method used to sort movies by decreasing duration */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        return Integer.compare(o2.getDuration(), o1.getDuration());
    }
}
