@regression @calendar
Feature: Verify dates from date pickers

  Background:
    Given I am on hotels home page

  @hotel-TA7-1
  Scenario: Verify shopping cart functionality for hotels.com gift card
    And I find the Gift Card tab and click on it at home page
    And I switch to the new window, scroll down and click on Buy now button
    Then I verify Your Cart is empty. at the right panel of the gift card section
    When I insert Happy Birthday into message input field
    And I click on Birthday tab
    And I click on second birthday design
    And I select 250 as gift card value
    And I click on email tab for delivery option
    And I insert John into Recipient Name field
    And I insert test@email.com into Recipient email field
    And I insert Doe into Purchaser Name field
    And I click on calendar view icon
    And I pick 2-23-2019 from date picker for card delivery date
    And I scroll down and click on Add to cart button
    Then I verify the cart that item Qty is 1, card amount is dollar 250.00
    When I click on Remove at shopping cart
    And I click on Yes button at popup menu
    Then I also verify that item can be removed from cart and Your Cart is empty.


  @hotel-TA7-2
  Scenario: Verify group booking feature 
    When I click groups tab on home page
    And I insert chicago at Destination field
    And I clicked on Chicago, IL, USA from the list
    And I clicked Check-in Date and I add 3 days with today and select
    And I clicked Check-out Date and I add 2 days with Check-in Date and select
    And I insert 10 rooms needed per night
    And I select Holiday Party as Group type
    And I select 2-4 star for rating field
    And I adjust Ideal nightly budget on slider
    And I insert eerkr@sdgjf.hz as email and John Doe as full name
    And I clicked on (first) Continue button
    And I insert please ignore this request at additional request field
    And I uncheck newsletter check box and click on continue button again
    And I clicked on Continue button
    And I clicked on Next: View Hotels button
    Then I verify My Details displayed
    And I also verify hotels are displayed

  @hotel-TA7-3
  Scenario: Verify invalid credit card
    When I click Packages tab on home page
    And I click Things to Do tab on home page
    And I insert chicago at destination and select Chicago Heights, Illinois from list
    And I click search button on home page
    And I click Chicago Haunted Pub Tour on home page
    And I switch the driver to new window
    And I click Check Availability button
    And I click Book button
    And I click Continue booking button
    And I scroll down and insert invalid credit card number as 1234567890123456
    Then I verify the error message Please enter a valid card number.

  @hotel-TA7-4
  Scenario: Verify subscription and unsubscribe function of hotels.com newsletter
    When I click Help tab on home page
    And I switch the the webdriver to new help window
    And I expand Your Settings at the left pannel and click on Newsletter subscription
    And I click signUp now link
    And I provide an email address in email field
    And I provide the same emil address in confirm email
    And I provide first name as John and last name as Doe
    And I click the check box of terms and condition
    And I click Register button
    Then I verify you subscribe successfully


  @hotel-TA7-5
  Scenario: verify Sign in security for suspected SignIn attempt with valid email and password
    When I click Sign in & Account tab on home page
    And I click Sign in Option
    And I provide my email address
    And I provide my password
    And I click Sign in button
    Then I verify the message Please enter the characters that appear in the image below which confirm human activity



#  @hotel-search
#  Scenario: Verify user is able to successfully search for hotels
#    When I click on Destination on home page
#    And I insert Dallas into Destination field on home page
#    And I click on Dallas, Texas, United States of America from auto suggestion
#    And I click on Check in on home page
#    And I select tomorrow date from date picker
#    And I click on Check out on home page
#    And I add 6 days with current date and click on that date
#    Then I verify 6 nights have been selected
#    When I click on occupancy drop down menu and select More options…
#    And I click on rooms drop down menu and select 1
#    And I click on adult drop down menu and select 2
#    And I click on children drop down menu and select 2
#    And I click on child1 drop down menu and select <1
#    And I click on child2 drop down menu and select 3
#    And I click on search button on home page
#    Then I verify city info Dallas, Texas, United States of America display correctly
#    And I verify 6 day range displayed correctly on result page
#    And I also verify 6 nights, 1 room, 2 adults, 2 children have been selected
#
#  @hotels
#  Scenario: pick dates from calendar
#    When I click on Check in on home page
#    And I select current date from date picker
#    And I click on Check out on home page
#    And I select 2-23-2019 date from date picker
#    Then I verify number of nights displayed correctly for the date 2-23-2019