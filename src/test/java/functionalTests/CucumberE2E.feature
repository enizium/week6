Feature: Employee E2E
  Description: Employee test

URL = http://localhost:8080

  Background: User generates token for Authorization
    Given I am an authorized user

  Scenario: Get all employee
    Given Get all employee
    When Adding employee
    Then Check employee is added