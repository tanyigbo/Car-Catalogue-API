package com.example.carapi.seed;

import com.example.carapi.model.*;
import com.example.carapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CarRepository carRepository;
    private final ImageRepository imageRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Autowired
    public DataLoader(CarRepository carRepository, ImageRepository imageRepository, ManufacturerRepository manufacturerRepository, ReviewRepository reviewRepository, ReviewImageRepository reviewImageRepository) {
        this.carRepository = carRepository;
        this.imageRepository = imageRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.reviewRepository = reviewRepository;
        this.reviewImageRepository = reviewImageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadManufactureData();
        loadCarData();
        loadCarImageData();
        loadReviewData();
        loadReviewImageData();
    }

    /**
     * Data loader for Manufacturer Repository
     */
    private void loadManufactureData() {
        if (manufacturerRepository.count() == 0) {
            List<Manufacturer> manufacturers = Arrays.asList(
                    new Manufacturer(1L, "Audi", "Germany"),
                    new Manufacturer(2L, "BMW", "Germany"),
                    new Manufacturer(3L, "Hyundai", "South Korea"),
                    new Manufacturer(4L, "Tesla", "United States")
            );
            manufacturerRepository.saveAll(manufacturers);
        }
    }

    /**
     * Data loader for Car Repository
     */
    private void loadCarData() {
        if (carRepository.count() == 0) {
            List<Manufacturer> manufacturers = manufacturerRepository.findAll();
            List<Car> cars = Arrays.asList(
                    // range capacity cargo length width height drive
                    new Car("e-tron GT", 232, 93.4, 12.9, 196.5, 77.2, 55.5, "AWD", manufacturers.get(0)),
                    new Car("Q4 e-tron", 252, 82, 18.4, 180.6, 73.4, 64.3, "RWD", manufacturers.get(0)),
                    new Car("i4 M50", 240, 83.9, 16.6, 188.3, 72.9, 57, "AWD", manufacturers.get(1)),
                    new Car("iX M60", 324, 111.5, 17.7, 195, 77.4, 66.7, "AWD", manufacturers.get(1)),
                    new Car("Model S", 315, 100, 31.6, 196, 77.3, 56.9, "AWD", manufacturers.get(3)),
                    new Car("Model 3", 315, 75, 15, 184.8, 76.1, 56.8, "AWD", manufacturers.get(3)),
                    new Car("Ioniq 5", 256, 72.6, 18.8, 182.5, 74.4, 63.2, "AWD", manufacturers.get(2))
            );
            carRepository.saveAll(cars);
        }
    }

    /**
     * Data loader for Image Repository
     */
    private void loadCarImageData() {
        if (imageRepository.count() == 0) {
            List<Car> cars = carRepository.findAll();
            String Base_URL = "https://raw.githubusercontent.com/tanyigbo/Car-Catalogue-API/main/src/main/resources/images/car_images/";
            List<String> values = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
            values.forEach(value -> {
                imageRepository.save(new Image(Base_URL + "Audi/e-tron_GT/GT_" + value + ".webp", cars.get(0)));
                imageRepository.save(new Image(Base_URL + "Audi/Q4_e-tron/Q4_" + value + ".webp", cars.get(1)));
                imageRepository.save(new Image(Base_URL + "BMW/i4/i4_" + value + ".webp", cars.get(2)));
                imageRepository.save(new Image(Base_URL + "BMW/iX/ix_" + value + ".webp", cars.get(3)));
                imageRepository.save(new Image(Base_URL + "Tesla/Model_S/ModelS_" + value + ".webp", cars.get(4)));
                imageRepository.save(new Image(Base_URL + "Tesla/Model_3/Model3_" + value + ".webp", cars.get(5)));
                imageRepository.save(new Image(Base_URL + "Hyundai/Ioniq_5/Ioniq5_" + value + ".webp", cars.get(6)));
            });

        }
    }

    /**
     * Data loader for Review Repository
     */
    private void loadReviewData() {
        if (reviewRepository.count() == 0) {
            List<Car> cars = carRepository.findAll();
            List<Review> reviews = Arrays.asList(
                    new Review("Cupholder Test", "I absolutely adore the Hyundai Ioniq 5, and so was excited to spend a week with it when the EV landed in my driveway. When I first drove the Ioniq 5 in San Diego, though, I hadn't packed my favorite style water bottle: the big 32-ounce Nalgene.", "JOHN BELTZ SNYDER", cars.get(6)),
                    new Review("Audi Hits a Bullseye", "We think the E-Tron GT is a winning package and an excellent ambassador for EVs. It speaks the visual and tactile language of cars, while providing all the benefits of a Tesla underneath. Audi has struck a bullseye.", "BEN HSU", cars.get(0)),
                    new Review("BMW i4 Luggage Test", "On paper, the BMW i4 has 10 cubic-feet of space, which is on par with what you'd usually find in a coupe. Now, BMW would tell you that the i4 is a coupe — it is, after all, based on the 4 Series Gran Coupe.", "JAMES RISWICK", cars.get(2)),
                    new Review("Get Anywhere", "Model 3 can get you anywhere you want to go — with industry-leading range and convenient charging options, all over the world. Model 3 charges up to 80% in 40 minutes on a Tesla Supercharger.", "EV Compare", cars.get(5)),
                    new Review("We Rented a Tesla Model S Plaid", "Review Text 5", "Zac Palmer", cars.get(4)),
                    new Review("Tesla Convertible", "While custom coach built Teslas aren't new, not even convertibles, this example from Italian design house Ares Design is one of the nicest we've seen. It's had quite a bit of work put into it as well, since not only is it a convertible, but it's a two-door, too.", "JOEL STOCKSDALE", cars.get(4))
            );
            reviewRepository.saveAll(reviews);
        }
    }

    /**
     * Data loader for Review Image Repository
     */
    private void loadReviewImageData() {
        if (reviewImageRepository.count() == 0) {
            List<Review> reviews = reviewRepository.findAll();
            String base_url = "https://raw.githubusercontent.com/tanyigbo/Car-Catalogue-API/main/src/main/resources/images/review_images/";
            List<ReviewImage> reviewImages = Arrays.asList(
                    new ReviewImage(base_url + "Hyundai/Ioniq_5/review_01/image_01.webp", reviews.get(0)),
                    new ReviewImage(base_url + "Audi/e-tron_GT/review_01/image_01.webp", reviews.get(1)),
                    new ReviewImage(base_url + "BMW/i4/review_01/image_01.jpg", reviews.get(2)),
                    new ReviewImage(base_url + "Tesla/model_3/review_01/image_01.webp", reviews.get(3)),
                    new ReviewImage(base_url + "Tesla/model_s/review_01/image_01.webp", reviews.get(4)),
                    new ReviewImage(base_url + "Tesla/model_s/review_02/image_01.webp", reviews.get(5))
            );
            reviewImageRepository.saveAll(reviewImages);
        }
    }
}
