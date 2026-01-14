package api.tests;

import api.base.BaseTest;
import api.endpoints.ExamEndpoints;
import api.models.request.ExamRequest;
import api.models.response.ExamResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US103_ExamCRUDTest extends BaseTest {

    private String examId;
    private String examName = "SDET Exam " + (int) (Math.random() * 1000);

    @Test(priority = 1)
    public void createExam() {

        ExamRequest examBody = new ExamRequest();
        examBody.setName(examName);
        examBody.setRegistrationStartDate("2026-01-14");
        examBody.setRegistrationEndDate("2026-01-30");

        ExamRequest.GradeLevel grade = new ExamRequest.GradeLevel();
        grade.setId("5e909860b0fd8113ea1432b4");
        examBody.setGradeLevel(grade);

        ExamResponse response = given()
                .spec(request) // BaseTest'ten gelen RequestSpecification
                .body(examBody)
                .when()
                .post(ExamEndpoints.CREATE_EXAM)
                .then()
                .statusCode(201) // Kriter: 201 Created
                .body("name", equalTo(examName))
                .extract().as(ExamResponse.class); // Yanıtı modele çevirme

        examId = response.getId();
        System.out.println("Created Exam ID: " + examId);
    }

    @Test(priority = 2, dependsOnMethods = "createExam")
    public void updateExam() {
        ExamRequest updateBody = new ExamRequest();
        updateBody.setId(examId); // Kriter: Güncelleme için ID gövdede gitmeli
        updateBody.setName(examName + " Updated");

        ExamRequest.GradeLevel grade = new ExamRequest.GradeLevel();
        grade.setId("5e909860b0fd8113ea1432b4");
        updateBody.setGradeLevel(grade);

        given()
                .spec(request)
                .body(updateBody)
                .when()
                .put(ExamEndpoints.UPDATE_EXAM)
                .then()
                .statusCode(200) // Kriter: 200 OK
                .body("name", containsString("Updated"))
                .body("id", equalTo(examId));
    }

    @Test(priority = 3, dependsOnMethods = "updateExam",  description = "Delete Exam - Expected: 200")
    public void deleteExam() {
        given()
                .spec(request)
                .pathParam("examId", examId)
                .when()
                .delete(ExamEndpoints.DELETE_EXAM)
                .then()
                // KRİTER: İsteğin başarılı olması durumunda, 200 status kodu döndürülmelidir.
                .statusCode(200);

        System.out.println("Exam delete request sent for ID: " + examId);
    }

    @Test(priority = 4, dependsOnMethods = "deleteExam", description = "Negative Delete - Expected: 404")
    public void deleteExamNegative() {
        // KRİTER: Geçersiz bir sınav kimliği {ID} ile silme yapmak isterse...
        // 404 status kodu döndürmelidir ve hata mesajı içermelidir.

        String invalidId = "invalidId12345"; // Kesinlikle geçersiz bir ID

        given()
                .spec(request)
                .pathParam("examId", invalidId)
                .when()
                .delete(ExamEndpoints.DELETE_EXAM)
                .then()
                .statusCode(404)
                .body("message", notNullValue());
    }
}