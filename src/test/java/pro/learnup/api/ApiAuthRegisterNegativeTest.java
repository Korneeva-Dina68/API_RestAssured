package pro.learnup.api;

import com.github.javafaker.Faker;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.ext.ApiTestExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(ApiTestExtension.class)
@DisplayName("/api/auth/register")
public class ApiAuthRegisterNegativeTest {
    String conflict = "{\n" +
            "  \"message\": \"User already exists\"\n" +
            "}";
    @Test()
    @DisplayName("/api/auth/register: 409: ошибка: пользователь уже существует")
    void createAnExistingUser(){


        given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \"vasya\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(409)
                .body(Matchers.stringContainsInOrder("{\"message\":\"User already exists\"}"));
    }
}
