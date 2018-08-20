Feature: Login Scenario Negative Cases
  Verify if user is not able to Login with wrong username or password

  Scenario Outline: Login with <username> and <password>
    Given user is on homepage
    When user navigates to Login Page
    And user enters <username> as username and <password> as password
    Then <error_message> as error message is displayed

  Examples:
    |username                 |password       |error_message|
    |12334qwer                |Cucumber@blog  |Invalid email address.|
    |blog.cucumber@gmail.com  |tutumber@blog  |Authentication failed.|