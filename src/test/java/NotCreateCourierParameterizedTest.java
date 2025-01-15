import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import practicum.CourierProfile;
import io.qameta.allure.Description;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;

import static practicum.TestData.*;
import static practicum.Utils.*;
import static practicum.Literals.*;

@RunWith(Parameterized.class)
public class NotCreateCourierParameterizedTest {

    private final CourierProfile body;
    private final Integer expectedStatusCode;
    private final String expectedErrorMessage;
    private CourierSteps courierSteps = new CourierSteps();

    public NotCreateCourierParameterizedTest(CourierProfile body, Integer expectedStatusCode, String expectedErrorMessage) {
        this.body = body;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new CourierProfile(LOGIN_PREFIX + getTimestamp(), CORRECT_COURIER_PWD, ""), 400, PROFILE_CREATION_ERROR},  // поле firstName обязательное, но профиль курьера создается
                {new CourierProfile(LOGIN_PREFIX + getTimestamp(), "", COURIER_NAMES_LIST[0]), 400, PROFILE_CREATION_ERROR},
                {new CourierProfile("", CORRECT_COURIER_PWD, COURIER_NAMES_LIST[0]), 400, PROFILE_CREATION_ERROR}
        };
    }

    @Before
    @Description("Подготовка тестовых данных - создание профиля курьера в системе")
    public void prepare(){
        courierSteps.setCourierProfile(body);
        courierSteps.createCourier();
    }

    @Test
    @Description("Проверяем кейс: если какого-то поля нет, запрос  'POST:/api/v1/courier' возвращает ошибку")
    public void courierNotCreatedTest(){
        courierSteps.checkUnsuccessResponse(courierSteps.getResponse(),expectedStatusCode, expectedErrorMessage);
    }
}
