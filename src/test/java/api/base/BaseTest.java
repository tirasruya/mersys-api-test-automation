package api.base;

import api.endpoints.AuthEndpoints;
import api.models.request.LoginRequest;
import api.models.response.LoginResponse;
import api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected static RequestSpecification request;

    protected static final Logger LOGGER =
            LogManager.getLogger(BaseTest.class);

    private static String token;

    @BeforeSuite
    public void setup() {

        LOGGER.info("=== Test Suite Started ===");

        RestAssured.baseURI = ConfigReader.get("base.url");
        LOGGER.info("Base URI set to {}", RestAssured.baseURI);

        LoginRequest loginRequest = new LoginRequest(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        LOGGER.info("Login request prepared");

        LoginResponse loginResponse =
                given()
                        .contentType(ContentType.JSON)
                        .body(loginRequest)
                        .post(AuthEndpoints.LOGIN)
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(LoginResponse.class);

        token = loginResponse.getAccess_token();
        LOGGER.info("Token successfully generated");

        request =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .auth().oauth2(token);

        LOGGER.info("RequestSpecification initialized with token");
    }
}