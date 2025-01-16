import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import practicum.CourierId;

public class GetOrdersListTests {

    private CourierSteps courierSteps = new CourierSteps();
    private OrdersSteps ordersSteps = new OrdersSteps();

    @Before
    @Description("Подготовка тестовых данных - создание заказов в системе")
    public void prepare(){
        courierSteps.setCourierId(courierSteps.createRandomCourier().courierLogin(null, null).as(CourierId.class));
        for (int i=0;i<4;i++){
        ordersSteps.acceptOrder(ordersSteps.generateOrderAndGetOrderId(), courierSteps.getCourierId().getId());
        }
    }

    @Test
    @Description("Запрашивается список доступных заказов и проверяется ответ сервера")
    public void getOrdersList(){
        ordersSteps.getOrdersList(courierSteps.getCourierId().getId()).checkOrdersListResponse();
    }

    @After
    @Description("Удаляем созданные в процессе теста сущности")
    public void clear(){
        ordersSteps.finishOrdersList();
        courierSteps.removeCourier();
    }
}
