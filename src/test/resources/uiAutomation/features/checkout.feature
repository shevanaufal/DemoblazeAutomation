Feature: Checkout

  Scenario: User can checkout after adding products to cart
    Given demoblaze homepage
    When user add "Samsung galaxy s6" with ID "1" to the cart
    When user goes to the cart
    When user completes checkout with random details
    Then order should be successful
