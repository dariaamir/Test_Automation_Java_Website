Feature: Login
  - User is able to open the Login Page
  - User is able to Login with correct username and password
  - User is not able to Login with wrong username or password

  Scenario: Open the Login page from the Home Page
    When user is on Home Page
    And user opens the Login Page link
    Then user is redirected to the Login page

  Scenario Outline: Login with <username> and <password>
    When user is on Login Page
    And user enters <username> as username and <password> as password
    Then <message> as success message is displayed
    And user is redirected to the account page

    Examples:
      |username|password|message|
      |zelenayakoshka@yandex.ru|Qwer1234!|Welcome to your account. Here you can manage all of your personal information and orders.|

  Scenario Outline: Login with <username> and <password>
    When user is on Login Page
    And user enters <username> as username and <password> as password
    Then <error_message> as login page error message is displayed

    Examples:
      |username                 |password  |error_message|
      |12334qwer                |          |Invalid email address.|
      |zelenayakoshka@yandex.ru |Qwer1235@ |Authentication failed.|