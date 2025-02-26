package apiAutomation.helper;

import apiAutomation.config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class UserAPI {

    static {
        RestAssured.baseURI = "https://dummyapi.io/data/v1";
    }

    public static Response getUsers() {
        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .when()
                .get("/user");
    }

    public static Response getUserById(String userId) {
        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .when()
                .get("/user/" + userId);
    }

    public static Response createUser(String firstName, String lastName, String email) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);

        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/user/create");
    }

    public static Response updateUser(String userId, String newFirstName) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", newFirstName);

        return RestAssured.given()
                .header("app-id", Config.APP_ID) //Call Config App ID
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .put("/user/" + userId);
    }

    public static Response deleteUser(String userId) {
        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .when()
                .delete("/user/" + userId);
    }

    public static Response createUserWithLongName(String longFirstName, String lastName, String email) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstName", longFirstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);

        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/user/create");
    }

    public static Response getTags() {
        return RestAssured.given()
                .header("app-id", Config.APP_ID)
                .when()
                .get("/tag");
    }

}
