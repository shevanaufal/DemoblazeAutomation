Feature: Add to Cart
  @web
  Scenario Outline: User can adds an item to the cart
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user can see welcome with username
    When user add "<productName>" with ID "<productId>" to the cart
    Then user should see <productName> in the cart

    Examples:
      | usernamelogin   | passwordlogin      | productName  | productId |
      | shevanr         | *Demoblaze99       | Samsung galaxy s6       | 1   |
      | shevanr         | *Demoblaze99       | Nokia lumia 1520        | 2   |
      | shevanr         | *Demoblaze99       | Nexus 6                 | 3   |
