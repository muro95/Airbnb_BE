package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.message.response.HouseListOfHost;
import com.codegym.airbnb.repository.HouseDao;
import com.codegym.airbnb.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HostServiceImpl implements HostService {
    @Autowired
    HouseDao houseDao;

    @Override
    public List<HouseListOfHost> getHouseListOfHosts(Long userId) {
        return houseDao.getListHouseOfHost(userId);
    }
}
