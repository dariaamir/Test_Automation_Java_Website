Feature: Positive Login Scenario
  Verify if user is able to Login with correct login and password

  Scenario: Login as a authenticated user
    Given user is on homepage
    When user navigates to Login Page
    And user enters username and password
    Then success message is displayed