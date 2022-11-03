package edu.reportportal.tests.api;

import edu.reportportal.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiServiceTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.port = Integer.parseInt(PropertyReader.getProperty("serverPort"));
        RestAssured.baseURI = PropertyReader.getProperty("baseURI");
        determineLog();
        setAuthenticationToken();
        RestAssured.basePath = PropertyReader.getProperty("basePath");

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    private void setAuthenticationToken() {
        Response response = RestAssured.given()
                .formParam("grant_type", "password")
                .formParam("username", "default")
                .formParam("password", "1q2w3e")
                .auth()
                .basic("ui", "uiman")
                .when()
                .post("/uat/sso/oauth/token")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        String uiToken = parseAccessToken(response);

        response = RestAssured.given()
                .auth()
                .oauth2(uiToken)
                .when()
                .get("uat/sso/me/apitoken")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        String apiToken = parseAccessToken(response);

        PreemptiveOAuth2HeaderScheme authenticationScheme = new PreemptiveOAuth2HeaderScheme();
        authenticationScheme.setAccessToken(apiToken);
        RestAssured.authentication = authenticationScheme;
    }

    private void determineLog() {
        boolean logAll = Boolean.parseBoolean(PropertyReader.getProperty("logAll"));
        if (logAll)
            RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
        else
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private String parseAccessToken(final Response response) {
        return response.jsonPath().getString("access_token");
    }
}
