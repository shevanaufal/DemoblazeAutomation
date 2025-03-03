package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetUsers {

    private Response response;

    @When("I request all users")
    public void requestAllUsers() {
        response = UserAPI.getUsers();
        response.then().log().all().assertThat().statusCode(200);
    }

    @Then("the response should contain a list of users")
    public void verifyUsersList() {
        assertFalse(response.getBody().asString().isEmpty(), "Response should contain user data");
        response.then().assertThat().body("data", Matchers.not(Matchers.empty()));
    }
}
