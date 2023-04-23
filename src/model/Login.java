package model;

public class Login {

    private final String username;
    private final String password;

    public Login(String username, String password) throws NullPointerException {

        if (username == null || password == null) {
            throw new NullPointerException("username or password is null.");
        }

        this.username = new String(username);
        this.password = new String(password);
    }

    public String getUsername() {
        return new String(username);
    }

    public String getPassword() {
        return new String(password);
    }
}
