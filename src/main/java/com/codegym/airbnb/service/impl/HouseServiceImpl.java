package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.model.House;
import com.codegym.airbnb.repository.HouseRepository;
import com.codegym.airbnb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public List<House> findByUserId(long userId) {
        return houseRepository.findByUserId(userId);
    }

    @Override
    public House findById(Long id) {
        return houseRepository.findById(id).get();
    }

    @Override
    public void createHouse(House house) {
        houseRepository.save(house);
    }

    @Override
    public void updateHouse(House house) {
        houseRepository.save(house);
    }

    @Override
    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }
}