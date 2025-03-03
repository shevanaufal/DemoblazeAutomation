Feature: Delete User
  @api @RequiresUser
  Scenario: Successfully delete an existing user
    Given I have an existing user
    When I delete the user
    Then the user should be deleted successfully
