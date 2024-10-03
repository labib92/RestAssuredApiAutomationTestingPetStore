package api.endpoints;

public class Routes {
    public static String baseUrl = "https://petstore.swagger.io/v2";

    //User Model
    public static String postUrl = baseUrl + "/user";
    public static String getUrl = baseUrl + "/user/{username}";
    public static String updateUrl = baseUrl + "/user/{username}";
    public static String deleteUrl = baseUrl + "/user/{username}";

    //Pet
    public static String addPet = baseUrl +"/pet";
    public static String getPetById = baseUrl + "/pet/{petId}";
    public static String getPetByStatus = baseUrl+ "/pet/findByStatus";
    public static String uplaodImage = baseUrl +"/pet/{petId}/uploadImage";

    //Store
    public static String getStoreInventory = baseUrl +"/store/inventory";
    public static String placeOrder = baseUrl + "/store/order";
    public static String findOrderById = baseUrl + "/store/order/{orderId}";


}
