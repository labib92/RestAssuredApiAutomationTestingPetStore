package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

public class UserTests {
    private User userPayload;
    private  Faker faker;
    private Logger logger;

    @BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5 ,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());

        logger.debug("debugging........");
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("********** Creating user **********");
        Response response =UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** User is created **********");
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("********** Reading user information **********");
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** User information is displayed **********");
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("********** Updating user **********");
        //update Data using playLoad
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("********** User is updated **********");

        //Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();

        String updatedFirstName = responseAfterUpdate.getBody().jsonPath().get("firstName").toString();
        String updatedLatName = responseAfterUpdate.getBody().jsonPath().get("lastName").toString();
        String updatedEmail = responseAfterUpdate.getBody().jsonPath().get("email").toString();

        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200,
                "Response status code should be "+200);
        Assert.assertEquals(updatedFirstName, this.userPayload.getFirstName(),
                "Updated firstname should be "+this.userPayload.getFirstName());
        Assert.assertEquals(updatedLatName, this.userPayload.getLastName(),
                "Updated lastname should be "+ this.userPayload.getLastName());
        Assert.assertEquals(updatedEmail, this.userPayload.getEmail(),
                "Updated email should be "+this.userPayload.getEmail());
    }


    @Test(priority = 4)
    public void deleteUserByName(){
        logger.info("********** Deleting user **********");
        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code should be "+200);
        logger.info("********** User was deleted **********");
    }
}
