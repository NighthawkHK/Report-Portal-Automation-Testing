package tests.api;

import entities.models.Project;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Severity(SeverityLevel.CRITICAL)
public class ProjectApiTest extends BaseApiServiceTest {

    private static final String PROJECT_NAME = "default_personal";

    @Test
    public void verifyProjectMandatoryInfo() {
        Project project = given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .when()
                .get("/v1/project/list/{projectName}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);

        Assert.assertEquals(project.getName(), PROJECT_NAME);
        Assert.assertEquals(project.getId(), 2);
        Assert.assertEquals(project.getEntryType(), "PERSONAL");
    }
}
