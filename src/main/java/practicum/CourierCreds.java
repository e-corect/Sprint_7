package practicum;

public class CourierCreds {

    private String login;

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
