package practicum;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static practicum.Literals.*;

public class CourierAPI extends BaseHTTPClient{

    @Step("Отправка запроса на создание курьера. Тело запроса передается в виде параметра метода.")
    public Response createCourier(CourierProfile body){
        return makePostRequest(COURIER_PATH, body).then().extract().response();
    }

    @Step("Отправка запроса на аутентификацию курьера в системе. Тело запроса формируется внутри метода из переданных параметров.")
    public Response courierLogin(String login, String pwd){
        CourierCreds body = new CourierCreds(login, pwd);
        return makePostRequest(COURIER_PATH + "login", body).then().extract().response();
    }

    @Step("Отправка запроса на аутентификацию курьера в системе. Параметры запроса берутся из переданного в метод объекта профиля курьера.")
    public Response courierLogin(CourierProfile body){
        return courierLogin(body.getLogin(), body.getPassword());
    }

    @Step("Отправка запроса на удаление курьера из системы.")
    public Response deleteCourier(CourierId courierId){
        return makeDeleteRequest(COURIER_PATH + courierId.getId(), courierId).then().extract().response();
    }
}
