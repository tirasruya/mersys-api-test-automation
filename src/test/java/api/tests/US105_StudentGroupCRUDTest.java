package api.tests;

import api.base.BaseTest;
import api.endpoints.StudentGroupEndpoints;
import api.models.request.StudentGroupRequest;
import api.models.response.StudentGroupResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class US105_StudentGroupCRUDTest extends BaseTest {

    private String groupId;

    @Test(priority = 1, description = "US105 - Create student group")
    public void createStudentGroup() {
        int rnd = new Random().nextInt(10_000);

        StudentGroupRequest body = new StudentGroupRequest(
                "US105-Group-" + rnd,
                "Automation created group",
                true,
                true,
                true
        );

        Response response =
                request
                        .body(body)
                        .post(StudentGroupEndpoints.CREATE);

        logResponse(response);
        Assert.assertEquals(response.statusCode(), 201);

        StudentGroupResponse res = response.as(StudentGroupResponse.class);
        groupId = res.getId();
        Assert.assertNotNull(groupId, "GroupId is null after create!");
    }

    @Test(priority = 2, dependsOnMethods = "createStudentGroup", description = "US105 - Update student group")
    public void updateStudentGroup() {
        Assert.assertNotNull(groupId);

        StudentGroupRequest body = new StudentGroupRequest(
                "US105-Group-Updated",
                "Updated description",
                true,
                false,
                true
        );
        body.setId(groupId);

        Response response =
                request
                        .body(body)
                        .put(StudentGroupEndpoints.UPDATE);

        logResponse(response);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3, dependsOnMethods = "createStudentGroup", description = "US105 - Delete student group")
    public void deleteStudentGroup() {
        Assert.assertNotNull(groupId);

        Response response =
                request
                        .pathParam("id", groupId)
                        .delete(StudentGroupEndpoints.DELETE);

        logResponse(response);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 204);
    }
}
