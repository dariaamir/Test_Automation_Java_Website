Feature: Negative Login Scenario
  Verify if user not able to Login with incorrect login or password

  Scenario: Fail login with wrong email
    Given user is on homepage
    When user navigates to Login Page
    And user enters wrong email
    Then wrong email message is displayed
    And user navigates to Login Page
    And user enters wrong password
    Then wrong password message is displayed