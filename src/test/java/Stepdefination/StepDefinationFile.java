package Stepdefination;

import Resources.APIResources;
import Resources.TestDataFields;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.User;
import pojo.UserResponse;
import pojo.VerifyOTP;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StepDefinationFile extends Utils {
    static ResponseSpecification Responsespec;
    static String place_id;
    static String response;
    static String response1;
    RequestSpecification res;
    JsonPath js;
    TestDataFields data = new TestDataFields();
    StepDefinationSuperadmin admin = new StepDefinationSuperadmin();
    String email;
    User user;
    int ID;
    UserResponse userResponse = new UserResponse();

    public StepDefinationFile() {
        // Default constructor required for Cucumber
    }

    //    @Given("Add Place payload {string} {string}")
//    public void add_place_payload(String name, String address) throws IOException {
//            //Response SpecBuilder
//            Responsespec = new ResponseSpecBuilder()
//                    .expectStatusCode(200)
//                    .expectContentType(ContentType.JSON).build();
//
//        res =  given().spec(requestspecification()).queryParams("key", "qaclick123").body(data.addplacePayload(name,address));
//
//    }
    @When("User call {string} with {string} http request")
    public String user_call_with_http_request(String resource, String method) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println("Resoures API : " + resourceAPI.getResource());
        if (method.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getResource())
                    .then().spec(Responsespec).extract().response().asString();
            System.out.println(response);
        } else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getResource())
                    .then().spec(Responsespec).extract().response().asString();
            System.out.println(response);
        }else if (method.equalsIgnoreCase("PUT")) {
            response = res.when().put(resourceAPI.getResource())
                    .then().spec(Responsespec).extract().response().asString();
            System.out.println(response);
        }else {
            System.out.println("Invalid http Method");
        }


        return response;
    }

    //    @Then("Api call {string} is success and status code {int} OK")
//    public void api_call_is_success_and_status_code_ok(String resource, Integer statusCode) {
//        // Write code here that turns the phrase above into concrete actions
//        APIResources resourceAPI = APIResources.valueOf(resource);
//        int actualStatusCode = res.when().log().all().post(resourceAPI.getResource()).getStatusCode();
//        assertEquals(statusCode.intValue(), actualStatusCode);
//    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyvalue, String expectedValue) {

        js = new JsonPath(response);
        place_id = js.get("place_id");
        String actualValue = js.getString(keyvalue);
        System.out.println("Value for " + keyvalue + ": " + actualValue);
        assertEquals(expectedValue, actualValue);


    }

    @Given("Add Place payload {string} {string}")
    public void add_place_payload(String string, String string2) throws IOException {
        Responsespec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType("text/plain").build();
        res = given().spec(requestspecification("https://rahulshettyacademy.com/"))
                .body("{\n" +
                        "\"name\":\"Learn Appium Automation with Java\",\n" +
                        "\"isbn\":\"bcwee21d\",\n" +
                        "\"aisle\":\"2954426\",\n" +
                        "\"author\":\"John foer\"\n" +
                        "}\n");
    }

    @Given("Add user payload")
    public void add_user_payload() throws IOException {
        this.email = Utils.generateRandomEmail();
        user = new User("Aditya Pawar", email, "Male", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(201).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("Verify user is created")
    public void verify_user_is_created() {
        js = new JsonPath(response);
        String status = js.get("status");
        Assert.assertNotNull(status, "Status field is missing in the response");
        Assert.assertEquals(status, "active", "User status is not active");
    }

    @When("Verify user ID is present or not")
    public void verify_user_id_is_present_or_not() {
        js = new JsonPath(response);
        int id = js.get("id");
        Assert.assertTrue(id > 0, "User ID is not present or invalid!");
    }

    @Given("Add user payload with missing name")
    public void add_user_payload_with_missing_name() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("", email, "Male", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("Verify Name is missing or empty!")
    public void verify_name_is_missing_or_empty() {
        validateErrorResponse(response, "name", "can't be blank");


//        js = new JsonPath(response);
//        String message = js.get("[0].message");
//        String name = js.get("[0].field");
//        Assert.assertEquals(message, "can't be blank");
//        Assert.assertEquals(name, "name");

    }

    @Given("Add user payload with the missing email")
    public void add_user_payload_with_the_missing_email() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", "", "Male", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("It should show the Email is missing or empty! message")
    public void it_should_show_the_email_is_missing_or_empty_message() {
        validateErrorResponse(response, "email", "can't be blank");
//        js = new JsonPath(response);
//        String message = js.get("[0].message");
//        String email = js.get("[0].field");
//        Assert.assertEquals(message, "can't be blank");
//        Assert.assertEquals(email, "email");

    }

    @Given("Add user payload with the missing Gender")
    public void add_user_payload_with_the_missing_gender() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", email, "", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("It should show the Gender is missing or empty! message")
    public void it_should_show_the_gender_is_missing_or_empty_message() {
        validateErrorResponse(response, "name", "can't be blank, can be male of female");
//        js = new JsonPath(response);
//        String message = js.get("[0].message");
//        String gender = js.get("[0].field");
//        Assert.assertEquals(message, "can't be blank, can be male of female");
//        Assert.assertEquals(gender, "gender");
    }

    @Given("Add user payload with the missing Status")
    public void add_user_payload_with_the_missing_status() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", email, "Male", "");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("It should show the Status is missing or empty! message")
    public void it_should_show_the_status_is_missing_or_empty_message() throws IOException {
        validateErrorResponse(response, "status", "can't be blank");
//        js = new JsonPath(response);
//        String message = js.get("[0].message");
//        String status = js.get("[0].field");
//        Assert.assertEquals(message, "can't be blank");
//        Assert.assertEquals(status, "status");
    }

    @When("Status is missing or empty!")
    public void status_is_missing_or_empty() {


    }

    //testuser_244816f7@example.com
    @Given("Add user payload with same email")
    public void add_user_payload_with_same_email() throws IOException {
        User user = new User("aditya pawar", "aditypawar@yopmail.com", "Male", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }

    @When("It should show the email already taken")
    public void it_should_show_the_email_already_taken() {
        validateErrorResponse(response, "email", "has already been taken");
        js = new JsonPath(response);
        String message = js.get("[0].message");
        String email = js.get("[0].field");
        Assert.assertEquals(message, "has already been taken");
        Assert.assertEquals(email, "email");
    }

    @Given("Payload for the users")
    public void payload_for_the_users() throws IOException {
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        // Initialize the RequestSpecification for the GET request
        res = given().spec(requestspecification("https://gorest.co.in/public/v2"));
    }

    @Then("I should receive a list of users")
    public void i_should_receive_a_list_of_users() {
        js = new JsonPath(response);
        List<Object> users = js.getList("");
        assertTrue("User list should not be empty", users.size() > 0);
    }

    @Then("Each user should have id, name, email, gender, and status fields")
    public void each_user_should_have_required_fields() {
        js = new JsonPath(response);
        List<Map<String, Object>> users = js.getList("");
//        for(Map<String, Object> l : users){
//            System.out.println("This is respons of all users : "+ l);
//        }
        for (Map<String, Object> user : users) {
            assertNotNull("User id should not be null", user.get("id"));
            assertTrue("User id should be of type Integer", user.get("id") instanceof Integer);
            assertNotNull("User name should not be null", user.get("name"));
            assertNotNull("User email should not be null", user.get("email"));
            assertNotNull("User gender should not be null", user.get("gender"));
            assertNotNull("User status should not be null", user.get("status"));
        }
    }

    @When("Verify user is created and Get ID from the response")
    public void verify_user_is_created_and_get_id_from_the_response() {
        js = new JsonPath(response);
        ID = js.get("id");

        System.out.println("Extracted id from the response" + ID);
    }

    @When("Verify user details by ID")
    public void verify_user_details_by_id() throws IOException {
        Responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestspecification("https://gorest.co.in/public/v2"))
                .pathParam("id", ID);
        user_call_with_http_request("GetuserDetails", "get");
        js = new JsonPath(response);
        int actualID = js.get("id");
        System.out.println("Extracted id from the response" + actualID);
        assertEquals(ID, actualID);
    }

    @Then("I should receive the user details")
    public void i_should_receive_the_user_details() {
        //value from the response
        js = new JsonPath(response);
        String name = js.get("name");
        String email = js.get("email");
        String gender = js.get("gender");
        String status = js.get("status");
        int id = js.get("id");
        //value from the requested body
        System.out.println("This is requested value : " + user.getName());
        System.out.println("This is requested value : " + user.getEmail());
        System.out.println("This is requested value : " + user.getGender());
        System.out.println("This is requested value : " + user.getStatus());

        //matching the values
        if (user.getName().equalsIgnoreCase(name) && user.getEmail().equalsIgnoreCase(email) &&
                user.getGender().equalsIgnoreCase(gender) && user.getStatus().equalsIgnoreCase(status)) {
            System.out.println("User data matches the response.");
        } else {
            System.out.println("User data does not match the response.");
        }
        System.out.println("Response id : " + id + " and the ID is : " + ID);
        if (String.valueOf(ID).equals(String.valueOf(id))) {
            System.out.println("ID matches the response.");
        } else {
            System.out.println("ID does not match the response.");
        }

    }


//    @When("Verify user details with invalid ID")
//    public void verify_user_details_with_invalid_id() throws IOException {
//        Responsespec = new ResponseSpecBuilder().expectStatusCode(404)
//                .expectContentType(ContentType.JSON).build();
//
//        res = given().spec(requestspecification("https://gorest.co.in/public/v2"))
//                .pathParam("id", "1");
//        user_call_with_http_request("GetuserDetails", "get");
//    }
//    @Then("I should receive the error message and validate the status code will be {int}")
//    public void i_should_receive_the_error_message_and_validate_the_status_code_will_be(Integer int1){
//        js = new JsonPath(response);
//        String errorMessage = js.get("message");
//        Assert.assertEquals(errorMessage,"Resource not found");
//    }

    @When("User calls {string} with {string} http request using invalid ID {string}")
    public void user_calls_with_http_request_using_invalid_id(String resoure, String httpMethod, String id) throws IOException {
        Responsespec = new ResponseSpecBuilder().expectStatusCode(404)
                .expectContentType(ContentType.JSON).build();

        res = given().spec(requestspecification("https://gorest.co.in/public/v2"))
                .pathParam("id", id);
        user_call_with_http_request(resoure, httpMethod);
    }
    @Then("Status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        Responsespec = new ResponseSpecBuilder().expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON).build();
    }
    @Then("The error message should be {string}")
    public void the_error_message_should_be(String string) {
        js = new JsonPath(response);
        String errorMessage = js.get("message");
        Assert.assertEquals(errorMessage,"Resource not found");
    }

    @Given("Update the user create data")
    public void updateTheUserCreateData() throws IOException {
        this.email = Utils.generateRandomEmail();
        user = new User("Aditya pawar", email, "Male", "Active");
        Responsespec = new ResponseSpecBuilder().expectStatusCode(201).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    int PutID;
    User putrequestdata = new User();
    @When("User call {string} with {string} http request for record update")
    public void userCallWithHttpRequestForRecordUpdate(String resourse, String httpMethod) throws IOException {
        Responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
        // Write code here that turns the phrase above into concrete actions
        String updatedemail = Utils.generateRandomEmail();
        putrequestdata = new User("Aditya",updatedemail, "female", "active");
        res = given().log().all().spec(requestspecification("https://gorest.co.in/public/v2"))
                .pathParam("id", PutID).body(putrequestdata);
        user_call_with_http_request(resourse,httpMethod);
        js = new JsonPath(response);
        int actualID = js.get("id");
        System.out.println("Extracted id from the response" + actualID);
        assertEquals(PutID, actualID);

    }

    @Then("Get the created user id")
    public void getTheCreatedUserId() {
        js = new JsonPath(response);
        PutID = js.get("id");
        System.out.println("Extracted id from the response" + PutID);
    }


    @And("Verify that the updated name, email, gender, status")
    public void verifyThatTheUpdatedNameEmailGenderStatus() {
        js = new JsonPath(response);
        String name = js.get("name");
        String email = js.get("email");
        String gender = js.get("gender");
        String status = js.get("status");
        int id = js.get("id");
        //value from the requested body
        System.out.println("This is requested value : " + putrequestdata.getName());
        System.out.println("This is requested value : " + putrequestdata.getEmail());
        System.out.println("This is requested value : " + putrequestdata.getGender());
        System.out.println("This is requested value : " + putrequestdata.getStatus());

        //matching the values
        if (user.getName().equalsIgnoreCase(name) && user.getEmail().equalsIgnoreCase(email) &&
                user.getGender().equalsIgnoreCase(gender) && user.getStatus().equalsIgnoreCase(status)) {
            System.out.println("User data matches the response.");
        } else {
            System.out.println("User data does not match the response.");
        }
        System.out.println("Response id : " + id + " and the ID is : " + PutID);
        if (String.valueOf(PutID).equals(String.valueOf(id))) {
            System.out.println("ID matches the response.");
        } else {
            System.out.println("ID does not match the response.");
        }
    }
}




