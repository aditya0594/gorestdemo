package Stepdefination;

import Resources.APIResources;
import Resources.TestDataFields;
import Resources.Utils;
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
import pojo.VerifyOTP;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinationFile extends Utils{
    static ResponseSpecification Responsespec;
     RequestSpecification res;
    static String place_id;
    JsonPath js;
    static String response;
    static String response1;

    TestDataFields data = new TestDataFields();
    StepDefinationSuperadmin admin = new StepDefinationSuperadmin();
    String email;

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
        System.out.println("Resoures API : "  + resourceAPI.getResource());
         if(method.equalsIgnoreCase("POST")){
             response =  res.when().post(resourceAPI.getResource())
                     .then().spec(Responsespec).extract().response().asString();
                    System.out.println(response);
         }

         else if (method.equalsIgnoreCase("GET")){
             response1 =  res.when().get(resourceAPI.getResource())
                     .then().spec(Responsespec).extract().response().asString();
            System.out.println(response1);
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
        User user = new User("Aditya Pawar", email, "Male", "Active");
        Responsespec  = new ResponseSpecBuilder().expectContentType("application/json").build();
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
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    @When("Verify Name is missing or empty!")
    public void verify_name_is_missing_or_empty() {
        js = new JsonPath(response);
        String message = js.get("[0].message");
        String name = js.get("[0].field");
        Assert.assertEquals(message, "can't be blank");
        Assert.assertEquals(name, "name");

    }

    @Given("Add user payload with the missing email")
    public void add_user_payload_with_the_missing_email() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", "", "Male", "Active");
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    @When("It should show the Email is missing or empty! message")
    public void it_should_show_the_email_is_missing_or_empty_message() {
        js = new JsonPath(response);
        String message = js.get("[0].message");
        String email = js.get("[0].field");
        Assert.assertEquals(message, "can't be blank");
        Assert.assertEquals(email, "email");

    }
    @Given("Add user payload with the missing Gender")
    public void add_user_payload_with_the_missing_gender() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", email, "", "Active");
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    @When("It should show the Gender is missing or empty! message")
    public void it_should_show_the_gender_is_missing_or_empty_message() {
        js = new JsonPath(response);
        String message = js.get("[0].message");
        String gender = js.get("[0].field");
        Assert.assertEquals(message, "can't be blank, can be male of female");
        Assert.assertEquals(gender, "gender");
    }

    @Given("Add user payload with the missing Status")
    public void add_user_payload_with_the_missing_status() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar", email, "Male", "");
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    @When("It should show the Status is missing or empty! message")
    public void it_should_show_the_status_is_missing_or_empty_message() throws IOException {
        this.email = Utils.generateRandomEmail();
        User user = new User("aditya pawar",email , "Male", "");
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
    @When("Status is missing or empty!")
    public void status_is_missing_or_empty() {
        js = new JsonPath(response);
        String message = js.get("[0].message");
        String status = js.get("[0].field");
        Assert.assertEquals(message, "can't be blank");
        Assert.assertEquals(status, "status");
    }
//testuser_244816f7@example.com
    @Given("Add user payload with same email")
    public void add_user_payload_with_same_email() throws IOException {
        User user = new User("aditya pawar","aditypawar@yopmail.com" , "Male", "Active");
        Responsespec  = new ResponseSpecBuilder().expectStatusCode(422).expectContentType("application/json").build();
        res = given().log().all()
                .spec(requestspecification("https://gorest.co.in/public/v2"))
                .body(user);
    }
        @When("It should show the email already taken")
        public void it_should_show_the_email_already_taken() {
            js = new JsonPath(response);
            String message = js.get("[0].message");
            String email = js.get("[0].field");
            Assert.assertEquals(message, "has already been taken");
            Assert.assertEquals(email, "email");
        }









































    @Then("verify the place id created maps to {string} using {string}")
    public void verify_the_place_id_created_maps_to_using(String expectedName, String resourceGet) throws IOException {
        // Write code here that turns the phrase above into concrete actions
        js = new JsonPath(response);
        place_id = js.get("place_id");
        System.out.println("Place ID is : "+ place_id);

        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1")).queryParams("key", "qaclick123").queryParam("place_id",place_id);
        user_call_with_http_request(resourceGet,"get");
        // Parse the GET response
        JsonPath js1 = new JsonPath(response1); // Ensure you use response1 for the GET call's response

        // Extract the name from the response
        String actualName = js1.getString("name");
        System.out.println(actualName);
        System.out.println("Extracted name from the response: " + actualName);

        // Verify that the extracted name matches the expected name
        assertEquals(expectedName, actualName);

    }
    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res = given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1"))
                .body(data.deleteplacePayload(place_id));

    }

    @Given("chaining verfiy otp")
    public void chaining_verfiy_otp() throws IOException {
        String OTP = String.valueOf(StepDefinationConsumer.getOtp());
        System.out.println("Verify api otp is : "+ OTP);
        res =  given().log().all().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1"))
                .body("{\n" +
                        "    \"email_address\" : \"{{Account_Email_id}}\",\n" +
                        "    \"otp\" : \""+OTP+"\"\n" +
                        "}");
    }
    @Given("Sent otp payload")
    public void sent_otp_payload() throws IOException {
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
//        res =  given().spec(requestspecification()).body(data.email_address("adityapawar2@yopmail.com"));
        //Response SpecBuilder
        res =  given().log().all().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1"))
                .body(data.SentOtp("adityapawar@yopmail.com"));

    }
   /* @Given("Verify otp payload")
    public void verify_otp_payload() throws IOException, InterruptedException {
        StepDefinationConsumer StepDefinationConsumer =new StepDefinationConsumer();
        String otp = "283320";//StepDefinationConsumer.getOtp();
        System.out.println("this the otp : " + otp);
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(422)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1").body(data.VerifyOTP(otp,"adityapawar@yopmail.com")));
        System.out.println("This is the property of baseurl" + properties("sos_baseurl"));//Thread.sleep(3000);
    }*/



   @Given("Verify otp payload")
   public void verify_otp_payload() throws IOException, InterruptedException {
       StepDefinationConsumer StepDefinationFileconsumer = new StepDefinationConsumer();
       String otp = StepDefinationFileconsumer.getOtp();
       System.out.println("This is the otp for the verify otp "+ otp);

   }



    @Given("Login consumer payload")
    public void login_consumer_payload() throws IOException {
        String consumerLogintoken = readExcel("Tokens",1,0);
        System.out.println("This is the consumer login " + consumerLogintoken);
//        Responsespec = new ResponseSpecBuilder()
//                .expectStatusCode(2)
//                .expectContentType(ContentType.JSON).build();
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1").header("x-sso-auth",consumerLogintoken).body(data.consumerLogin("adityapawar@yopmail.com")));

    }


    @Given("Super Admin Login credential for otp")
    public void super_admin_login_credential_for_otp() throws IOException {

        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1")).body(data.SuperadminSentOTP("gabriel.sze@yopmail.com"));
    }
    @Given("Super Admin login credential for verify otp")
    public void super_admin_login_credential_for_verify_otp() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        String otp = StepDefinationSuperadmin.AdminOTP;
      Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1")).body(data.SuperAdminVerifyOTP(Integer.parseInt(otp),"gabriel.sze@yopmail.com"));
    }

    @Given("Super Admin login credential")
    public void super_admin_login_credential() throws IOException {
        String AdminToken = readExcel("Tokens",1,1);
        System.out.println("the adminToken : "+ AdminToken);
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://qa.gaedkeeper.com/qa/api/v1")).header("x-sso-auth",AdminToken).body(data.SuperAdminLogin("gabriel.sze@yopmail.com"));
    }
    @Given("Payload for the buy and sell project")
    public void payload_for_the_buy_and_sell_project() throws IOException {
        String loginToken = readExcel("Tokens",1,3);
        File jsonFile = new File("src/main/java/pojo/BuySell_project_JSON.json");
        System.out.println("The loginToken from the excel : "+ loginToken);  // writeExcel("Tokens",1,3,LoginToken);
        Responsespec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        res =  given().spec(requestspecification("https://marketplace.qa.gaedkeeper.com/qa/api/v1")).header("x-sso-auth",loginToken).body(jsonFile);
    }
}



