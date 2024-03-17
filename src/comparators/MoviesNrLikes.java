package comparators;

import pootv.Movie;

import java.util.Comparator;

public class MoviesNrLikes implements Comparator<Movie> {
    /** Compare method used to sort movies by decreasing duration and, in case of equality,
     * by increasing rating */
    @Override
    public int compare(final Movie o1, final Movie o2) {
        if (o2.getNumLikes() > o1.getNumLikes()) {
            return 1;
        } else if (o1.getNumLikes() == o2.getNumLikes()) {
            if (o2.getName().compareTo(o1.getName()) > 0) {
                return -1;
            } else if (o1.getName().compareTo(o2.getName()) == 0) {
                return 0;
            } else if (o1.getName().compareTo(o2.getName()) > 0) {
                return 1;
            }
        } else {
            return -1;
        }
        return 1;
    }
}
