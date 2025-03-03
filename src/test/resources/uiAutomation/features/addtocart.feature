Feature: Add to Cart
  @web
  Scenario Outline: User can adds an item to the cart
    Given demoblaze homepage
    When user add "<productName>" with ID "<productId>" to the cart
    Then user should see <productName> in the cart

    Examples:
      | productName             | productId  |
      | Samsung galaxy s6       | 1   |
      | Nokia lumia 1520        | 2   |
      | Nexus 6                 | 3   |
