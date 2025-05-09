Feature: Validating place Api

  Scenario: Verify the add user API
    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user is created


    Given Add user payload
    When User call "AddUser" with "POST" http request
    And Verify user ID is present or not


    Given Add user payload with missing name
    When User call "AddUser" with "POST" http request
    And Verify Name is missing or empty!



    Given Add user payload with the missing email
    When User call "AddUser" with "POST" http request
    And It should show the Email is missing or empty! message


    Given Add user payload with the missing Gender
    When User call "AddUser" with "POST" http request
    And It should show the Gender is missing or empty! message


    Given Add user payload with the missing Status
    When User call "AddUser" with "POST" http request
    And It should show the Status is missing or empty! message


    Given Add user payload with same email
    When User call "AddUser" with "POST" http request
    And It should show the email already taken