import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static practicum.Literals.*;

public class CourierLoginTests {

    private CourierSteps courierSteps = new CourierSteps();

    @Before
    @Description("Подготовка тестовых данных - создание курьера в системе")
    public void prepare(){
        courierSteps.createRandomCourier();
    }

    @Test
    @Description("Проверка наличия у курьера возможности авторизоваться.")
    public void courierLoginTest(){
        courierSteps.courierLogin(null, null);
        courierSteps.checkLoginResponse();
    }

    @Test
    @Description("Проверка обязательности значений поля login и password.")
    public void courierLoginWithoutLoginAndPwdTest(){

        courierSteps.checkUnsuccessResponse(courierSteps.courierLogin("", ""),
                400, NOT_ENOUGH_LOGIN_INFO_ERROR);
    }

    @Test
    @Description("Проверка обязательности значения поля 'login'")
    public void courierLoginWithoutLoginTest(){
        courierSteps.checkUnsuccessResponse(
                courierSteps.courierLogin("",
                        courierSteps.getCourierProfile().getPassword()),
                400, NOT_ENOUGH_LOGIN_INFO_ERROR);
    }

    @Test
    @Description("Проверка обязательности значения поля 'password'")
    public void courierLoginWithoutPwdTest(){
        courierSteps.checkUnsuccessResponse(
                courierSteps.courierLogin(courierSteps.getCourierProfile().getLogin(),
                        ""),
                400, NOT_ENOUGH_LOGIN_INFO_ERROR);
    }

    @Test
    @Description("Проверка возможности авторизоваться под несуществующим пользователем")
    public void courierLoginWithWrongLoginTest(){
        courierSteps.checkUnsuccessResponse(
                courierSteps.courierLogin(courierSteps.getCourierProfile().getLogin() + "1",
                courierSteps.getCourierProfile().getPassword()), 404, ACCOUNT_NOT_FOUND_ERROR);
    }

    @After
    @Description("Удаляем профиль созданного курьера")
    public void clean(){
        courierSteps.removeCourier();
    }
}
