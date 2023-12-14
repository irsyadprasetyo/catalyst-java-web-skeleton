@voila @desktop @login.feature
Feature: Sample Feature 1

  Background:
    Given desktop user visit voila homepage
    And user click "Sign In" button on sticky navbar
    And user fill username "irsyad@machtwatch.co.id" on login page
    And user fill password "voila-UF5" on login page
    And user click sign in button on login page
    And user see voila loader disappear
    And user will directed to "voila-web.machtwatch.net"

  @sample.login
  Scenario: Verify user able to login with a valid username and password (1)
    Then user verify "not see" sign in button on sticky navbar

  @sample.database.check
  Scenario: Verify user can visit profile and match profile data match with db data
    And user access database xms ms_customer with email "irsyad@machtwatch.co.id" to get data
      | listOfData |
      | name       |
      | email      |
    And user hover profile on sticky navbar
    When user click "My Profile" on dropdown account
    Then user verify is on my profile page
    And user will directed to "voila-web.machtwatch.net/account/my-profile"
    And user will see my profile data match with ms_customer database:
      | compare |
      | name    |
      | email   |