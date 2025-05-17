Feature: Send Message via Contact Modal
  @web
  Scenario Outline:
    Given demoblaze homepage
    When user click log in
    And user input <usernamelogin> as usernameLogin
    And user input <passwordlogin> as passwordLogin
    And user click log in button
    Then user can see welcome with username
    When user click contact
    And user input "random" as contact email
    And user input "random" as contact name
    And user input "random" contact message
    And user click send message
    Then will show success send message <sendMessageSuccess> as success message

    Examples:
    | usernamelogin | passwordlogin     | sendMessageSuccess        |
    |  shevanr      | *Demoblaze99      | Thanks for the message!!  |