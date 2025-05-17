Feature: Checkout
  @web
  Scenario Outline: User can checkout after adding products to cart
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user can see welcome with username
    When user add "Samsung galaxy s6" with ID "1" to the cart
    And user goes to the cart
    And user completes checkout with random details
    And user click purchase button
    Then order should be successful
    And user confirm modal success purchase


    Examples:
      | usernamelogin   | passwordlogin      |
      | shevanr         | *Demoblaze99       |
