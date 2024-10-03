package api.endpoints;

import api.payload.pet.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetEndPoints {

    public static Response addNewPet(Pet payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.addPet);
    }

    public static Response getPetById(long id){
        return  given()
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .when()
                .get(Routes.getPetById);
    }

    public static  Response getPetByStatus(String status){
        return given().accept(ContentType.JSON)
                .queryParam("status",status)
                .when()
                .get(Routes.getPetByStatus);
    }

    public static Response updatePetInStoreWithFormData(long id , String name, String status){
        return given()
                .contentType(ContentType.URLENC)
                .formParams("name", name)
                .formParams("status", status)
                .pathParam("petId", id)
                .accept(ContentType.JSON)
                .when()
                .post(Routes.getPetById);
    }


    public static Response uploadAnImage(long id,String additionalMetaData, File file) {
        return given()
                .contentType("multipart/form-data")
                .multiPart("additionalMetadata", additionalMetaData)
                .multiPart("file", file)
                .pathParam("petId", id)
                .accept(ContentType.JSON).when()
                .post(Routes.uplaodImage);
    }


    public static Response updatePet(Pet payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(Routes.addPet);
    }

    public static Response deletePet(long id){
        return given()
                .pathParam("petId", id)
                .when()
                .delete(Routes.getPetById);
    }
}
