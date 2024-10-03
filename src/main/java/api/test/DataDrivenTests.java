package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data" , dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName,
                             String firstName , String lastName,
                             String userEmail, String password , String phoneNumber){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phoneNumber);

        Response response = UserEndPoints.createUser(userPayload);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Response status code should be "+200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username) {
        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200,
                "Status code should be "+200);
    }
}
