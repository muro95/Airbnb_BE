package com.codegym.airbnb.service;

import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.CategoryList;
import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.message.response.HouseList;
import com.codegym.airbnb.model.House;
import com.codegym.airbnb.model.HouseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseService {

    List<HouseEntity> findAll();

    List<HouseEntity> findByUser(Long userId);

    HouseEntity findById(Long id);

    void createHouse(HouseEntity house);

    void updateHouse(HouseEntity house);

    void deleteHouse(Long id);

    void createHouseRQ(CreateHouseRequest house);

    HouseDetail getHouseDetailById(Long id);

    List<HouseList> getListHouse(int page, int pageSize);

    List<CategoryList> getListCategory();
}