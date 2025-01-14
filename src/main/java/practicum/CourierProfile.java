package practicum;

import com.sun.istack.Nullable;

public class CourierProfile extends CourierCreds{
    @Nullable
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public CourierProfile(String login, String password, String firstName) {
        super(login, password);
        this.firstName = firstName;
    }
}
