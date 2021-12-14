Feature:

  Background:
    Given Login Page is loaded

  Scenario Outline: Add products to the cart
    When User input "<login>" and "<password>"
    And User go to page "<firstLevel>" -> "<secondLevel>"
    And User select product with number "<number>"
    And User click ""
    Then Product is added to the cart

    Examples:
      | login                                   | password | firstLevel | secondLevel    | number |
      | autosupertravel+1117140805004@yandex.ru | 12345    | Women      | Summer Dresses | 1      |
      | autosupertravel+1117140805004@yandex.ru | 12345    | Women      | T-shirts       | 0      |


