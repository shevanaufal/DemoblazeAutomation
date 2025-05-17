Feature: Create User
  @api
  Scenario: Successfully create a new user
    Given I have user details with first name "Sheva", last name "Naufal", and a unique email
    When I send a request to create the user
    Then the user should be created successfully
  @api
  Scenario: Attempt to create a user with an extremely long name
    Given I have user details with an excessively long first name
    When I send a request to create a user with a long name
    Then the response should indicate a bad request

