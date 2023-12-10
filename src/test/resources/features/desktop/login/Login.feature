@voila @desktop @login
Feature: Sample Feature

  @sample.login.1
  Scenario: Verify user able to login with a valid username and password (1)
    Given user visit voila homepage
    And user click "Sign In" button on sticky navbar
    And user fill username "irsyad@machtwatch.co.id" on login page
    And user fill password "voila-UF5" on login page
    When user click sign in button on login page
    Then user will directed to voila homepage with "voila-web.machtwatch.net/women"
    And user verify "not see" sign in button on sticky navbar

  @sample.login.2
  Scenario: Verify user able to login with a valid username and password (2)
    Given user visit voila homepage
    And user click "Sign In" button on sticky navbar
    And user fill username "irsyad@machtwatch.co.id" on login page
    And user fill password "voila-UF5" on login page
    When user click sign in button on login page
    Then user will directed to voila homepage with "voila-web.machtwatch.net/women"
    And user verify "not see" sign in button on sticky navbar
