import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.Order;

import static practicum.TestData.SCOOTERS_COLOR_BLACK;
import static practicum.TestData.SCOOTERS_COLOR_GREY;

@RunWith(Parameterized.class)
public class CreateOrdersParametrizedTest {

    private Order order;
    private Integer expectedStatusCode;
    private OrdersSteps ordersSteps = new OrdersSteps();

    public CreateOrdersParametrizedTest(Order order, Integer expectedStatusCode){
        this.order = order;
        this.expectedStatusCode = expectedStatusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {

        return new Object[][]{
                {new Order(SCOOTERS_COLOR_GREY, null), 201},
                {new Order(SCOOTERS_COLOR_GREY, SCOOTERS_COLOR_GREY), 201},
                {new Order(SCOOTERS_COLOR_BLACK, null), 201},
                {new Order(SCOOTERS_COLOR_BLACK, SCOOTERS_COLOR_BLACK), 201},
                {new Order(SCOOTERS_COLOR_GREY, SCOOTERS_COLOR_BLACK), 201},
                {new Order(null, null), 201}
        };
    }

    @Before
    @Description("Подготовка тестовых данных - создание заказа в системе")
    public void prepare(){
        ordersSteps.setOrder(order);
        ordersSteps.createOrder();
    }

    @Test
    @Description("Проверка ответа системы после создания заказа")
    public void createOrderTest(){
        ordersSteps.checkOrderCreationResponse(expectedStatusCode);
    }

    @After
    @Description("Отмена созданного заказа")
    public void clean(){
        ordersSteps.cancelOrder();
    }
}
