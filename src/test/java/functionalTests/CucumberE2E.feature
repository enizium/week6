Feature: Employee E2E
  Description: Employee tes

URL = http://localhost:8080

  Background: User generates token for Authorization
    Given I am an authorized user



  Scenario: Get all employee
    Given Get all employee
    When Admin request to get all Employee details
    Then Check employee is added