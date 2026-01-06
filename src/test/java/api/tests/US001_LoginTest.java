package api.tests;

import api.endpoints.AuthEndpoints;
import api.models.request.LoginRequest;
import api.models.response.LoginResponse;
import api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US001_LoginTest {

    private static final Logger LOGGER =
            LogManager.getLogger(US001_LoginTest.class);

    @Test
    public void loginSuccessfully() {

        LOGGER.info("US001 Login test started");

        // Arrange
        LoginRequest loginRequest = new LoginRequest(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        LOGGER.info("Login request prepared for user: {}", ConfigReader.get("username"));

        // Act
        Response response =
                RestAssured
                        .given()
                        .baseUri(ConfigReader.get("base.url"))
                        .contentType(ContentType.JSON)
                        .body(loginRequest)
                        .when()
                        .post(AuthEndpoints.LOGIN);

        LOGGER.info("Login request sent to {}", AuthEndpoints.LOGIN);
        LOGGER.info("Response status code: {}", response.getStatusCode());

        // Assert
        response.then().statusCode(200);

        LoginResponse loginResponse = response.as(LoginResponse.class);

        Assert.assertNotNull(loginResponse.getAccess_token(), "Access token is null!");
        Assert.assertNotNull(loginResponse.getRefresh_token(), "Refresh token is null!");

        LOGGER.info("Login successful, access token received");
    }
}
