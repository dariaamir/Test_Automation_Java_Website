Feature: Login
  - User is able to open the Login Page
  - User is able to Login with correct credentials
  - User is not able to Login with wrong credentials

  Scenario: Open the Login page from the Home Page
    When user is on Home Page
    And user opens the Login Page link
    Then user is redirected to the Login page

  Scenario Outline: Login successful
    When user is on Login Page
    And user enters credentials
      |username|<username>|
      |password|<password>|
    Then success message is displayed
      |success message |<success_message>|
    And user is redirected to the account page

    Examples:
      |username                |password |success_message                                                                          |
      |zelenayakoshka@yandex.ru|Qwer1234!|Welcome to your account. Here you can manage all of your personal information and orders.|

  Scenario Outline: Login fail
    When user is on Login Page
    And user enters credentials
      |username|<username>|
      |password|<password>|
    Then error message is displayed
      |error message|<error_message>|

    Examples:
    |username                 | password | error_message        |
    |12334qwer                |          |Invalid email address.|
    |zelenayakoshka@yandex.ru |Qwer1235@ |Authentication failed.|
