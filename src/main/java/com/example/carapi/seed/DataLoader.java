package com.example.carapi.seed;

import com.example.carapi.exception.InformationNotFound;
import com.example.carapi.model.Car;
import com.example.carapi.model.Manufacture;
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
            Manufacture audi = findManufactureByID(1L);
            Manufacture bmw = findManufactureByID(2L);
            Manufacture tesla = findManufactureByID(3L);
            Manufacture hyundai = findManufactureByID(4L);

            List<Car> cars = Arrays.asList(
                    new Car(1L, "e-tron GT RS", audi),
                    new Car(2L, "Q4 e-tron", audi),
                    new Car(3L, "i4 M50", bmw),
                    new Car(4L, "iX", bmw),
                    new Car(5L, "Model S", tesla),
                    new Car(6L, "Model X", tesla),
                    new Car(7L, "Model 3", tesla),
                    new Car(8L, "Ioniq 5", hyundai),
                    new Car(9L, "Ioniq 6", hyundai)
            );
            carRepository.saveAll(cars);
        }
    }
}
