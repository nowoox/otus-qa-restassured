import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


public class UserTests {

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Constants.BASE_URL)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    ResponseSpecification happyResponseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectStatusCode(HttpStatus.SC_OK)
            .log(LogDetail.ALL).build();

    ResponseSpecification unhappyResponseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();


    @Test
    void getUserByFailName() {

        String userName = User.generateName();

        given(requestSpecification)
        .when()
                .get(Constants.USER_ENDPOINT + "/" + userName)
        .then()
                .spec(unhappyResponseSpecification)
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("User not found"));

    }

    @Test
    void createUserWithNoData() {

        given(requestSpecification)
        .when()
                .post(Constants.USER_ENDPOINT)
        .then()
                .spec(unhappyResponseSpecification)
                .assertThat()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);

    }

    @Test
    void createUser() throws JSONException {

        String userName = User.generateName();

        given(requestSpecification)
                .body(User.generateUser(userName))
        .when()
                .post(Constants.USER_ENDPOINT)
        .then()
                .spec(happyResponseSpecification)
                .log().everything()
                .assertThat()
                .body("message", notNullValue());

        given(requestSpecification)
        .when()
                .get(Constants.USER_ENDPOINT + "/" + userName)
        .then()
                .spec(happyResponseSpecification)
                .assertThat()
                .body("username", equalTo(userName));

    }
}
