package api.test;


import api.endpoints.PetStoreEndPoints2;
import api.payload.store.PetStore;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class PetStore2Tests {
    private PetStore petStorePayload;
    private Logger logger;

    @BeforeClass
    public void setupData(){
        int min = 1;
        int max = 10;

        petStorePayload = new PetStore();
        String[] categories = new String[]{"placed", "approved", "sold"};
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String date = dateFormat.format(Calendar.getInstance().getTime());

        petStorePayload.setPetId(random.nextInt((max - min) + 1) + min);
        petStorePayload.setQuantity(random.nextInt((max - min) + 1) + min);
        petStorePayload.setShipDate(date);
        petStorePayload.setStatus(categories[random.nextInt(categories.length)]);
        petStorePayload.setComplete(random.nextBoolean());

        //logs
        logger = LogManager.getLogger(this.getClass());

        logger.debug("debugging........");
    }

    @Test(priority = 1)
    public void testGetStoreInventory(){
        logger.info("********** Get inventory **********");
        Response response = PetStoreEndPoints2.getInventory();
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Inventory is displayed **********");
    }

    @Test(priority = 2)
    public void testPlaceOrder(){
        logger.info("********** Order placed pet **********");
        Response response = PetStoreEndPoints2.placeOrder(petStorePayload);
        petStorePayload.setId(response.jsonPath().get("id"));
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Order placed was added **********");
    }


    @Test(priority = 3)
    public void testFindPlacedOrderById(){
        logger.info("********** Find placed order **********");
        Response response = PetStoreEndPoints2.findOrderById(petStorePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Order was found **********");
    }

    @Test(priority = 4)
    public void testDeleteOrder(){
        logger.info("********** Delete order **********");
        Response response = PetStoreEndPoints2.deleteOrder(petStorePayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
        logger.info("********** Order was deleted **********");
    }
}
