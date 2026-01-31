package api.tests;

import api.base.BaseTest;
import api.endpoints.StudentGroupMemberEndpoints;
import api.models.request.AddStudentToGroupRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class US106_AddStudentToGroupTest extends BaseTest {

    // Burada groupId ve studentId nereden gelecek?
    // 1) US105 create edip groupId üretirsin (en temiz)
    // 2) Sabit bir groupId/studentId kullanırsın (riskli)

    private final String groupId = "PUT_YOUR_GROUP_ID";
    private final String studentId = "PUT_YOUR_STUDENT_ID";

    @Test
    public void addStudentToGroup() {
        AddStudentToGroupRequest body = new AddStudentToGroupRequest(List.of(studentId));

        Response response =
                request
                        .pathParam("groupId", groupId)
                        .body(body)
                        .post(StudentGroupMemberEndpoints.ADD_STUDENT);

        logResponse(response);
        Assert.assertEquals(response.statusCode(), 200);
    }
}
