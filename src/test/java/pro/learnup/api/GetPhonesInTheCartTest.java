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
@DisplayName("/api/cart")
public class GetPhonesInTheCartTest {

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
                .post("/api/auth/register")
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
    @DisplayName("/api/cart: 200: проверка наличия телефона в корзине")
    void getPhonesCart() {
        given()
                .header(new Header("Authorization", "Bearer " + token))
                .body("{\n" +
                        "  \"product\": \"64936257b6fcf1a02a330baa\",\n" +
                        "  \"quantity\": 1,\n" +
                        "  \"user\": \"61b3677584790d001212a841\"\n" +
                        "}")
                .post("/api/cart")
                .then()
                .statusCode(200);
        given()
                .header(new Header("Authorization", "Bearer " + token))
                .get("/api/cart")
                .then()
                .statusCode(200)
                .body("items.product.info.name", Matchers.hasItem("Apple iPhone 8 Plus"));
    }
}
