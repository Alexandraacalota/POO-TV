package comparators;

import pootv.Movie;
import java.util.Comparator;

public class CustomComparatorDurationAscending implements Comparator<Movie> {
    /** Compare method used to sort movies by increasing duration */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        return Integer.compare(o1.getDuration(), o2.getDuration());
    }
}
