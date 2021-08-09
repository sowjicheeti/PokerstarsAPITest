package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import utils.ConfigFileReader;
import utils.FixtureUtil;
import java.io.IOException;

public class CreateAndDeleteFixtureTest {
   @Test
   public void CreateAndDeleteFixture() throws IOException, ParseException {

      final String BASE_URL = ConfigFileReader.getProperty("baseURL");
      Response response;
      JSONObject fixture = FixtureUtil.getFixture();
      String newFixtureId = "100";
      fixture.put("fixtureId",newFixtureId);
      System.out.println("fixture is: " + fixture);

      //specify base URI
      RestAssured.baseURI = BASE_URL;

      //Request object
      RequestSpecification request = RestAssured.given()
              .header("Content-Type", "application/json")
              .body(fixture.toString());

      //Response object
      response = request.post("/fixture");

      //print response on console window
      String responseBody = response.getBody().asString();
      System.out.println("Response Body is: " + responseBody);

      //status code validation
      int statusCode = response.getStatusCode();
      System.out.println("Status Code is: " + statusCode);

      //verifying status code
      Assert.assertEquals(200, statusCode);
      System.out.println("Fixture created successfully");


      //delete the fixture
       request = RestAssured.given();
       response = request.delete("/fixture/"+newFixtureId);


      //check the status of deletion
      statusCode = response.getStatusCode();
      Assert.assertEquals(200, statusCode);
      System.out.println("Fixture deleted successfully");

      //retrieve the fixture to check if its deleted
      request = RestAssured.given();
      response = request.get("/fixture/"+newFixtureId);

      statusCode = response.getStatusCode();
      Assert.assertEquals(404, statusCode);

      //Fixture not found
      Assert.assertEquals("Fixture could not be deleted","Fixture not found", response.htmlPath().getString("html.body"));

   }
   }


