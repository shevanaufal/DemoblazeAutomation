package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import apiAutomation.utility.SharedStorage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateUser {

    private Response response;

    @When("I update the user’s first name to {string}")
    public void updateUser(String newFirstName) {
        assertNotNull(SharedStorage.createdUserId, "User ID should not be null before updating");

        //ensure created userid included
        response = UserAPI.updateUser(SharedStorage.createdUserId, newFirstName, "Naufal", "updated@example.com");

        //debug
        response.then().log().all();
    }

    @Then("the user's details should be updated successfully")
    public void verifyUserUpdated() {
        response.then().assertThat().statusCode(200);
    }
}
