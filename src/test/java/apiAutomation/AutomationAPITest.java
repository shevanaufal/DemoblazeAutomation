package apiAutomation;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


import java.io.File;

public class AutomationAPITest {
    private static final String BASE_URL = "https://dummyapi.io/data/v1";
    private static final String APP_ID = "63a804408eb0cb069b57e43a";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetUsers() {
        File jsonSchema = new File("src/test/resources/jsonSchema/getListUsersSchema.json");

        RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .get("/user")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data", Matchers.not(Matchers.empty()))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    public void testGetSingleUser() {
        String userId = "67bbc79c48ebc6af65ec2f95";

        RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .get("/user/" + userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", Matchers.equalTo(userId));
    }

    @Test
    public void testCreateUser() {
        String firstName = "Sheva";
        String lastName = "Naufal";
        String email = "sheva.naufal@example.com";

        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);

        Response response = RestAssured
                .given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/user/create")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(firstName))
                .extract().response();

        String createdUserId = response.jsonPath().getString("id");
        System.out.println("Created User ID: " + createdUserId);
    }

    @Test
    public void testUpdateUser() {
        String userId = "67bbc79c48ebc6af65ec2f95"; // Replace with an actual user ID
        String newFirstName = "Updated Sheva";

        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", newFirstName);

        RestAssured
                .given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .put("/user/" + userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(newFirstName));
    }

    @Test
    public void testDeleteUser() {
        String userId = "67bbc79c48ebc6af65ec2f95"; // Replace with an actual user ID

        RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .delete("/user/" + userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", Matchers.equalTo(userId));
    }

    @Test
    public void testUserNotFound() {
        String invalidUserId = "67bbc79c48ebc6af65ec2f95";

        Response response = RestAssured
                .given()
                .header("app-id", APP_ID)
                .when()
                .get("/user/" + invalidUserId)
                .then().log().all()
                .assertThat().statusCode(404)
                .extract().response();

        String responseBody = response.getBody().asString().trim();
        if (responseBody.isEmpty()) {
            throw new AssertionError("Test Failed");
        } else {
            System.out.println("Test Passed");
        }
    }

    @Test
    public void testEdgeCaseLongName() {
        String longFirstName = "Sheva".repeat(100);
        String requestBody = String.format("{\"firstName\": \"%s\", \"lastName\": \"Naufal\", \"email\": \"sheva@example.com\"}", longFirstName);

        Response response = RestAssured
                .given()
                .header("app-id", APP_ID)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/user/create")
                .then().extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            String firstName = response.jsonPath().getString("firstName");
            Assert.assertEquals(firstName.length(), 500, "First name length should match input length.");
        } else {
            Assert.assertEquals(statusCode, 400, "Unexpected response status.");
        }
    }
}