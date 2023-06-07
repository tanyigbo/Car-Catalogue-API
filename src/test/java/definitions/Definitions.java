package definitions;

import com.example.carapi.CarApiApplication;
import com.example.carapi.model.Car;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarApiApplication.class)
public class Definitions {

    private static final String BASE_URL = "http://localhost:";
    private static final String SUCCESS = "success";
    private static final RequestSpecification request = RestAssured.given();
    private static Response response;

    @LocalServerPort
    String port;


    /**
     * Manufacturer Tests
     */
    @When("User requests a list of all manufacturers")
    public void userRequestsAListOfAllManufacturers() {
        response = request.get(BASE_URL + port + "/api/manufacturers");
    }

    @Then("A list of manufacturers is returned")
    public void aListOfManufacturersIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        List<Map<String, String>> manufacturers = response.jsonPath().get("data");
        Assert.assertTrue(manufacturers.size() > 0);
    }

    @When("User requests a manufacturer by id")
    public void userRequestsAManufacturerById() {
        response = request.get(BASE_URL + port + "/api/manufacturers/2");
    }

    @Then("Requested manufacturer is returned")
    public void requestedManufacturerIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        Map<String, Object> manufacturer = response.jsonPath().get("data");
        Assert.assertEquals(2, manufacturer.get("id"));
        Assert.assertEquals("BMW", manufacturer.get("name"));
        Assert.assertEquals("Germany", manufacturer.get("country"));
    }

    @When("User requests a manufacturer by id that does not exist")
    public void userRequestsAManufacturerByIdThatDoesNotExist() {
        response = request.get(BASE_URL + port + "/api/manufacturers/10");
    }

    @Then("Error message returned stating manufacture not found")
    public void errorMessageReturnedStatingManufactureNotFound() {
        Assert.assertEquals(404, response.getStatusCode());
        String message = response.jsonPath().get("message");
        String expected = "Manufacturer with ID 10 was not found.";
        Assert.assertEquals(expected, message);
    }

    @When("User requests a list of all cars by manufacturer")
    public void userRequestsAListOfAllCarsByManufacturer() {
        response = request.get(BASE_URL + port + "/api/manufacturers/1/cars");
    }

    @Then("A list of all cars by requested manufacturer is returned")
    public void aListOfAllCarsByRequestedManufacturerIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        List<Map<String, Object>> cars = response.jsonPath().get("data");
        Assert.assertTrue(cars.size() > 0);
    }

    /**
     * Car Tests
     */
    @When("User requests a list of all cars")
    public void userRequestsAListOfAllCars() {
        response = request.get(BASE_URL + port + "/api/cars");
    }

    @Then("A list of all cars is returned")
    public void aListOfAllCarsIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        List<Map<String, String>> cars = response.jsonPath().get("data");
        Assert.assertTrue(cars.size() > 0);
        System.out.println(cars);
    }

    @When("User requests a car by id")
    public void userRequestsACarById() {
        response = request.get(BASE_URL + port + "/api/cars/1");
    }

    @Then("Requested car is returned")
    public void requestedCarIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        Map<String, Object> car = response.jsonPath().get("data");
        Assert.assertEquals(1, car.get("id"));
        Assert.assertEquals("e-tron GT", car.get("model"));
    }

    @When("User requests a car by id that does not exist")
    public void userRequestsACarByIdThatDoesNotExist() {
        response = request.get(BASE_URL + port + "/api/cars/13");
    }

    @Then("Error message returned stating car not found")
    public void errorMessageReturnedStatingCarNotFound() {
        Assert.assertEquals(404, response.getStatusCode());
        String message = response.jsonPath().get("message");
        String expected = "Car with ID 13 was not found.";
        Assert.assertEquals(expected, message);
    }

    @When("User requests a list of images of a specific car")
    public void userRequestsAListOfImagesOfASpecificCar() {
        response = request.get(BASE_URL + port + "/api/cars/1/images");
    }

    @Then("A list of all images of requested car is returned")
    public void aListOfAllImagesOfRequestedCarIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        List<Map<String, String>> images = response.jsonPath().get("data");
        Assert.assertTrue(images.size() > 0);
    }

    @When("User requests all reviews of specific car")
    public void userRequestsAllReviewsOfSpecificCar() {
        response = request.get(BASE_URL + port + "/api/cars/5/reviews");
    }

    @Then("List of all reviews of requested car is returned")
    public void listOfAllReviewsOfRequestedCarIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        List<Map<String, Object>> reviews = response.jsonPath().get("data");
        Assert.assertTrue(reviews.size() > 0);
        Assert.assertEquals("Title 5", reviews.get(1).get("title"));
        Assert.assertEquals("Reviewer 5", reviews.get(1).get("reviewerName"));
    }

    /**
     * Review Tests
     */
    @When("User requests a list of images of specific review")
    public void userRequestsAListOfImagesOfSpecificReview() {
        response = request.get(BASE_URL + port + "/api/reviews/4/images");
    }

    @Then("List of all images of requested review is returned")
    public void listOfAllImagesOfRequestedReviewIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        List<Map<String, Object>> images = response.jsonPath().get("data");
        Assert.assertTrue(images.size() > 0);
        Assert.assertEquals(4, images.get(0).get("id"));
        Assert.assertEquals("reviewImg04.png", images.get(0).get("imageAddress"));
        Assert.assertEquals(5, images.get(1).get("id"));
        System.out.println(images);
    }

    @When("User provides new review info")
    public void userProvidesNewReviewInfo() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "New Review");
        requestBody.put("reviewText", "The red fox jumped over the brown fence.");
        requestBody.put("reviewerName", "Superman");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/reviews/new/3");
    }

    @Then("Created review returned")
    public void createdReviewReturned() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals(SUCCESS, message);
        Map<String, Object> review = response.jsonPath().get("data");
        Assert.assertEquals("New Review", review.get("title"));
        Assert.assertEquals("Superman", review.get("reviewerName"));
    }
}
