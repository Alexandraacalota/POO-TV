package pootv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;

public final class Output {

    /** Method that prints the output both in case of a successfully accomplished
     * action or in case of an error */
    public void generalOutput(final ObjectMapper objectMapper, final ArrayNode output,
                              final ArrayList<Movie> movies, final User user, final boolean ok) {
        ObjectNode node = objectMapper.createObjectNode();
        if (ok) {
            node.put("error", (JsonNode) null);
        } else {
            node.put("error", "Error");
        }
        if (movies != null) {
            ArrayList<Movie> copyMovies = new ArrayList<>();
            for (Movie movie : movies) {
                Movie copyMovie = new Movie(movie);
                copyMovies.add(copyMovie);
            }
            node.putPOJO("currentMoviesList", copyMovies);
        } else {
            node.putPOJO("currentMoviesList", new ArrayList<>());
        }
        if (user != null) {
            User copyUser = new User(user);
            node.putPOJO("currentUser", copyUser);
        } else {
            node.putPOJO("currentUser", null);
        }
        output.add(node);
    }
}
