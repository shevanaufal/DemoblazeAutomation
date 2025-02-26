package apiAutomation.tests;

import apiAutomation.helper.UserAPI;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class AutomationTest {

    private static String createdUserId;

    @BeforeAll
    static void setup() {
        // Setup URL already put in helper
    }

    @BeforeEach
    void createUserForTests() {
        // Create a user before each test that requires it
        String firstName = "Sheva";
        String lastName = "Naufal";
        String email = UUID.randomUUID() + "@example.com"; // Unique email

        Response response = UserAPI.createUser(firstName, lastName, email)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(firstName))
                .extract().response();

        // Extract and store the user ID
        createdUserId = response.jsonPath().getString("id");

        System.out.println("DEBUG: Created User ID = " + createdUserId);
        Assertions.assertNotNull(createdUserId, "User ID should not be null");
    }

    @Test
    public void testGetUsers() {
        UserAPI.getUsers()
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data", Matchers.not(Matchers.empty()));
    }

    @Test
    public void testGetSingleUser() {
        String userId = "60d0fe4f5311236168a109cc";

        UserAPI.getUserById(userId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", Matchers.equalTo(userId));
    }

    @Test
    public void testUpdateUser() {
        System.out.println("DEBUG: User ID Before Update = " + createdUserId);
        Assertions.assertNotNull(createdUserId, "User ID should not be null before update");

        String newFirstName = "Updated Sheva";
        Response response = UserAPI.updateUser(createdUserId, newFirstName);

        System.out.println("DEBUG: API Response = " + response.getBody().asString());

        response.then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("firstName", Matchers.equalTo(newFirstName));
    }

    @Test
    public void testDeleteUser() {
        System.out.println("DEBUG: User ID Before Delete = " + createdUserId);
        Assertions.assertNotNull(createdUserId, "User ID should not be null before delete");

        UserAPI.deleteUser(createdUserId)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id", Matchers.equalTo(createdUserId));
    }

    @Test
    public void testUserNotFound() {
        String invalidUserId = "67bf04f6eb1fe7b43177df81";

        Response response = UserAPI.getUserById(invalidUserId)
                .then().log().all()
                .assertThat().statusCode(404)
                .extract().response();

        Assertions.assertFalse(response.getBody().asString().isEmpty(), "Response body should not be empty");
    }

    @Test
    public void testEdgeCaseLongName() {
        String longFirstName = "Sheva".repeat(100);
        String lastName = "Naufal";
        String email = "sheva@example.com";

        Response response = UserAPI.createUserWithLongName(longFirstName, lastName, email)
                .then().log().all()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(400, statusCode, "Expected status code 400 for long name edge case");
    }

    @Test
    public void testGetTags() {
        Response response = UserAPI.getTags()
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("data", Matchers.not(Matchers.empty()))
                .extract().response();

        // Print the tags
        System.out.println("Tags: " + response.jsonPath().getList("data"));
    }
}