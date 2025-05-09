Feature: To verify the update user API

  Scenario: Verify the add user API
    Given Update the user create data
    When User call "AddUser" with "POST" http request
    Then Get the created user id
    When User call "Putuserdata" with "PUT" http request for record update
    And Verify that the updated name, email, gender, status

#  Scenario: Verify the add user
#    Given Update the user create data
#    When User call "AddUser" with "POST" http request
#    Then Get the created user id
#    When User call "Putuserdata" with "PUT" http request for record update
#    And Verify that the updated name, email, gender, status
