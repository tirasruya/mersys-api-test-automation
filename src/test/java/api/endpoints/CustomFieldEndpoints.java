package api.endpoints;

import api.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CustomFieldEndpoints extends BaseTest {

    private String endpoint = "/school-service/api/entity-field";

    private String tenantId = "5e39ade1cb4c066deeb43015";

    private void checkAuth() {
        if (request == null) { super.setup(); }
    }

    public Response createField(Object body) {
        checkAuth();

        return given()
                .spec(request)
                .header("tenantId", "5e39ade1cb4c066deeb43015")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(endpoint);
    }

    public Response updateField(Object body) {
        checkAuth();
        return given()
                .spec(request)
                .header("tenantId", "5e39ade1cb4c066deeb43015")
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(endpoint);
    }

    public Response deleteField(String id) {
        checkAuth();
        return given()
                .spec(request)
                .header("tenantId", "5e39ade1cb4c066deeb43015")
                .pathParam("id", id)
                .when()
                .delete(endpoint + "/{id}");
    }
}