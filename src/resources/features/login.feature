Feature: Login
  - User is able to open the Login Page
  - User is able to Login with correct username and password
  - User is not able to Login with wrong username or password

  Scenario: Open the Login page from the Home Page
    When user is on Home Page
    And user opens the Login Page link
    Then user is redirected to the Login page

  Scenario: Login successful
    When user is on Login Page
    And user enters username and password
      |username                |password |
      |zelenayakoshka@yandex.ru|Qwer1234!|
    Then success message is displayed
      |message                                                                                  |
      |Welcome to your account. Here you can manage all of your personal information and orders.|
    And user is redirected to the account page


  Scenario Outline: Login fail
    When user is on Login Page
    And user enters credentials
      |username                 |password  |
      |<username>|<password>          |
    Then error message is displayed
      |error message|
      |<error_message>|

    Examples:
    |username                 | password | error_message        |
    |12334qwer                |          |Invalid email address.|
    |zelenayakoshka@yandex.ru |Qwer1235@ |Authentication failed.|


    Scenario: asdas
      Given logged it
      And on page X
      When I enter stuff in field X

      When I enter stuff in field X1
      When I enter stuff in field X2
      When I enter stuff in field X













      When I enter stuff in field X