Feature: addToCart
  User is able to add item to cart
  - from the main page
  - from quick view
  - from catalogue page

  Background:
    Given user is logged in

  Scenario: Add item to the cart from the main page
    When user is on Home Page
    And user click on the first add_to_cart button
    Then confirmation pop-up is displayed

  Scenario Outline: Add item to the cart from quick view
    Given user is on Home Page
    When
    Then


    Examples:
      | | |
      | | |

  Scenario Outline: Add item to the cart from catalogue page
    Given user is on Home Page
    When
    Then


    Examples:
      | | |
      | | |