Feature: Update User
  @api
  Scenario: Successfully update an existing user
    Given I have user details with first name "Sheva", last name "Naufal", and a unique email
    When I update the user's first name to "Updated Sheva"
    Then the user's details should be updated successfully
