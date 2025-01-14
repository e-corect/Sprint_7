import com.sun.istack.Nullable;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import practicum.CourierAPI;
import practicum.CourierId;
import practicum.CourierProfile;


import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static practicum.TestData.*;
import static practicum.Utils.*;


public class CourierSteps {

    private Response response;
    private CourierId courierId;
    private CourierProfile courierProfile;
    private CourierAPI courierAPI = new CourierAPI();

    public void setCourierProfile(CourierProfile courierProfile) {
        this.courierProfile = courierProfile;
    }

    public CourierProfile getCourierProfile() {
        return courierProfile;
    }

    public void setCourierId(CourierId courierId) {
        this.courierId = courierId;
    }

    public CourierId getCourierId() {
        return courierId;
    }

    public Response getResponse() {
        return response;
    }

    @Step("Создаем объект профиля курьера с псевдослучайными параметрами")
    public CourierSteps courier(){
        courierProfile =  new CourierProfile(LOGIN_PREFIX + getTimestamp(),
                CORRECT_COURIER_PWD,
                COURIER_NAMES_LIST[getRandomInt(0, COURIER_NAMES_LIST.length)]);
        return this;
    }

    @Step("Проверка успешного ответа сервера на соответствие ожидаемым критериям")
    public void checkSuccessResponse(Response response, Integer expectedStatusCode, @Nullable String expectedErrorMessage){

        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertTrue(response.getBody().jsonPath().get("ok"));
    }

    @Step("Проверка неуспешного ответа сервера на соответствие ожидаемым критериям")
    public void checkUnsuccessResponse(Response response, Integer expectedStatusCode, @Nullable String expectedErrorMessage){

        Assert.assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
        Assert.assertEquals(expectedErrorMessage, response.getBody().jsonPath().get("message"));
    }

    @Step("Создание в системе курьера со случайными параметрами.")
    public CourierSteps createRandomCourier(){
        response = courier().createCourier().response;
        return this;
    }
    @Step("Создание в системе курьера")
    public CourierSteps createCourier(){
        response = courierAPI.createCourier(courierProfile);
        return this;
    }

    @Step("Вход курьера в систему.")
    public Response courierLogin(String login, String pwd){
        if(login == null && pwd == null){
            response = courierAPI.courierLogin(courierProfile);
            courierId = response.as(CourierId.class);
        } else{
            response = courierAPI.courierLogin(login, pwd);
        }
        return response;
    }

    @Step("Удаление курьера из системы.")
    public Response removeCourier(){
        courierLogin(null, null);
        return response = courierAPI.deleteCourier(courierId);
    }
    @Step("Проверка, что после успешной авторизации курьера возвращается его id в системе")
    public void checkLoginResponse(){
        Assert.assertNotNull(courierId.getId());
        response.then().assertThat().body("id", instanceOf(Integer.class));
    }
}
