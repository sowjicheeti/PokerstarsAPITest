package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import utils.ConfigFileReader;

public class RetrieveFixturesTest {

   //Assert that there are 3 fixtures within the returned object
   //Assert that each of the 3 fixtures has a fixtureId value
   @Test
   public void validateFixtures() {
      final String BASE_URL = ConfigFileReader.getProperty("baseURL");
      Response response;

      //specify base URI
      RestAssured.baseURI = BASE_URL;

      //Request object
      RequestSpecification request = RestAssured.given();

      //Response object
      response = request.get("/fixtures");

      //print response on console window
      String responseBody = response.getBody().asString();
      System.out.println("Response Body is: " + responseBody);

      //status code validation
      int statusCode = response.getStatusCode();
      Assert.assertEquals(200, statusCode);

      JSONArray fixturesArray = new JSONArray(responseBody);

      //checking that there are 3 fixtures within the returned object
      Assert.assertEquals(3, fixturesArray.length());

      //checking that each of the 3 fixtures has a fixtureId value
      for (int i = 0; i < fixturesArray.length(); i++) {
         JSONObject fixtureJSON = fixturesArray.getJSONObject(i);
         String fixtureId = fixtureJSON.getString("fixtureId");
         System.out.println("fixtureId is: " + fixtureId);
         Assert.assertNotNull(fixtureId);
      }
   }
}
