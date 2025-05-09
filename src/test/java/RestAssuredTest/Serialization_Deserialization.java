package RestAssuredTest;

public class Serialization_Deserialization {
    /**
     Rest Assured uses libraries like "Jackson" or "Gson" under the hood to handle the conversion automatically.

     Serialization is the process of converting a Java object into JSON format to send in the request body.

     📌 Why use it?
     When you want to send structured data (e.g., in a POST or PUT request),
     converting a Java object to JSON makes the request cleaner and type-safe.


     User user = new User();
     user.setId(1);
     user.setName("Aditya");
     user.setEmail("aditya@example.com");

     given()
     .contentType("application/json")
     .body(user)
     .when()
     .post("/users")
     .then()
     .statusCode(201);



     Deserialization (JSON → Java Object)
     Deserialization is the process of converting JSON response data into a Java object.

     📌 Why use it?
     So that you can easily access and assert specific fields using object properties instead of parsing the raw JSON manually.

     Imagine this JSON response:
     {
     "id": 1,
     "name": "Aditya",
     "email": "aditya@example.com"
     }

     And you have a POJO class like this:
     private int id;
     private String name;
     private String email;

     // Getters and setters
     }

     User user = response.as(User.class);  // json get deserializted
     System.out.println(user.getName());  // Output: Aditya and by the getter method































     */



}
