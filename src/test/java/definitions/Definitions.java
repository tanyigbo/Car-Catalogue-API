package definitions;

import com.example.carapi.CarApiApplication;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarApiApplication.class)
public class Definitions {

    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request = RestAssured.given();
    private static Response response;

    @LocalServerPort
    String port;

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
        response = request.get(BASE_URL + port + "/api/manufacturer/2");
    }

    @Then("Requested manufacturer is returned")
    public void requestedManufacturerIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals("success", message);
        Map<String, Object> manufacturer = response.jsonPath().get("data");
        Assert.assertEquals(2, manufacturer.get("id"));
        Assert.assertEquals("BMW", manufacturer.get("name"));
        Assert.assertEquals("Germany", manufacturer.get("country"));
    }

    @When("User requests a manufacturer by id that does not exist")
    public void userRequestsAManufacturerByIdThatDoesNotExist() {
        response = request.get(BASE_URL + port + "/api/manufacturer/10");
    }

    @Then("Error message returned stating manufacture not found")
    public void errorMessageReturnedStatingManufactureNotFound() {
        Assert.assertEquals(404,response.getStatusCode());
        String message = response.jsonPath().get("message");
        String expected = "Manufacturer with ID 10 was not found.";
        Assert.assertEquals(expected,message);
    }

    /**
     *
     * Car Tests
     *
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
    }

    @When("User requests a car by id")
    public void userRequestsACarById() {
        response = request.get(BASE_URL + port + "/api/cars/1");
    }

    @Then("Requested car is returned")
    public void requestedCarIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Assert.assertEquals("success", message);
        Map<String, Object> car = response.jsonPath().get("data");
        Assert.assertEquals(1, car.get("id"));
        Assert.assertEquals("e-tron GT", car.get("model"));
    }
}
