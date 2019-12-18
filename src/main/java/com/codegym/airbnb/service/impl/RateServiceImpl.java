package com.codegym.airbnb.service.impl;

import com.codegym.airbnb.model.Rate;
import com.codegym.airbnb.repository.RateRepository;
import com.codegym.airbnb.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public List<Rate> findAllByHouseId(Long houseId) {
        return this.rateRepository.findAllByHouseId(houseId);
    }

    @Override
    public void createRate(Rate rate) {
        this.rateRepository.save(rate);
    }

    @Override
    public boolean existsRateByUserIdAndHouseId(Long id, Long houseId) {
        return this.rateRepository.existsRateByUserIdAndHouseId(id, houseId);
    }

    @Override
    public Rate findByUserIdAndHouseId(Long userId, Long houseId) {
        return rateRepository.findByUserIdAndHouseId(userId, houseId);
    }
}
