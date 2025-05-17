package apiAutomation.stepDefinitions;

import apiAutomation.helper.UserAPI;
import apiAutomation.utility.SharedStorage;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class Hooks {

    @Before("@RequiresUser")
    public void ensureUserExists() {
        if (SharedStorage.createdUserId == null) {
            Response response = UserAPI.createUser("Sheva", "Naufal", "test" + System.currentTimeMillis() + "@example.com");
            SharedStorage.createdUserId = response.jsonPath().getString("id");
        }
    }
}
