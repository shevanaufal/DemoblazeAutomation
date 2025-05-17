#Feature: End-to-End Test for User Journey
#  @web
#  Scenario Outline: User completes a end-to-end journey from login-checkout-logout
#    Given demoblaze homepage
#    When user click log in
#    And user input <usernamelogin> as usernameLogin
#    And user input <passwordlogin> as passwordLogin
#    And user click log in button
#    Then user can see welcome with username
#    And user goes to the cart
#    And user completes checkout with random details
#    Then order should be successful
#    And user click logout
#    Then user successfully logout
#
#    Examples:
#      | usernamelogin   | passwordlogin      |
#      | shevanr         | *Demoblaze99       |
#
