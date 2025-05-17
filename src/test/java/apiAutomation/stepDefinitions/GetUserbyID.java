package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import apiAutomation.utility.SharedStorage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetUserbyID {

    private Response response;

    @Given("I have an existing user")
    public void ensureUserExists() {
        if (SharedStorage.createdUserId == null) {
            Response response = UserAPI.createUser("Sheva", "Naufal", "test" + System.currentTimeMillis() + "@example.com");
            SharedStorage.createdUserId = response.jsonPath().getString("id");
            System.out.println("DEBUG: Created User ID = " + SharedStorage.createdUserId);
        }
        assertNotNull(SharedStorage.createdUserId, "User ID should not be null");
    }

    @When("I request a user by ID")
    public void requestUserById() {
        assertNotNull(SharedStorage.createdUserId, "User ID should not be null before fetching");

        response = UserAPI.getUserById(SharedStorage.createdUserId);
        response.then().log().all().assertThat().statusCode(200);
    }

    @Then("the response should contain the user details")
    public void verifyUserDetails() {
        response.then().assertThat().body("id", Matchers.equalTo(SharedStorage.createdUserId));
    }

    @When("I request a user with an invalid ID")
    public void requestInvalidUser() {
        response = UserAPI.getUserById("invalid_id");
        response.then().log().all().assertThat().statusCode(400);
    }

    @Then("the response should indicate user not found")
    public void verifyUserNotFound() {
        Assertions.assertTrue(response.getBody().asString().contains("PARAMS_NOT_VALID"),
                "Response should contain error message 'PARAMS_NOT_VALID'");
    }
}
