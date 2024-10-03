package api.endpoints;

import api.payload.pet.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class PetEndPoints2 {

    private static ResourceBundle getURL(){
        return ResourceBundle.getBundle("routes");
    }

    public static Response addNewPet(Pet payload){
        String addPet = getURL().getString("addPet");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(addPet);
    }

    public static Response getPetById(long id){
        String getPetById = getURL().getString("getPetById");
        return  given()
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .when()
                .get(getPetById);
    }

    public static  Response getPetByStatus(String status){
        String getPetByStatus = getURL().getString("getPetByStatus");
        return given().accept(ContentType.JSON)
                .queryParam("status",status)
                .when()
                .get(getPetByStatus);
    }

    public static Response updatePetInStoreWithFormData(long id , String name, String status){
        String updateWithFormData = getURL().getString("updatedPetWithForData");
        return given()
                .contentType(ContentType.URLENC)
                .formParams("name", name)
                .formParams("status", status)
                .pathParam("petId", id)
                .accept(ContentType.JSON)
                .when()
                .post(updateWithFormData);
    }


    public static Response uploadAnImage(long id,String additionalMetaData, File file) {
        String uploadPhoto = getURL().getString("uploadImage");
        return given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata", additionalMetaData)
                .multiPart("file", file)
                .pathParam("petId", id)
                .accept(ContentType.JSON).when()
                .post(uploadPhoto);
    }


    public static Response updatePet(Pet payload){
        String updatePet = getURL().getString("updatePet");
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(updatePet);
    }

    public static Response deletePet(long id){
        String deletePet = getURL().getString("deletePet");
        return given()
                .pathParam("petId", id)
                .when()
                .delete(deletePet);
    }
}
