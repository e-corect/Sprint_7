package practicum;

public class Literals {

    public static final String HOST = "https://qa-scooter.praktikum-services.ru";
    public static final String COURIER_PATH = "/api/v1/courier/";
    public static final String ORDERS_PATH = "/api/v1/orders";
    public static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel?track=";
    public static final String ACCEPT_ORDER_PATH = "/api/v1/orders/accept/";
    public static final String GET_ORDER_PATH = "api/v1/orders/track";
    public static final String FINISH_ORDER_PATH = "/api/v1/orders/finish/";

    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    public static final String JSON_HEADER_VALUE = "application/json";
    public static final String PROFILE_CREATION_ERROR = "Недостаточно данных для создания учетной записи";
    public static final String LOGIN_IS_TAKEN_ERROR = "Этот логин уже используется. Попробуйте другой.";
    public static final String NOT_ENOUGH_LOGIN_INFO_ERROR = "Недостаточно данных для входа";
    public static final String ACCOUNT_NOT_FOUND_ERROR = "Учетная запись не найдена";
    }
