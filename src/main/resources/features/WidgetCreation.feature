Feature: As a user, I want to be able to create a widget

  Scenario: Add a widget
    Given I am logged in
    And I open a dashboard with name "DEMO DASHBOARD"
    When I add a new "Launch statistics chart" widget with name "WidgetAutotest"
    Then A widget with name "WidgetAutotest" should be displayed on the page