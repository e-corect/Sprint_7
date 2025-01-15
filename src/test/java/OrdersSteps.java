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
import static practicum.Literals.ORDER_ID_JSONPATH;
import static practicum.Literals.ORDER_TRACK_JSONPATH;

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
        response = ordersAPI.cancelOrder(response.getBody().jsonPath().get(ORDER_TRACK_JSONPATH).toString());
        return this;
    }

    @Step("Проверка ответа сервера после создания заказа")
    public void checkOrderCreationResponse(Integer expectedStatusCode){
        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains(ORDER_TRACK_JSONPATH));
        response.then().assertThat().body(ORDER_TRACK_JSONPATH, instanceOf(Integer.class));
    }

    @Step("Генерируем и создаем в системе случайное число (от 2 до 5) заказов, вытаскиваем из ответа trackId, получаем заказ, вытаскиваем его Id, формируем список")
    public Integer generateOrderAndGetOrderId(){
        Integer orderId = ordersAPI.getOrderId(generateOrder()
                .response.as(TrackOrder.class)
                .getTrack())
                .getBody()
                .jsonPath().get(ORDER_ID_JSONPATH);
        ordersIdList.add(orderId);
        return orderId;
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
