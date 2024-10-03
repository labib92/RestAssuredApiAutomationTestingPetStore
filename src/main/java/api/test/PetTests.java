package api.test;

import api.endpoints.PetEndPoints;
import api.payload.pet.Category;
import api.payload.pet.Pet;
import api.payload.pet.Tag;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PetTests {
    private Pet petPayLoad;
    private Faker faker;
    private Logger logger;
    private String[] categories;
    private Random random;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        petPayLoad = new Pet();
        categories= new String[]{"available", "pending", "sold"};
        random = new Random();


        petPayLoad.setName(faker.name().firstName());
        petPayLoad.setCategory(new Category(faker.idNumber().hashCode(), faker.animal().name()));
        petPayLoad.setTags(new
                ArrayList<Tag>(
                        Arrays.asList(new Tag(faker.idNumber().hashCode(), faker.name().title()),
                                new Tag(faker.idNumber().hashCode(), faker.name().title()),
                                new Tag(faker.idNumber().hashCode(), faker.name().title()))));
        petPayLoad.setStatus(categories[random.nextInt(categories.length)]);

        //logs
        logger = LogManager.getLogger(this.getClass());

        logger.debug("debugging........");
    }

    @Test(priority = 1)
    public void testAddNewPet(){
        logger.info("********** Adding new pet **********");
        Response response = PetEndPoints.addNewPet(petPayLoad);
        petPayLoad.setId(response.jsonPath().get("id"));
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** New Pet was added **********");
    }

    @Test(priority = 2)
    public void testUpdatePetWithFormData(){
        logger.info("********** Updating pet with form data **********");
        petPayLoad.setName(faker.name().firstName());
        petPayLoad.setStatus(categories[random.nextInt(categories.length)]);

        Response response = PetEndPoints
                .updatePetInStoreWithFormData(petPayLoad.getId(), petPayLoad.getName(), petPayLoad.getStatus());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Pet was updated with form data **********");
    }

    @Test(priority = 3)
    public void testUpdatePet(){
        logger.info("********** Updating pet **********");
        faker = new Faker();
        petPayLoad.setName(faker.name().firstName());
        petPayLoad.setCategory(new Category(faker.idNumber().hashCode(), faker.animal().name()));
        Response response = PetEndPoints.updatePet(petPayLoad);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Pet was updated **********");
    }

    @Test(priority = 4)
    public void testUploadImage(){
        logger.info("********** Upload image **********");
        File image = new File("C:\\Users\\Labib\\Downloads\\waifu.png");
        Response response = PetEndPoints.uploadAnImage(petPayLoad.getId() , petPayLoad.getName(), image);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Image was uploaded successfully **********");
    }

    @Test(priority = 5)
    public void  testGetPetById(){
        logger.info("********** Get pet by id **********");
        Response response = PetEndPoints.getPetById(petPayLoad.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Pet information is displayed **********");
    }

    @Test(priority = 6)
    public void testGetPetByStatus(){
        logger.info("********** Get pet by status **********");
        Response response = PetEndPoints.getPetByStatus(petPayLoad.getStatus());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Pet information is shown **********");
    }

    @Test(priority = 7)
    public void deletePet(){
        logger.info("********** Delete Pet **********");
        Response response = PetEndPoints.deletePet(petPayLoad.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Pet deleted **********");
    }
}
