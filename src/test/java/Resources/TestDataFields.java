package Resources;

import pojo.*;

import java.util.ArrayList;
import java.util.List;

public class TestDataFields {
    public addPlace addplacePayload(String name,String address){
        addPlace p = new addPlace();
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress(address);
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
        return p;
    }
    public String deleteplacePayload(String placeID){
        return "{\r\n    \"place_id\": \""+placeID+"\"\r\n}";
    }
    public SentOTP SentOtp(String consumerEmail){
        SentOTP emailotp = new SentOTP();
        emailotp.setEmail_address(consumerEmail);
        return emailotp;
    }
   /* public VerifyOTP VerifyOTP(String otp, String email){
        VerifyOTP verifyotp = new VerifyOTP();
        verifyotp.setOtp(otp);
        verifyotp.setEmail_address(email);
        return verifyotp;
    }
*/
   public VerifyOTP VerifyOTP(String otp, String email) {
       VerifyOTP verifyotp = new VerifyOTP();
       verifyotp.setOtp(otp);
       verifyotp.setEmail_address(email);
       return verifyotp;
   }
    public ConsumerLogin consumerLogin(String consumerEmail){
        ConsumerLogin emailotp = new ConsumerLogin();
        emailotp.setEmail_address(consumerEmail);
        return emailotp;
    }

    public SuperAdminSentOTP SuperadminSentOTP(String Email){
        SuperAdminSentOTP superadmin = new SuperAdminSentOTP();
        superadmin.setEmail_address(Email);
        return superadmin;

    }

    public SuperAdminVerifyOTP SuperAdminVerifyOTP(int otp, String Email){
        SuperAdminVerifyOTP verifyOTP = new SuperAdminVerifyOTP();
        verifyOTP.setEmail_address(Email);
        verifyOTP.setOtp(String.valueOf(otp));
        return verifyOTP;
    }

    public SuperAdminLogin SuperAdminLogin(String email){
        SuperAdminLogin loginsuperadmin = new SuperAdminLogin();
        loginsuperadmin.setEmail_address(email);
        return loginsuperadmin;
    }

    public Project  createProject(String projectName) {

        String img_base64 = "data:image/jpeg;base64,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";
        Project project = new Project();
        project.setProject_name(projectName);
        project.setProject_description("");
        project.setMaster_project_type_id(1);
        project.setMaster_project_category_id(2);
        project.setLocation("Chatrapati Square, Sawarkar Nagar, Vivekanand Nagar, Nagpur, Maharashtra");
        project.setLatitude(21.1107592);
        project.setLongitude(79.07008859999999);
        project.setMaster_country_id(103);
        project.setBid_expiry_day(90);
        project.setMaster_agreement_type_id(1);
        project.setCost_sharing(100);
        project.setMaster_cost_sharing_unit_id(125);
        project.setAverage_monthly_payment(1);
        project.setAmp_master_currency_id(125);
        project.setLeasing_tariff(null);
        project.setLt_master_currency_id(125);
        project.setProject_capacity(1000);
        project.setMaster_capacity_unit_id(2);
        project.setTenure_length(1);
        project.setTenure_age(1);
        project.setMaster_module_specification_id(5);
        project.setModule_brand("brand");
        project.setMaster_grid_connection_id(3);
        project.setCurrent_tenancy_age(100);
        project.setMounting_system("Mounting System");
        project.setInstallation_date("2025-03-05");
        project.setGeneration_data("");
        project.setGeneration_data_unit(2);
        project.setMaintenance_covered_under(true);
        project.setTemporary_occupation_permit_date("2025-03-04");
        project.setAvg_system_eff(10);
        project.setAvg_system_eff_unit("%");
        project.setDegeneration_rate(1);
        project.setSell_budget(150000);
        project.setSbu_master_currency_id(125);
        project.setAddon_feature("");
        project.setVideo_url("https://www.youtube.com/watch?v=Xf0yP-kNyXQ");

        // Adding sample project images
        List<Project.ProjectImage> projectImages = new ArrayList<>();
        projectImages.add(new Project.ProjectImage(img_base64, "IMG 1.png", "png", true));
        projectImages.add(new Project.ProjectImage(img_base64, "IMG 2.png", "png", false));
        projectImages.add(new Project.ProjectImage(img_base64, "IMG 3.png", "png", false));
        projectImages.add(new Project.ProjectImage(img_base64, "IMG 5.png", "png", false));

        project.setProject_images(projectImages);
        project.setProject_documents(new ArrayList<>());

        return project;
    }
}

