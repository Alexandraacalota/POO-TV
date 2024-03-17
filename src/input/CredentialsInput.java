package input;

public class CredentialsInput {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public CredentialsInput() { }
    public CredentialsInput(final CredentialsInput credentials) {
        this.name = credentials.name;
        this.password = credentials.password;
        this.accountType = credentials.accountType;
        this.country = credentials.country;
        this.balance = credentials.balance;
    }

    public CredentialsInput(final String name, final String password, final String accountType,
                            final String country, final String balance) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.country = country;
        this.balance = balance;
    }

    /** Returns the name of the user */
    public String getName() {
        return name;
    }

    /** Returns the password of the user */
    public String getPassword() {
        return password;
    }

    /** Returns the account type of the user (standard/ premium) */
    public String getAccountType() {
        return accountType;
    }

    /** Returns the country the user lives in */
    public String getCountry() {
        return country;
    }

    /** Returns the user's balance */
    public String getBalance() {
        return balance;
    }

    /** Sets the name of the user */
    public void setName(final String name) {
        this.name = name;
    }

    /** Sets the password of the user */
    public void setPassword(final String password) {
        this.password = password;
    }

    /** Sets the account type of the user */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /** Sets the country the user lives in */
    public void setCountry(final String country) {
        this.country = country;
    }

    /** Sets the user's balance */
    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
