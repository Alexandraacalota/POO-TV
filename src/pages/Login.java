package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import pootv.CurrentUserData;
import pootv.DataBase;
import pootv.Output;
import pootv.User;

import java.util.ArrayList;

public final class Login extends Page {
    public Login() { }

    /** User can switch from Login page to Authenticated Homepage */
    @Override
    public boolean changeToAuthenticatedHomepage() {
        return true;
    }

    /** User can refresh Login page */
    @Override
    public boolean changeToLogin() {
        return true;
    }

    /** The method has been called from the Login page */
    @Override
    public boolean isLoginPage() {
        return true;
    }

    /** Method that checks if the provided name and password match any user from the database
     * and, if so, logs him into his account */
    public Page login(final DataBase dataBase, final String name, final String password,
                      final ObjectMapper objectMapper, final ArrayNode output,
                      final CurrentUserData currentUserData) {
        ArrayList<User> users = dataBase.getUsers();
        Output message = new Output();
        for (User user : users) {
            if ((user.getCredentials().getName().equals(name)) && (user.getCredentials().
                    getPassword().equals(password))) {
                // login successful
                currentUserData.setCurrentUser(user);
                User copyUser = new User(user);
                message.generalOutput(objectMapper, output, new ArrayList<>(), copyUser, true);
                return new Authenticated();
            }
        }
        // login unsuccessful
        message.generalOutput(objectMapper, output, new ArrayList<>(), null, false);
        return new Unauthenticated();
    }
}
