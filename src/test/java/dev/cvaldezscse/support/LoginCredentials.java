package dev.cvaldezscse.support;

/**
 * LoginCredentials
 *
 * @author Carlos Valdez (cvaldez.scse@gmail.com)
 * @version 1.0.0
 * @description
 * @modified 7/14/20
 * @since 7/14/20
 */
public enum LoginCredentials {
    USER1("dummy_user@localhost.com", "hello123!")
    ;

    private String username;
    private String password;

    LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
