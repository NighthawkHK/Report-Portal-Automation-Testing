Feature: As a user, I want to be able to filter launches by a certain column

  Scenario: Verify column visibility
    Given I am logged in
    When I open debug tab
    Then Columns with names are present on the page
      | Name | Start time | Total | Passed | Failed | Skipped | Product bug | Auto bug | System issue | To investigate |