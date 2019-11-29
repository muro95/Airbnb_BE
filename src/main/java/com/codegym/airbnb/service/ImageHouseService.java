package com.codegym.airbnb.service;

import com.codegym.airbnb.model.ImageOfHouse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageHouseService {
    List<ImageOfHouse> findAll();

    List<ImageOfHouse> findByHouseId(Long id);

    List<String> getListImageUrlOfHouseByHouseId(Long id);

    ImageOfHouse findById(Long id);

    void createImageHouse(ImageOfHouse imageOfHouse);

    void updateImageHouse(ImageOfHouse imageOfHouse);

    void deleteImageHouse(Long id);
}