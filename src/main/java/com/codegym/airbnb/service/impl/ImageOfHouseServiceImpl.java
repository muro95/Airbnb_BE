package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.model.ImageOfHouse;
import com.codegym.airbnb.repository.ImageHouseRepository;
import com.codegym.airbnb.service.ImageHouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ImageOfHouseServiceImpl implements ImageHouseService {

    @Autowired
    private ImageHouseRepository imageHouseRepository;

    @Override
    public List<ImageOfHouse> findAll() {
        return imageHouseRepository.findAll();
    }

    @Override
    public List<ImageOfHouse> findByHouseId(Long id) {
        return imageHouseRepository.findByHouseId(id);
    }

    @Override
    public List<String> getListImageUrlOfHouseByHouseId(Long id) {
        List<String> listImageUrl=new ArrayList<>();
        List<ImageOfHouse> imageOfHouses=imageHouseRepository.findByHouseId(id);
        for (ImageOfHouse image:imageOfHouses){
            listImageUrl.add(image.getImageUrl());
        }
        return listImageUrl;
    }

    @Override
    public ImageOfHouse findById(Long id) {
        return imageHouseRepository.findById(id).get();
    }

    @Override
    public void createImageHouse(ImageOfHouse imageOfHouse) {
        imageHouseRepository.save(imageOfHouse);
    }

    @Override
    public void updateImageHouse(ImageOfHouse imageOfHouse) {
        imageHouseRepository.save(imageOfHouse);
    }

    @Override
    public void deleteImageHouse(Long id) {
        imageHouseRepository.deleteById(id);
    }
}
