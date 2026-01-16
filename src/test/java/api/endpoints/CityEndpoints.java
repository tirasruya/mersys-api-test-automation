package api.endpoints;

import api.base.BaseTest;
import api.models.request.CityRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CityEndpoints extends BaseTest {

    public static final String CITIES = "/school-service/api/cities";
    public static final String CITY_BY_ID = "/school-service/api/cities/{id}";

    public static Response createCity(CityRequest body) {
        return given()
                .spec(request)
                .body(body)
                .when()
                .post(CITIES);
    }

    public static Response updateCity(CityRequest body) {
        return given()
                .spec(request)
                .body(body)
                .when()
                .put(CITIES);
    }

    public static Response deleteCity(String cityId) {
        return given()
                .spec(request)
                .pathParam("id", cityId)
                .when()
                .delete(CITY_BY_ID);
    }

    public static Response searchCity(Object body) {
        return given()
                .spec(request)
                .contentType(io.restassured.http.ContentType.JSON) // Diğerlerini bozmaz, sadece bu isteği etkiler
                .accept(io.restassured.http.ContentType.JSON)
                .body(body)
                .when()
                .post(CITIES + "/search");
    }
}