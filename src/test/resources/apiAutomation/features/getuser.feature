Feature: Get Users
  @api
  Scenario: Retrieve all users
    When I request all users
    Then the response should contain a list of users
