package pro.learnup.api;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.api.ext.ApiTestExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(ApiTestExtension.class)
@DisplayName("/api/user")
public class ApiUserTest {

    String token;
    String userName;

    @BeforeEach
    void setUp() {
        userName = new Faker().name().fullName();

        token = given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya2\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \""+ userName +"\"\n" +
                        "}")
                .post("http://localhost:5000/api/auth/register")
                .then()
                .statusCode(201)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName))
                .extract()
                .jsonPath()
                .getString("token");
    }

    @Test()
    @DisplayName("/api/user: 200: получение информации о юзере авторизованным пользователем")
    void successfulGetUserTest() {
        given()
                .header(new Header("Authorization", "Bearer " + token))
                .get("/api/user")
                .then()
                .statusCode(200)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName));
    }
}
