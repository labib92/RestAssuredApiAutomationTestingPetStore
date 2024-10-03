package api.endpoints;

import api.payload.store.PetStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class PetStoreEndPoints2 {

    private static ResourceBundle getURL(){
        return ResourceBundle.getBundle("routes");
    }

    public static Response getInventory(){
        String getInventory = getURL().getString("getInventory");
        return given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(getInventory);
    }


    public static Response placeOrder(PetStore payload){
        String placeOrder = getURL().getString("placeOrder");
        return given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(placeOrder);
    }

    public static Response findOrderById(long id){
        String findOrderById = getURL().getString("findOrderById");
        return  given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("orderId", id)
                .when()
                .get(findOrderById);
    }

    public static Response deleteOrder(long id){
        String deleteOrder = getURL().getString("deleteOrder");
        return given()
                .pathParam("orderId", id)
                .when()
                .delete(deleteOrder);
    }
}
