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

    private void loadCarData() {
        if (carRepository.count() == 0) {
            List<Manufacturer> manufacturers = manufacturerRepository.findAll();
            List<Car> cars = Arrays.asList(
                    new Car(1L, "e-tron GT", manufacturers.get(0)),
                    new Car(2L, "Q4 e-tron", manufacturers.get(0)),
                    new Car(3L, "i4 M50", manufacturers.get(1)),
                    new Car(4L, "iX M60", manufacturers.get(1)),
                    new Car(5L, "Model S", manufacturers.get(3)),
                    new Car(6L, "Model 3", manufacturers.get(3)),
                    new Car(7L, "Ioniq 5", manufacturers.get(2))
            );
            carRepository.saveAll(cars);
        }
    }

    private void loadCarImageData() {
        if (imageRepository.count() == 0) {
            List<Car> cars = carRepository.findAll();
            String Base_URL = "https://raw.githubusercontent.com/tanyigbo/Car-Catalogue-API/front-end-changes/src/main/resources/images/car_images/";
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

    private void loadReviewData() {
        if (reviewRepository.count() == 0) {
            List<Car> cars = carRepository.findAll();
            List<Review> reviews = Arrays.asList(
                    new Review(1L, "Title 1", "Review Text 1", "Reviewer 1", cars.get(0)),
                    new Review(2L, "Title 2", "Review Text 2", "Reviewer 2", cars.get(1)),
                    new Review(3L, "Title 3", "Review Text 3", "Reviewer 3", cars.get(2)),
                    new Review(4L, "Title 4", "Review Text 4", "Reviewer 4", cars.get(4)),
                    new Review(5L, "Title 5", "Review Text 5", "Reviewer 5", cars.get(4)),
                    new Review(6L, "Title 6", "Review Text 6", "Reviewer 6", cars.get(4)),
                    new Review(7L, "Title 7", "Review Text 7", "Reviewer 7", cars.get(5)),
                    new Review(8L, "Title 8", "Review Text 8", "Reviewer 8", cars.get(5)),
                    new Review(9L, "Title 9", "Review Text 9", "Reviewer 9", cars.get(6))
            );
            reviewRepository.saveAll(reviews);
        }
    }

    private void loadReviewImageData() {
        if (reviewImageRepository.count() == 0) {
            List<Review> reviews = reviewRepository.findAll();
            List<ReviewImage> reviewImages = Arrays.asList(
                    new ReviewImage(1L, "reviewImg01.png", reviews.get(0)),
                    new ReviewImage(2L, "reviewImg02.png", reviews.get(2)),
                    new ReviewImage(3L, "reviewImg03.png", reviews.get(2)),
                    new ReviewImage(4L, "reviewImg04.png", reviews.get(3)),
                    new ReviewImage(5L, "reviewImg05.png", reviews.get(3)),
                    new ReviewImage(6L, "reviewImg06.png", reviews.get(3)),
                    new ReviewImage(7L, "reviewImg07.png", reviews.get(4)),
                    new ReviewImage(8L, "reviewImg08.png", reviews.get(5)),
                    new ReviewImage(9L, "reviewImg09.png", reviews.get(5)),
                    new ReviewImage(10L, "reviewImg10.png", reviews.get(6)),
                    new ReviewImage(11L, "reviewImg11.png", reviews.get(7)),
                    new ReviewImage(12L, "reviewImg12.png", reviews.get(8))
            );
            reviewImageRepository.saveAll(reviewImages);
        }
    }
}
