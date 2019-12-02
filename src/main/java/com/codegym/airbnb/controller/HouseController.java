package com.codegym.airbnb.controller;

import com.codegym.airbnb.message.response.HouseDetail;
import com.codegym.airbnb.message.response.ResponseMessage;
import com.codegym.airbnb.model.House;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.HouseService;
import com.codegym.airbnb.service.ImageHouseService;
import com.codegym.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class HouseController {

    @Autowired
    private HouseService houseService;

//    @Autowired
//    private ImageHouseService imageHouseService;


    @Autowired
    private UserService userService;


    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
//
//    @Autowired
//    private StatusHouseService statusHouseService;

    @RequestMapping(value = "/houses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseMessage> listAllHouse(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        List<HouseDetail> houses = this.houseService.getListHouse(1,2);

        if (houses.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

//        for (House house : houses) {
//            List<String> listImageUrlOfHouse = imageHouseService.getListImageUrlOfHouseByHouseId(house.getId());
//            house.setImageUrls(listImageUrlOfHouse);
//            List<OrderHouse> orderHouses = orderHouseService.findOrderHousesByHouseId(house.getId());
//            house.setOrderHouses(orderHouses);
//        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list all house", houses),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/houses/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> getHouse(@PathVariable Long id) {
        House house = this.houseService.findById(id);

        if (house == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

//        List<String> listImageUrlOfHouse = imageHouseService.getListImageUrlOfHouseByHouseId(house.getId());
//        house.setImageUrls(listImageUrlOfHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get detail house", house),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/houses2/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseMessage> getHouseNative(@PathVariable Long id) {
        HouseDetail house = this.houseService.getHouseDetailById(id);

        if (house == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.OK);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get detail house", house),
                HttpStatus.OK);
    }
}