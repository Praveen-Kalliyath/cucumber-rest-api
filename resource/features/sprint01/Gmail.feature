@Regression
Feature: GMAIL

  Background: 
    Given User navigates to the gmail home page

  @Sanity
  Scenario: Validate Login Page Details
    Given I login using the credentials
    When I validate the login details
    And I close the browser

  @Sanity
  Scenario: Validate Log Out Functionality
    Given I login using the credentials
    When I validate home screen details
    Then I click on logout button
    And I validate user landed on the login page
    And I close the browser
