Feature: Sign Up to Demoblaze
  @web
  Scenario Outline: User an sign up to Demoblaze
    Given demoblaze homepage
    When user click sign up
    And user input "random" as username
    And user input "random" as password
    And user click sign up button
    Then will show success sign up <expectedmessage> as expected message

    Examples:
      | expectedmessage      |
      | Sign up successful.  |