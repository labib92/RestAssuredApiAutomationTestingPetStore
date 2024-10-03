package api.endpoints;

import api.payload.User.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    //method created for getting URL's from properties files
    private static ResourceBundle getURL(){
        return  ResourceBundle.getBundle("routes");
    }

    public static Response createUser(User payload){
       String postUrl = getURL().getString("postUrl");
        return given()
                 .contentType(ContentType.JSON)
                 .accept(ContentType.JSON)
                 .body(payload)
                 .when()
                 .post(postUrl);
    }


    public static Response readUser(String username){
        String getUrl = getURL().getString("getUrl");
        return given()
                .pathParam("username", username)
                .when()
                .get(getUrl);
    }

    public static Response updateUser(String username, User payload){
        String updateUrl = getURL().getString("updateUrl");
        return given()
                .pathParam("username", username)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(updateUrl);
    }

    public static Response deleteUser(String username){
        String deleteUrl = getURL().getString("deleteUrl");
        return given()
                .pathParam("username", username)
                .when()
                .delete(deleteUrl);
    }
}
