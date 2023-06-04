package com.example.carapi.seed;

import com.example.carapi.model.Manufacture;
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
    }

    private void loadManufactureData(){
        if(manufactureRepository.count() == 0){
            List<Manufacture> manufactures = Arrays.asList(
                    new Manufacture(1L, "Audi", "Germany"),
                    new Manufacture(2L, "Porsche", "Germany"),
                    new Manufacture(3L, "Tesla", "United States"),
                    new Manufacture(4L, "Hyundai", "South Korea")
            );
            manufactureRepository.saveAll(manufactures);
        }
    }
}
