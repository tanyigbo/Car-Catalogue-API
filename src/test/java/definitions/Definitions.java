package definitions;

import com.example.carapi.CarApiApplication;
import com.example.carapi.model.Manufacturer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
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

    @Then("A list of manufacture is returned")
    public void aListOfManufactureIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        List<Map<String, String>> manufacturers = response.jsonPath().get("data");
        Assert.assertTrue(manufacturers.size() > 0);
    }

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
}
