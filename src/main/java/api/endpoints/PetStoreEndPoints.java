package api.endpoints;

import api.payload.store.PetStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetStoreEndPoints {

    public static Response getInventory(){
        return given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.getStoreInventory);
    }


    public static Response placeOrder(PetStore payload){
        return given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.placeOrder);
    }

    public static Response findOrderById(long id){
        return  given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("orderId", id)
                .when()
                .get(Routes.findOrderById);
    }

    public static Response deleteOrder(long id){
        return given()
                .pathParam("orderId", id)
                .when()
                .delete(Routes.findOrderById);
    }
}
