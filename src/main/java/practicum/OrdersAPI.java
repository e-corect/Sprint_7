package practicum;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static practicum.Literals.*;

public class OrdersAPI extends BaseHTTPClient{

    public Response cancelOrder(String orderId){
        return makePutRequest(CANCEL_ORDER_PATH + orderId);
    }

    public Response createOrder(Order order){
        return makePostRequest(ORDERS_PATH, order);
    }

    public Response getOrdersList(Map<String, String> queryParams){

        return makeGetRequest(ORDERS_PATH, queryParams);
    }

    public Response acceptOrder(Integer orderId, Integer courierId){
        Map<String, String> params = new HashMap<>();
        params.put("courierId", courierId.toString());
        return makePutRequest(ACCEPT_ORDER_PATH + orderId.toString(), params);
    }

    public Response getOrderId(Integer trackId){
        Map<String, String> params = new HashMap<>();
        params.put("t", trackId.toString());
        return makeGetRequest(GET_ORDER_PATH, params);
    }

    public Response finishOrder(Integer orderId){
        OrderId body = new OrderId(orderId);
        return makePutRequest(FINISH_ORDER_PATH + orderId.toString(), body);
    }
}
