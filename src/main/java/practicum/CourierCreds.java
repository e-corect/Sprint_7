package practicum;

import com.sun.istack.Nullable;

public class CourierCreds {
    @Nullable
    private String login;
    @Nullable
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
