Feature: Send Message via Contact Modal
  @TDD
  Scenario Outline:
    Given demoblaze homepage
    When user click contact
    And user input "random" as contact email
    And user input "random" as contact name
    And user input "random" contact message
    And user click send message
    Then will show success send message <sendMessageSuccess> as success message

    Examples:
    | sendMessageSuccess        |
    | Thanks for the message!!  |