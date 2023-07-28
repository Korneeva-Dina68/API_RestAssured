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
public class ApiAuthRegisterTest {
    @Test()
    @DisplayName("/api/auth/register: 201: успешное создание юзера")
    void creatUserTest(){

        Faker faker = new Faker();
        String userName = faker.name().fullName();

        given()
                .body("{\n" +
                        "  \"address\": \"russia\",\n" +
                        "  \"email\": \"sdgrdsg@vas.ru\",\n" +
                        "  \"password\": \"vasya2\",\n" +
                        "  \"phone\": \"8999999999\",\n" +
                        "  \"username\": \""+ userName +"\"\n" +
                        "}")
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .body("address", Matchers.equalTo("russia"))
                .body("email", Matchers.equalTo("sdgrdsg@vas.ru"))
                .body("phone", Matchers.equalTo("8999999999"))
                .body("username", Matchers.equalTo(userName));
    }
}
