package tests.api;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Severity(SeverityLevel.CRITICAL)
public class DashboardApiTest extends BaseApiServiceTest {

    private JSONObject requestParams;
    private int dashboardId;
    private static final String PROJECT_NAME = "default_personal";

    @BeforeClass
    public void createDashboard() {
        requestParams = new JSONObject();
        requestParams.put("description", "Test Project");
        requestParams.put("name", "Test");
        requestParams.put("shared", false);

        dashboardId = given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .when()
                .body(requestParams.toString())
                .post("/v1/{projectName}/dashboard")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .body("id", notNullValue())
                .extract().jsonPath().getInt("id");
    }

    @Test
    public void verifyDashboardModification() {
        requestParams = new JSONObject();
        requestParams.put("description", "New test project description");
        requestParams.put("name", "Test renamed");
        requestParams.put("shared", true);
        requestParams.put("updatedWidgets", new ArrayList<>());

        given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .pathParam("dashboardId", dashboardId)
                .body(requestParams.toString())
                .when()
                .put("/v1/{projectName}/dashboard/{dashboardId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @AfterClass
    public void deleteDashboard() {
        given().spec(requestSpec)
                .pathParam("projectName", PROJECT_NAME)
                .pathParam("dashboardId", dashboardId)
                .body(requestParams.toString())
                .when()
                .delete("/v1/{projectName}/dashboard/{dashboardId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }
}
