package practicum.OrdersList;

import java.util.ArrayList;

public class OrdersList {

    public OrdersList(ArrayList<Order> orders, PageInfo pageInfo, ArrayList<AvailableStation> availableStations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.availableStations = availableStations;
    }

    public ArrayList<practicum.OrdersList.Order> getOrders() {
        return orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public ArrayList<AvailableStation> getAvailableStations() {
        return availableStations;
    }

    private ArrayList<practicum.OrdersList.Order> orders;
    private PageInfo pageInfo;
    private ArrayList<AvailableStation> availableStations;

}
