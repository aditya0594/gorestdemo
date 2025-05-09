package RestAssuredTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post_Get_PUT {

    RequestSpecification requestSpecificationfortheuse = new RequestSpecBuilder().
            setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON).build();
    ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(201).build();
    @Test
    public void post() {

        String responsetest = given().spec(requestSpecificationfortheuse).header("x-api-key","reqres-free-v1").log().all()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when().post("/api/users")
                .then().log().all().assertThat().statusCode(201).extract().asString();
        System.out.println( "This is the response we get " + responsetest);

    }

}
