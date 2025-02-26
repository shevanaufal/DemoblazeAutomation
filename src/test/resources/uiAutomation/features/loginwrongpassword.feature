Feature: Login to Demoblaze
  @TDD
  Scenario Outline: User cannot login to Demoblaze when the password is invalid
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user will get <wrongpasswordmessage> as wrong password message

    Examples:
      | usernamelogin   | passwordlogin        | wrongpasswordmessage  |
      | shevanr         | wrongpassword        | Wrong password.       |