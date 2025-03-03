package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import apiAutomation.utility.SharedStorage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteUser {

    private Response response;

    @When("I delete the user")
    public void deleteUser() {
        assertNotNull(SharedStorage.createdUserId, "User ID should not be null before deleting");

        response = UserAPI.deleteUser(SharedStorage.createdUserId);
        response.then().log().all();
    }

    @Then("the user should be deleted successfully")
    public void verifyUserDeleted() {
        response.then().assertThat().statusCode(200);
        SharedStorage.createdUserId = null;
    }
}
