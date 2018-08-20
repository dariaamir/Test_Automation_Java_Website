Feature: Login Scenario
  Verify if user is able to Login with correct username and password

  Scenario Outline: Login with <username> and <password>
    Given user is on homepage
    When user navigates to Login Page
    And user enters <username> as username and <password> as password
    Then <message> as success message is displayed
    And user is redirected to the account page

  Examples:
    |username|password|message|
    |blog.cucumber@gmail.com|Cucumber@blog|Welcome to your account. Here you can manage all of your personal information and orders.|
