@regressio @skydark 
Feature: Verify Timeline Feature

  Background:
    Given I am on home page of darksky

  @darksky-weeklydays1
  Scenario: Verify weekly forecast days are displayed correctly
    Then I verify days of the week is displayed in correct order

  @darksky-tempLowToHigh2
  Scenario: Verify low to high value is displayed correctly on weekly forecast section
    When  I click on todays bar
    Then I verify low and high temp displayed correctly on parent bar

  @darksky-timeMachine3
  Scenario: Select Tomorrow date from Time Machine
    When I click on Time Machine button
    And I click on Tomorrow date
    Then I verify that tomorrow date displayed on the details page

  @darksky-timeLine
  Scenario: verify timeline value are displayed correctly
    When I create a List of timeline time values
    Then I compare both list values and verify

  @darksky-days
  Scenario: Verify weekly days are displayed correctly
    When I create a List of website weekdays including today
    And I create a List of local weekdays including today
    Then I compare both list and verify that values are equal

  @darksky-temperature
  Scenario: Verify low to high value is displayed correctly on weekly forecast section
    When I create a List of minimum temperature value
    And I create a List of maximum temperature value
    Then I verify each maximum value is grater than it's minimum value
