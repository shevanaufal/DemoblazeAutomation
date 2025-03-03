package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import apiAutomation.utility.SharedStorage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUser {

    private Response response;
    private String firstName, lastName, email;

    @Given("I have user details with first name {string}, last name {string}, and a unique email")
    public void generateUserDetails(String first, String last) {
        this.firstName = first;
        this.lastName = last;
        this.email = UUID.randomUUID() + "@example.com";
    }

    @When("I send a request to create the user")
    public void createUser() {
        response = UserAPI.createUser(firstName, lastName, email);
        response.then().assertThat().statusCode(200);

        //store created user by id
        SharedStorage.createdUserId = response.jsonPath().getString("id");

        Assertions.assertNotNull(SharedStorage.createdUserId, "User ID should not be null");
        System.out.println("DEBUG: Created User ID = " + SharedStorage.createdUserId);
    }

    @Then("the user should be created successfully")
    public void verifyUserCreated() {
        response.then().assertThat().body("firstName", Matchers.equalTo(firstName));
    }

    @Given("I have user details with an excessively long first name")
    public void generateLongName() {
        this.firstName = "Sheva".repeat(100);
        this.lastName = "Naufal";
        this.email = "longname@example.com";
    }

    @When("I send a request to create a user with a long name")
    public void createUserWithLongName() {
        response = UserAPI.createUserWithLongName(firstName, lastName, email);
    }

    @Then("the response should indicate a bad request")
    public void verifyBadRequest() {
        response.then().log().all();
        assertEquals(400, response.getStatusCode(), "Expected status code 400 for long name");
    }
}
