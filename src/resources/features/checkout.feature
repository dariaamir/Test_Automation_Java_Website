Feature: Checkout
  User is able to purchase item

  Scenario: Add item to the cart
    Given user is on item page
    When user clicks add_to_cart button at the item page
    And user clicks proceed_to_checkout
    Then user is redirected to the checkout page
    And item is displayed at the checkout page

  Scenario: 1st checkout page (Summary)
    Given user is on 1st checkout page
    When user clicks proceed_to_checkout
    Then user is redirected to 2nd checkout page

  Scenario: 2nd checkout page (Sign in)
    Given user is on 2nd checkout page
    When user is logged in
    Then user is redirected to 3rd checkout page

  Scenario: 3rd checkout page (Address)
    Given user is on 3rd checkout page
    When user clicks proceed_to_checkout
    Then user is redirected to 4th checkout page

  Scenario: 4th checkout page (Shipping)
    Given user is on 4th checkout page
    When user agrees the terms of service
    And user clicks proceed_to_checkout
    Then user is redirected to 5th checkout page

  Scenario: 5th checkout page (Shipping)
    Given user is on 5th checkout page
    When user selects payment by whire
    And user clicks confirmation button
    Then confirmation message is displayed