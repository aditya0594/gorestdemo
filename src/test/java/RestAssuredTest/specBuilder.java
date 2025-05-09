package RestAssuredTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.addPlace;
import pojo.location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItemInArray;

public class specBuilder {
    @Test
    public void Addapi() {

        //String bodystr = Payload.AddPlace();


        addPlace p = new addPlace();
        p.setAccuracy(50);
        p.setName("Frontline adi house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        //type list which is the array
        List<String> listType = new ArrayList<>();
        listType.add("shoe park");
        listType.add("shop");
        p.setType(listType);

        // for the location which contains the class first we have to create the object to access then we have to use that
        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);


        // request Spec Builder
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("Key", "=qaclick123")
                .setContentType(ContentType.JSON).build();

        //Response Spec Builder
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200).expectContentType(ContentType.JSON).build();



       // RestAssured.baseURI = "https://rahulshettyacademy.com";
        //String response = RestAssured.given().log().all().queryParam("Key", "=qaclick123").header("Content-Type", "application/json")



        //POST
        RequestSpecification res =  given().spec(requestSpecification).body(p);

        String response =  res.when().post("/maps/api/place/add/json")
                .then().spec(responseSpecification).body("scope", equalTo("APP"))
                .extract().response().asString();

        System.out.println(response);
        JsonPath json = new JsonPath(response);
        String placeID = json.getString("place_id");
        System.out.println("extracted place ID " + placeID);

        // Put the place update

        RequestSpecification res1 =  given().spec(requestSpecification).body(p);
        res1.body("{\n" +
                        "\"place_id\":\"" + placeID + "\",\n" +
                        "\"address\":\"Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").when().put("/maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
        //GET

        RequestSpecification res2 = given().log().all().spec(requestSpecification).queryParam("place_id","1c618ccdd09c2c7d2febb4e00603f7ab");
        String responseGet = res2.when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().extract().asString();
        System.out.println("This is the get response : " + responseGet);





        RequestSpecification requestSpecificationfortheuse = new RequestSpecBuilder().
                setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON).build();

        String responsetest = given().spec(requestSpecification).header("x-api-key","reqres-free-v1").log().all()
                .body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}")
                .when().post("/api/users")
                .then().log().all().assertThat().statusCode(201).extract().asString();
        System.out.println( "This is the response we get " + responsetest);



















    }



}
