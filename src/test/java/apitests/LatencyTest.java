package apitests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Test;
import utils.ConfigFileReader;
import utils.FixtureUtil;

public class LatencyTest {

    static String newFixtureId = "51";

    @Test
    public void AddandRetrieveFixture()
    {
        final String BASE_URL = ConfigFileReader.getProperty("baseURL");
        RestAssured.baseURI = BASE_URL;

        JSONObject fixture = FixtureUtil.getFixture();
        fixture.put("fixtureId", newFixtureId);

        //Request object
        RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(fixture.toString());

        //start a new thread to retrieve the fixture
        RetrieveFixture rf = new RetrieveFixture();
        rf.start();

        long startTime = System.currentTimeMillis();
        Response response = request.post("/fixture");
        long endTime = System.currentTimeMillis();
        System.out.println("time taken to add fixture : "+ (endTime-startTime));
    }
}


class RetrieveFixture extends Thread {
    static String newFixtureId = "51";
    public void run() {
        System.out.println("In RetrieveFixture... ");
        Response response;
        final String BASE_URL = ConfigFileReader.getProperty("baseURL");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        String fixtureId = "51";
        boolean fixtureRetrieved = false;

        long startTime = System.currentTimeMillis();
        do {
            response = request.get("/fixture/" + newFixtureId);
            if (response.getStatusCode() == 200)
                fixtureRetrieved = true;
        } while (!fixtureRetrieved);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to retrieve new fixture (milli seconds) : " + (endTime-startTime));
    }
}
