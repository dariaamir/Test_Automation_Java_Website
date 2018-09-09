Feature: Catalogue
   User is able to browse the catalogue

  Scenario Outline: Browse <category> and <subcategory>
    Given user is on homepage
    When user opens <category> as category page
    And user opens <subcategory> as subcategory page
    Then user is able to see <test_catalogue_item>

    Examples:
      |category |subcategory    |test_catalogue_item        |
      |Woman    |Tops           |Faded Short Sleeve T-shirts|
      |Dresses  |Casual Dresses |Printed Dress              |

  Scenario Outline: Hover on <category>
    Given user is on homepage
    When user hovers over the <category> menu item
    Then user is able to see <subcategory>

    Examples:
      |category |subcategory        |
      |Woman    |Blouses            |
      |Woman    |Evening Dresses    |


  Scenario Outline: Select <size>
    Given user is on <category> page
    When user opens selects <size>
    And user opens first item at the page
    Then <size> is availabe for purchase

    Examples:
      |category |size|
      |Dresses  |s   |
      |T-shirts |l   |