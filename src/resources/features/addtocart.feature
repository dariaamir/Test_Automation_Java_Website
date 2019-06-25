Feature: addToCart
  User is able to add item to cart
  - from the main page
  - from catalogue page

  Background:
    Given user is logged in

  Scenario: Add item to the cart from the main page
    When user is on Home Page
    And user click on the first add_to_cart button at the home page
    Then confirmation pop-up is displayed

  Scenario: Add item to the cart from item page
    When user is on item page
    And user clicks add to cart button at the item page
    Then confirmation pop-up is displayed