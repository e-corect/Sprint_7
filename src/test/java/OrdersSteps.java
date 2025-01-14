import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import practicum.Order;
import practicum.OrdersAPI;
import practicum.OrdersList.OrdersList;
import practicum.TrackOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static practicum.Utils.getRandomInt;

public class OrdersSteps {

    private Order order;
    private ArrayList<Integer> ordersIdList = new ArrayList<>();
    private Response response;
    private OrdersAPI ordersAPI = new OrdersAPI();

    public void setOrder(Order order) {
        this.order = order;
    }

    @Step("Создание заказа в системе")
    public OrdersSteps createOrder(){
        response = ordersAPI.createOrder(order);
        return this;
    }

    @Step("Генерируем случайный заказ и создаем его в системе")
    public OrdersSteps generateOrder(){
        order = new Order();
        response = ordersAPI.createOrder(order);
        return this;
    }

    @Step("Отмена заказа")
    public OrdersSteps cancelOrder(){
        response = ordersAPI.cancelOrder(response.getBody().jsonPath().get("track").toString());
        return this;
    }

    @Step("Проверка ответа сервера после создания заказа")
    public void checkOrderCreationResponse(Integer expectedStatusCode){
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("track"));
        response.then().assertThat().body("track", instanceOf(Integer.class));
    }

    @Step("Генерируем и создаем в системе случайное число (от 2 до 5) заказов, вытаскиваем из ответа trackId, получаем заказ, вытаскиваем его Id, формируем список")
    public Integer generateOrderAndGetOrderId(){
        Integer orderId = ordersAPI.getOrderId(generateOrder()
                .response.as(TrackOrder.class)
                .getTrack())
                .getBody()
                .jsonPath().get("order.id");
        ordersIdList.add(orderId);
        return orderId;
    }

    @Step("Генерируем и создаем в системе случайное число (от 2 до 5) заказов, вытаскиваем из ответа trackId, получаем заказ, вытаскиваем его Id, формируем список")
    public ArrayList<Integer> generateOrdersAndCreateOrdersList(){
        ArrayList<Integer> ordersIdList = new ArrayList<>();
        for(int i=0;i<getRandomInt(2, 5);i++){      //вернхняя граница исключена
            ordersIdList.add(
                ordersAPI.getOrderId(generateOrder()
                .response.as(TrackOrder.class)
                .getTrack())
                .getBody()
                .jsonPath().get("order.id"));
        }
        return this.ordersIdList = ordersIdList;
    }

    @Step("Получаем список заказов из системы для курьера")
    public OrdersSteps getOrdersList(Integer courierId){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("limit", "5");
        queryParams.put("page", "0");
        queryParams.put("courierId", courierId.toString());
        response = ordersAPI.getOrdersList(queryParams);
        return this;
    }

    @Step("Проверка списка заказов")
    public void checkOrdersListResponse(){
        response.then().statusCode(200);
        OrdersList ordersList = response.as(OrdersList.class);
        Assert.assertTrue(ordersList.getOrders().size() > 0);
        for (int i=0;i<ordersList.getOrders().size();i++){
            Assert.assertThat(ordersList.getOrders().get(i).getId(), instanceOf(Integer.class));
        }
    }

    @Step("Принимаем список заказов на курьера")
    public OrdersSteps acceptOrders(Integer courierId){
        for(int i=0;i<ordersIdList.size();i++){
            response = ordersAPI.acceptOrder(ordersIdList.get(i), courierId);}
        return this;
    }

    @Step("Принимаем заказ на курьера")
    public OrdersSteps acceptOrder(Integer orderId, Integer courierId){
        response = ordersAPI.acceptOrder(orderId, courierId);
        return this;
    }

    @Step("Завершаем заказы из списка")
    public OrdersSteps finishOrdersList(){
        for(Integer orderId : ordersIdList){
            ordersAPI.finishOrder(orderId);}
        return this;
    }
}
