package com.codegym.airbnb.repository;

import com.codegym.airbnb.model.StatusHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Repository
public interface StatusHouseRepository extends JpaRepository<StatusHouse, Long> {
    List<StatusHouse> findAllByHouseId(Long houseId);
}
