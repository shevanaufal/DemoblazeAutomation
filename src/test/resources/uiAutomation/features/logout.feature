Feature: Logout from Demoblaze
  @web
  Scenario Outline: user can logout from Demoblaze
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user can see welcome with username
    And user click logout
    Then user successfully logout

    Examples:
      | usernamelogin   | passwordlogin      |
      | shevanr         | *Demoblaze99       |