package com.example.carapi.seed;

import com.example.carapi.exception.InformationNotFound;
import com.example.carapi.model.Car;
import com.example.carapi.model.Manufacture;
import com.example.carapi.model.Review;
import com.example.carapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final CarRepository carRepository;
    private final ImageRepository imageRepository;
    private final ManufactureRepository manufactureRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Autowired
    public DataLoader(CarRepository carRepository, ImageRepository imageRepository, ManufactureRepository manufactureRepository, ReviewRepository reviewRepository, ReviewImageRepository reviewImageRepository) {
        this.carRepository = carRepository;
        this.imageRepository = imageRepository;
        this.manufactureRepository = manufactureRepository;
        this.reviewRepository = reviewRepository;
        this.reviewImageRepository = reviewImageRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadManufactureData();
        loadCarData();
        loadReviewData();
    }

    private Manufacture findManufactureByID(Long id) {
        Optional<Manufacture> manufactures = manufactureRepository.findById(id);
        if (manufactures.isPresent()) {
            return manufactures.get();
        }
        throw new InformationNotFound("Manufacture Not Found.");
    }

    private void loadManufactureData() {
        if (manufactureRepository.count() == 0) {
            List<Manufacture> manufactures = Arrays.asList(
                    new Manufacture(1L, "Audi", "Germany"),
                    new Manufacture(2L, "BMW", "Germany"),
                    new Manufacture(3L, "Tesla", "United States"),
                    new Manufacture(4L, "Hyundai", "South Korea")
            );
            manufactureRepository.saveAll(manufactures);
        }
    }

    private void loadCarData() {
        if (carRepository.count() == 0) {
            List<Manufacture> manufactures = manufactureRepository.findAll();
            List<Car> cars = Arrays.asList(
                    new Car(1L, "e-tron GT RS", manufactures.get(0)),
                    new Car(2L, "Q4 e-tron", manufactures.get(0)),
                    new Car(3L, "i4 M50", manufactures.get(1)),
                    new Car(4L, "iX M60", manufactures.get(1)),
                    new Car(5L, "Model S", manufactures.get(2)),
                    new Car(6L, "Model X", manufactures.get(2)),
                    new Car(7L, "Model 3", manufactures.get(2)),
                    new Car(8L, "Ioniq 5", manufactures.get(3)),
                    new Car(9L, "Ioniq 6", manufactures.get(3))
            );
            carRepository.saveAll(cars);
        }
    }

    private void loadReviewData(){
        if(reviewRepository.count() == 0){
            List<Car> cars = carRepository.findAll();
            List<Review> reviews = Arrays.asList(
              new Review(1L,"Title 1","Review Text 1", "Reviewer 1",cars.get(0)),
              new Review(2L,"Title 2","Review Text 2", "Reviewer 2",cars.get(1)),
              new Review(3L,"Title 3","Review Text 3", "Reviewer 3",cars.get(2)),
              new Review(4L,"Title 4","Review Text 4", "Reviewer 4",cars.get(4)),
              new Review(5L,"Title 5","Review Text 5", "Reviewer 5",cars.get(4)),
              new Review(6L,"Title 6","Review Text 6", "Reviewer 6",cars.get(5)),
              new Review(7L,"Title 7","Review Text 7", "Reviewer 7",cars.get(6)),
              new Review(8L,"Title 8","Review Text 8", "Reviewer 8",cars.get(6)),
              new Review(9L,"Title 9","Review Text 9", "Reviewer 9",cars.get(6)),
              new Review(10L,"Title 10","Review Text 10", "Reviewer 10",cars.get(6)),
              new Review(11L,"Title 11","Review Text 11", "Reviewer 11",cars.get(7))
            );
            reviewRepository.saveAll(reviews);
        }
    }
}
