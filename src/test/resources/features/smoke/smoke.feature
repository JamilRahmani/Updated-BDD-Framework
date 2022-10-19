Feature: Smoke Test

  @smoke
  Scenario: Login test scenarios
    Given login as csr to insurance app with username "supervisor" and password "password"
    Then assert application title "TEK Insurance Portal"