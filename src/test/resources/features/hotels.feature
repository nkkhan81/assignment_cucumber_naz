@regression @calendar
Feature: Verify dates from date pickers

  Background:
    Given I am on hotels home page

  @hotel-search
  Scenario: Verify user is able to successfully search for hotels
    When I click on Destination field on home page
    And I insert Dallas into Destination field on home page
    And I click on Dallas, Texas, United States of America from auto suggestion
    And I click on Check in field on home page
    And I select tomorrow date from date picker
    And I click on Check out field on home page
    And I add 6 days with current date and click on that date
    Then I verify 6 nights have been selected
    When I click on occupancy drop down menu and select More options…
    And I click on rooms drop down menu and select 1
    And I click on adult drop down menu and select 2
    And I click on children drop down menu and select 2
    And I click on child1 drop down menu and select <1
    And I click on child2 drop down menu and select 3
    And I click on search button field on home page
    Then I verify city info Dallas, Texas, United States of America display correctly
    And I verify 6 day range displayed correctly on result page
    And I also verify 6 nights, 1 room, 2 adults, 2 children have been selected

  @hotels
  Scenario: pick dates from calendar
    When I click on Check in field on home page
    And I select current date from date picker
    And I click on Check out field on home page
    And I select 2-23-2019 date from date picker
    Then I verify number of nights displayed correctly for the date 2-23-2019