package com.codegym.airbnb.service;

import com.codegym.airbnb.message.response.HouseListOfHost;

import java.util.List;

public interface HostService {
    List<HouseListOfHost> getHouseListOfHosts(Long userId);
}
