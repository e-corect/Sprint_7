package practicum;

public class CourierProfile extends CourierCreds{

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public CourierProfile(String login, String password, String firstName) {
        super(login, password);
        this.firstName = firstName;
    }
}
