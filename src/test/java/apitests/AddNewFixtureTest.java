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
import java.io.*;

public class AddNewFixtureTest {

   @Test
   public void AddFixture() throws IOException, ParseException {

      final String BASE_URL = ConfigFileReader.getProperty("baseURL");
      Response response;
      JSONObject fixture = FixtureUtil.getFixture();

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
   }

   @Test
   public void validateNewFixture() throws IOException, ParseException {

      Response response;
      RequestSpecification request = RestAssured.given();
      JSONObject fixture = FixtureUtil.getFixture();
      String fixtureId = (String)fixture.get("fixtureId");

      System.out.println("Retrieved fixtureId is: " + fixtureId);

      //Response object
      response = request.get("/fixture/"+ fixtureId);

      //verifying status code
      Assert.assertEquals(200, response.getStatusCode());

      String teamID = response.getBody().jsonPath().getString("footballFullState.teams[0].teamId");
      System.out.println("TeamID is: " + teamID);
      Assert.assertEquals("HOME", teamID);

      }

   }


