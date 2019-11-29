package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.ImageOfHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageHouseRepository extends JpaRepository<ImageOfHouse,Long> {
    List<ImageOfHouse> findByHouseId(Long id);
}

