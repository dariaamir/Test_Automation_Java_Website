Feature: WishList
  - There is a wishlist link at the user page
  - User is able to add item to wishlist
  - User is able to see items in the wishlist
  - User is able to delete item from the wishlist

  Background:
    Given user is logged in

  Scenario: Wishlist link at the user page
    When user is on My Account page
    And user clicks my_wishlists link
    Then my_wishlist page is opened

  Scenario: Add item to the wishlist
    When user is on item page
    And user clicks add_to_wlishlist link
    Then wishlist confirmation pop-up is displayed

  Scenario: See item in the wishlist
    When user is on my_wishlist page
    And user clicks wishlist title
    Then item is displayed in the list

  Scenario: Delete item from the wishlist
    When user is on my_wishlist page
    And user clicks wishlist title
    And user clicks delete button
    Then item is not displayed in the list