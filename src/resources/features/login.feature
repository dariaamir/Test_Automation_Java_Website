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


  Scenario: Login fail
    When user is on Login Page
    And user enters username and password
      |username                 |password  |
      |12334qwer                |          |
      |zelenayakoshka@yandex.ru |Qwer1235@ |
    Then error message is displayed
      |error message|
      |Invalid email address.|
      |Authentication failed.|
