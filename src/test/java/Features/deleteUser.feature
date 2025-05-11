Feature: To verify the user delete API functionality
  @DeleteUser @regression
  Scenario: Verify the add user API
    Given Update the user create data
    When User call "AddUser" with "POST" http request
    Then Then The response status code should be 201 Get the created user id
  @DeleteUser @regression
  Scenario: Verify the delete user API
  When User calls "DeleteUser" with "DELETE" http request and verify the status code 204

