package practicum;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static practicum.Literals.*;

public abstract class BaseHTTPClient {
    private RequestSpecification baseRequestSpec = new RequestSpecBuilder()
                .setBaseUri(HOST)
                .addHeader(CONTENT_TYPE_HEADER_NAME, JSON_HEADER_VALUE)
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

    protected Response makeGetRequest(String path, Map<String, String> params){
        return given().spec(baseRequestSpec).queryParams(params).get(path).thenReturn();
    }

    protected Response makePostRequest(String path, Object body){
        return given().spec(baseRequestSpec).body(body).post(path).thenReturn();
    }

    protected Response makePutRequest(String path, Object body){
        return given().spec(baseRequestSpec).body(body).put(path).thenReturn();
    }

    protected Response makePutRequest(String path, Map<String, String> params){
        return given().spec(baseRequestSpec).queryParams(params).put(path).thenReturn();
    }

    protected Response makePutRequest(String path){
        return given().spec(baseRequestSpec).put(path).thenReturn();
    }

    protected Response makeDeleteRequest(String path, Object body){
        return given().spec(baseRequestSpec).body(body).delete(path).thenReturn();
    }
}
