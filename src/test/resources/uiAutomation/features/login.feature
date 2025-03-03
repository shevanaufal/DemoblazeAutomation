Feature: Login to Demoblaze
  @web
  Scenario Outline: User can login to Demoblaze
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user can see welcome with username

    Examples:
      | usernamelogin   | passwordlogin      |
      | shevanr         | *Demoblaze99       |