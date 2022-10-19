Feature: Smoke Test

  @test
  Scenario: Login test scenarios this is test scenario
    Given create database connection
    When execute query "SELECT username FROM user where account_type = 'CSR' Limit 1;"
    And print result
    And Login as CSR with db result
    Then assert application title "TEK Insurance Portal"