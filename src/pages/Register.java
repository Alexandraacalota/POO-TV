package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.ActionInput;
import pootv.CurrentUserData;
import pootv.DataBase;
import pootv.Output;
import pootv.User;

import java.util.ArrayList;

public final class Register extends Page {
    public Register() { }

    /** The current page can switch to the Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        return true;
    }

    /** The user can refresh the Register page */
    @Override
    public boolean changeToRegister() {
        return true;
    }

    /** The user is currently on the Register page */
    @Override
    public boolean isRegisterPage() {
        return true;
    }

    /** Method that adds a new user to the database */
    public Page register(final DataBase dataBase, final ActionInput action, final CurrentUserData
            currentUserData, final ObjectMapper objectMapper, final ArrayNode output) {
        final int freePremiumMovies = 15;
        Output message = new Output();
        ArrayList<User> users = dataBase.getUsers();
        for (User user : users) {
            // in case there is another user with the same name
            if (user.getCredentials().getName().equals(action.getCredentials().getName())) {
                // print error message
                message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
                return new Unauthenticated();
            }
        }
        User newUser = new User(action.getCredentials(), 0, freePremiumMovies, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        // set number of free premium movies to 15 for all users
        newUser.setNumFreePremiumMovies(freePremiumMovies);
        // add a new user to the database
        dataBase.getUsers().add(users.size(), newUser);
        currentUserData.setCurrentUser(newUser);
        User copyUser = new User(newUser);
        message.generalOutput(objectMapper, output, new ArrayList<>(), copyUser, true);
        return new Authenticated();
    }
}
