package com.example.carapi.repository;

import com.example.carapi.model.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture,Long> {
}
