package input;

public class UserInput {
    private CredentialsInput credentials;

    /** Returns user's credentials */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /** Sets user's credentials */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }
}
