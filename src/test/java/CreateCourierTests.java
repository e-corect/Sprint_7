
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static practicum.Literals.*;
import io.qameta.allure.Description;

public class CreateCourierTests {

    private CourierSteps courierSteps = new CourierSteps();

    @Before
    @Description("Подготовка тествых данных - создание курьера")
    public void prepare(){
        courierSteps.createRandomCourier();
    }

    @Test
    @Description("Проверяем, что курьера можно создать")
    public void createCourierTest(){
        courierSteps.checkSuccessResponse(courierSteps.getResponse(), 201);
    }

    @Test
    @Description("Проверяем, что нельзя нельзя создать двух одинаковых курьеров")
    public void duplicatedCourierProfileTest(){
        courierSteps.checkUnsuccessResponse(courierSteps.createCourier().getResponse(), 409, LOGIN_IS_TAKEN_ERROR);
    }

    @After
    @Description("Удаление тестовых данных")
    public void clear(){
        courierSteps.removeCourier();
    }
}
