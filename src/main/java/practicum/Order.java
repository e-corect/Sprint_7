package practicum;

import com.sun.istack.Nullable;

import static practicum.TestData.*;
import static practicum.Utils.*;

import java.util.ArrayList;

public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private ArrayList<String> color;

    public ArrayList<String> getColor() {
        return color;
    }

    public String getComment() {
        return comment;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public String getAddress() {
        return address;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setColor(ArrayList<String> color) {
        this.color = color;
    }

    public Order(@Nullable String scootersColor1, @Nullable String scootersColor2){
        ArrayList<String> color = new ArrayList<>();
        if (scootersColor1 !=null ){
            color.add(scootersColor1);
        }
        if (scootersColor2 !=null ){
            color.add(scootersColor2);
        }
        this.firstName = generateFirstName();
        this.lastName = generateLastName();
        this.address = generateAddress();
        this.metroStation = getRandomInt(1, 9);
        this.phone = generatePhone();
        this.rentTime = getRandomInt(1,8);
        this.deliveryDate = getDate(2);
        this.comment = ORDERS_COMMENT;
        this.color = color;
    }

    public Order(){
        ArrayList<String> color = new ArrayList<>();
        String[] colors = {SCOOTERS_COLOR_GREY, SCOOTERS_COLOR_BLACK};
        color.add(colors[getRandomInt(0, colors.length)]);
        this.firstName = generateFirstName();
        this.lastName = generateLastName();
        this.address = generateAddress();
        this.metroStation = getRandomInt(1, 9);
        this.phone = generatePhone();
        this.rentTime = getRandomInt(1,8);
        this.deliveryDate = getDate(2);
        this.comment = ORDERS_COMMENT;
        this.color = color;
    }
}
