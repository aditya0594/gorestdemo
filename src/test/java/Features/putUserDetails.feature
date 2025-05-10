Feature: To verify the update user API
 @UpdateUser
  Scenario: Verify the add user API
    Given Update the user create data
    When User call "AddUser" with "POST" http request
    Then Then The response status code should be 201 Get the created user id
    When User call "Putuserdata" with "PUT" http request for record update
    And Verify that the updated name, email, gender, status
 @UpdateUser
  Scenario: Update user with blank email
    When User calls "Putuserdata" with "PUT" http request using missing "email"
    Then The error message "email" should contain "can't be blank"
 @UpdateUser
  Scenario: Update user with missing name
    When User calls "Putuserdata" with "PUT" http request using missing "name"
    Then The error message "name" should contain "can't be blank"
 @UpdateUser
  Scenario: Update user with missing gender
    When User calls "Putuserdata" with "PUT" http request using missing "gender"
    Then The error message "gender" should contain "can't be blank, can be male of female"
 @UpdateUser
  Scenario: Update user with missing status
    When User calls "Putuserdata" with "PUT" http request using missing "status"
    Then The error message "status" should contain "can't be blank"
  @UpdateUser
  Scenario: Update user with missing email
    When User calls "Putuserdata" with "PUT" http request using missing "email" field
    Then It should update the user with the provided data
  @UpdateUser
  Scenario: Update user with missing name
    When User calls "Putuserdata" with "PUT" http request using missing "name" field
    Then It should update the user with the provided data
  @UpdateUser
  Scenario: Update user with missing gender
    When User calls "Putuserdata" with "PUT" http request using missing "gender" field
    Then It should update the user with the provided data
  @UpdateUser
  Scenario: Update user with missing status
    When User calls "Putuserdata" with "PUT" http request using missing "status" field
    Then It should update the user with the provided data