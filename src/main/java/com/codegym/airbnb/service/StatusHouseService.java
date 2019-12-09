package com.codegym.airbnb.service;

import com.codegym.airbnb.model.StatusHouse;

import java.util.List;

public interface StatusHouseService {
    List<StatusHouse> findAll();

    StatusHouse findById(Long id);

    void save(StatusHouse statusHouse);

    void deleteById(Long id);

    List<StatusHouse> findAllByHouseId(Long houseId);
}
