package notifications;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comparators.MoviesNrLikes;
import pootv.CurrentUserData;
import pootv.PooTv;
import pootv.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public final class Recommendation {

    /** Method that generates the recommendation for the premium user at the end of the actions */
    public void generate(final ObjectMapper objectMapper, final ArrayNode output) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getLikedMovies().size(); i++) {
            for (int j = 0; j < CurrentUserData.getCurrentUserData().getCurrentUser().
                    getLikedMovies().get(i).getGenres().size(); j++) {
                // if the genre already exists in the hashtable, increment its value
                if (hm.containsKey(CurrentUserData.getCurrentUserData().getCurrentUser().
                        getLikedMovies().get(i).getGenres().get(j))) {
                    int newValue = hm.get(CurrentUserData.getCurrentUserData().getCurrentUser().
                            getLikedMovies().get(i).getGenres().get(j)) + 1;
                    hm.replace(CurrentUserData.getCurrentUserData().getCurrentUser().
                            getLikedMovies().get(i).getGenres().get(j), newValue);
                // if the genre doesn't exist in the hashtable, add it on the next
                // available position
                } else {
                    hm.put(CurrentUserData.getCurrentUserData().getCurrentUser().getLikedMovies().
                            get(i).getGenres().get(j), 1);
                }
            }
        }
        // sort the hashmap by values and, in case of equality, lexicographically by key
        HashMap<String, Integer> genres = sortByNrLikes(hm);
        PooTv pooTv = new PooTv();
        pooTv.getMoviesAvailableForUser();
        // sort the list of movies available to the user by number of likes
        CurrentUserData.getCurrentUserData().getCurrentMoviesList().sort(new MoviesNrLikes());
        for (Map.Entry<String, Integer> set : genres.entrySet()) {
            for (int j = 0; j < CurrentUserData.getCurrentUserData().
                    getCurrentMoviesList().size(); j++) {
                for (int k = 0; k < CurrentUserData.getCurrentUserData().getCurrentMoviesList().
                        get(j).getGenres().size(); k++) {
                    if (set.getKey().equals(CurrentUserData.getCurrentUserData().
                            getCurrentMoviesList().get(j).getGenres().get(k))
                            && (!watched(CurrentUserData.getCurrentUserData().
                                    getCurrentMoviesList().get(j).getName()))) {
                        Notification notification = new Notification(CurrentUserData.
                                getCurrentUserData().getCurrentMoviesList().get(j).getName(),
                                "Recommendation");
                        // add the recommendation to the current user's notifications list
                        CurrentUserData.getCurrentUserData().getCurrentUser().getNotifications().
                                add(notification);
                        User copyUser = new User(CurrentUserData.getCurrentUserData().
                                getCurrentUser());
                        print(objectMapper, output, copyUser);
                        return;
                    }
                }
            }
        }
        // in case no recommendation has been found
        Notification notification = new Notification("No recommendation", "Recommendation");
        CurrentUserData.getCurrentUserData().getCurrentUser().getNotifications().add(notification);
        User copyUser = new User(CurrentUserData.getCurrentUserData().getCurrentUser());
        print(objectMapper, output, copyUser);
    }

    /** Method that prints in the output file the user with his movie recommendation */
    public void print(final ObjectMapper objectMapper, final ArrayNode output,
                      final User copyUser) {
        ObjectNode node = objectMapper.createObjectNode();
        node.put("error", (JsonNode) null);
        node.putPOJO("currentMoviesList", null);
        node.putPOJO("currentUser", copyUser);
        output.add(node);
    }

    /** Sort the hashmap of genres by the number of likes each of them has
     * In case of equality, sort them lexicographically by hashmap's key */
    public static HashMap<String, Integer> sortByNrLikes(final HashMap<String, Integer> hm) {
        // create a list from elements of HashMap
        List<Map.Entry<String, Integer>>list =
                new LinkedList<>(hm.entrySet());

        // sort the list
        list.sort((genre1, genre2) -> {
            if (genre2.getValue() > genre1.getValue()) {
                return 1;
            } else if (genre1.getValue().equals(genre2.getValue())) {
                if (genre2.getKey().compareTo(genre1.getKey()) > 0) {
                    return -1;
                } else if (genre1.getKey().compareTo(genre2.getKey()) == 0) {
                    return 0;
                } else if (genre1.getKey().compareTo(genre2.getKey()) > 0) {
                    return 1;
                }
            } else {
                return -1;
            }
            return 1;
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> map : list) {
            temp.put(map.getKey(), map.getValue());
        }
        return temp;
    }

    /** Check if the movie about to be recommended has already been watched by the user */
    public boolean watched(final String movieName) {
        for (int i = 0; i < CurrentUserData.getCurrentUserData().getCurrentUser().
                getWatchedMovies().size(); i++) {
            if (CurrentUserData.getCurrentUserData().getCurrentUser().
                    getWatchedMovies().get(i).getName().equals(movieName)) {
                return true;
            }
        }
        return false;
    }
}
