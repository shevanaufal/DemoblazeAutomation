Feature: Checkout
  @web
  Scenario: User can checkout after adding products to cart
    Given demoblaze homepage
    When user add "Samsung galaxy s6" with ID "1" to the cart
    And user goes to the cart
    And user completes checkout with random details
    And user click purchase button
    Then order should be successful
