Feature: Get User by ID
  @api @RequiresUser
  Scenario: Retrieve a valid user
    Given I have an existing user
    When I request a user by ID
    Then the response should contain the user details

  #Negative Case
  @api
  Scenario: Request a non-existent user
    When I request a user with an invalid ID
    Then the response should indicate user not found
