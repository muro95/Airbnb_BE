package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.CategoryList;
import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.message.response.HouseList;
import com.codegym.airbnb.model.House;
import com.codegym.airbnb.model.HouseEntity;
import com.codegym.airbnb.repository.HouseDao;
import com.codegym.airbnb.repository.HouseRepository;
import com.codegym.airbnb.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseDao houseDao;

    @Override
    public List<HouseEntity> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public List<HouseEntity> findByUser(Long userId) {
        return houseRepository.findByUser(userId);
    }

    @Override
    public HouseEntity findById(Long id) {
        return houseRepository.findById(id).get();
    }

    @Override
    public void createHouse(HouseEntity house) {
        houseRepository.save(house);
    }

    @Override
    public void updateHouse(HouseEntity house) {
        houseRepository.save(house);
    }

    @Override
    public void deleteHouse(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public void createHouseRQ(CreateHouseRequest house) {
        HouseEntity houseEntity = house.cloneToEntity();
        houseDao.insert(houseEntity);
    }

    @Override
    public HouseDetail getHouseDetailById(Long id) {
        return houseDao.getHouseDetailById(id);
    }

    @Override
    public List<HouseList> getListHouse(int page, int pageSize) {
        return houseDao.getListHouse(page,pageSize);
    }

    @Override
    public List<CategoryList> getListCategory() {
        return houseDao.getListCategory();
    }
}