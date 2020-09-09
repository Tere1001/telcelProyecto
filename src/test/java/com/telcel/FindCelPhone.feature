Feature: Find Cell Phone

  Scenario: Search page Telcel by state
    Given I am on telcel Page
    When I search cell phones by state
    Then It displays cell phone by state

  Scenario: Search the phone information with correction
    Given I am on Cell phone page
    When I store the first cell phone info
    And I click on this cell phone
    Then It displays the phone information








