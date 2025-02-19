Feature: Sign Up to Demoblaze
  @TDD
  Scenario Outline: User an sign upto Demoblaze
    Given demoblaze homepage
    When user click sign up
    And user input <username> as username
    And user input <password> as password
    And user click sign up button
    Then will show success sign up <expectedmessage> as expected message

    Examples:
      | username       | password      | expectedmessage      |
      | snr_user1      | secret_snr    | Sign up successful.  |