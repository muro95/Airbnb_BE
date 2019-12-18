package com.codegym.airbnb.service;

import com.codegym.airbnb.model.Rate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RateService {
    List<Rate> findAllByHouseId(Long houseId);

    void createRate(Rate rate);

    boolean existsRateByUserIdAndHouseId (Long id, Long houseId);

    Rate findByUserIdAndHouseId(Long userId, Long houseId);
}
