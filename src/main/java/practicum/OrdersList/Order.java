package practicum.OrdersList;

import java.util.ArrayList;

public class Order {

    public Order(Integer id, Object courierId, String firstName,
                 String lastName, String address, String metroStation,
                 String phone, Integer rentTime, String deliveryDate,
                 Integer track, ArrayList<String> color, String comment,
                 String createdAt, String updatedAt, Integer status) {
        this.id = id;
        this.courierId = courierId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.track = track;
        this.color = color;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    Integer id;
    Object courierId;
    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phone;
    Integer rentTime;
    String deliveryDate;
    Integer track;
    ArrayList<String> color;
    String comment;
    String createdAt;
    String updatedAt;
    Integer status;

    public Integer getId() {
        return id;
    }

}
