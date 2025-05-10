package Resources;

public enum APIResources {

    //enum is th special class in the java which has a collection of constants and methods

    //add user
    AddUser("/users"),
    Getuser("/users"),
    GetuserDetails("/users/{id}"),//holding the id
    Putuserdata("/users/{id}"),
    DeleteUser("/users/{id}");


    private String resource;
     APIResources(String resource){
            this.resource= resource;
     }

     public String getResource(){
         return resource;
     }

}
