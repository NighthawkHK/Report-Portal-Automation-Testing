package edu.reportportal.tests.api;

import edu.reportportal.entities.models.Project;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@Severity(SeverityLevel.CRITICAL)
public class ProjectApiTest extends BaseApiServiceTest {

    private static final String PROJECT_NAME = "default_personal";
    private static final String PROJECT_ENDPOINT = "/v1/project/list/{projectName}";

    @Test
    public void verifyProjectMandatoryInfo() {
        Project project = given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .when()
                .get(PROJECT_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().as(Project.class);

        Assert.assertEquals(project.getName(), PROJECT_NAME);
        Assert.assertEquals(project.getId(), 2);
        Assert.assertEquals(project.getEntryType(), "PERSONAL");
    }

    @Test
    public void verifyProjectContract() {
        given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .when()
                .get(PROJECT_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchema(new File("src/test/java/tests/data/jsons/project_schema.json")));
    }
}
