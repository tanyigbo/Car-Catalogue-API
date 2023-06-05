package definitions;

import com.example.carapi.CarApiApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CarApiApplication.class)

public class Definitions {

    @Given("A list of manufacture")
    public void aListOfManufacture() {
        Assert.assertFalse(false);
    }

    @Given("A list of cars")
    public void aListOfCars() {
        Assert.assertTrue(true);
    }
}
