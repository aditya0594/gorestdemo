package Resources;

public enum APIResources {

    //enum is th special class in the java which has a collection of constants and methods
     AddPlaceAPI("/maps/api/place/add/json"),
    DeletePlaceAPI("/maps/api/place/delete/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    SentOTP("/user/send-otp"),
    VerifyOTP("/user/verify-otp"),
    ConsumerLogin("/user/login"),
    SuperAdminOTP("/admin/send-otp"),
    SuperAdminVerifyOTP("/admin/verify-otp"),
     SuperAdminLogin("/admin/login"),
    BuyandSellCreateProject("/project/sell-add"),
    //add user
    AddUser("/users"),
    Getuser("/users"),
    GetuserDetails("/users/{id}"),
    Putuserdata("/users/{id}");//holding the id

    private String resource;
     APIResources(String resource){
            this.resource= resource;
     }

     public String getResource(){
         return resource;
     }

}
