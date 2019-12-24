package com.codegym.airbnb.controller;


import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.HouseListOfHost;
import com.codegym.airbnb.message.response.ResponseMessage;
import com.codegym.airbnb.message.response.UserOrderList;
import com.codegym.airbnb.message.response.UserOrderListOfHost;
import com.codegym.airbnb.model.*;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/host")
public class HostController {

    @Autowired
    private HostService hostService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private ImageHouseService imageHouseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderHouseService orderHouseService;

    @Autowired
    private StatusHouseService statusHouseService;

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/statusHouses/{id}")
    public ResponseEntity<ResponseMessage> getStatusHouseById(@PathVariable Long id) {
        StatusHouse statusHouse = this.statusHouseService.findById(id);

        if (statusHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true,"Get the status house successfully",statusHouse),
                HttpStatus.OK);
    }

    @PutMapping("/statusHouses/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseMessage> editStatusHouse(@RequestBody StatusHouse statusHouse, @PathVariable Long id) {
        StatusHouse currentStatusHouse = this.statusHouseService.findById(id);

        if (currentStatusHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }

        //no update id for StatusHouse
        currentStatusHouse.setStartDate(statusHouse.getStartDate());
        currentStatusHouse.setEndDate(statusHouse.getEndDate());

        this.statusHouseService.save(currentStatusHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Update the status house successfully", null),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("/statusHouses")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseMessage> createStatusHouse(@RequestBody StatusHouse statusHouse) {
        this.statusHouseService.save(statusHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Post a new status house successfully", null),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/statusHouses/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseMessage> deleteStatusHouse(@PathVariable Long id) {
        StatusHouse statusHouse = this.statusHouseService.findById(id);

        if (statusHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }

        this.statusHouseService.deleteById(id);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true,"Delete the status house successfully",null),
                HttpStatus.OK);
    }

    @GetMapping("/houses")
    @PreAuthorize("hasRole('HOST') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> listHouseOfHost() {
        long userId = getCurrentUser().getId();
//        List<HouseEntity> houses = houseService.findByUser(userId);
        List<HouseListOfHost> houses = hostService.getHouseListOfHosts(userId);
        if (houses.isEmpty()) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found data", null),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Successfully. Get list house of host", houses),
                HttpStatus.OK);
    }

//    @PostMapping("/houses")
//    @PreAuthorize("hasRole('HOST')")
//    public ResponseEntity<ResponseMessage> createHouse(@RequestBody List<ImageOfHouse> imageOfHouses) {
//        //find category
//        String typeName = imageOfHouses.get(0).getHouse().getCategory().getName();
//        Category category = categoryService.findByName(typeName);
//        //save house
//        House house = imageOfHouses.get(0).getHouse();
////        house.setStatus(Status.AVAILABLE);
//        house.setCategory(category);
//        house.setUser(userService.findByUsername(getCurrentUser().getUsername()));
//        this.houseService.createHouse(house);
//        //save image of house
////        for (ImageOfHouse imageOfHouse : imageOfHouses) {
////            imageOfHouse.setHouse(house);
////            this.imageHouseService.createImageHouse(imageOfHouse);
////        }
//        return new ResponseEntity<ResponseMessage>(
//                new ResponseMessage(true, "Post a new house successfully", null),
//                HttpStatus.CREATED);
//    }
@PostMapping("/houses")
@PreAuthorize("hasRole('HOST')")
public ResponseEntity<ResponseMessage> createHouse(@RequestBody CreateHouseRequest house) {

        houseService.createHouseRQ(house);

    //find category
//    String typeName = imageOfHouses.get(0).getHouse().getCategory().getName();
//    Category category = categoryService.findByName(typeName);
    //save house
//    House house = imageOfHouses.get(0).getHouse();
//        house.setStatus(Status.AVAILABLE);
//    house.setCategory(category);
//    house.setUser(userService.findByUsername(getCurrentUser().getUsername()));
//    this.houseService.createHouse(house);
//

    //save image of house
//        for (ImageOfHouse imageOfHouse : imageOfHouses) {
//            imageOfHouse.setHouse(house);
//            this.imageHouseService.createImageHouse(imageOfHouse);
//        }
    return new ResponseEntity<ResponseMessage>(
            new ResponseMessage(true, "Post a new house successfully", null),
            HttpStatus.CREATED);
}

    @PutMapping("/houses/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseMessage> editHouse(@RequestBody HouseEntity house, @PathVariable Long id) {
//        Category category=categoryService.findByName(house.getCategory().getName());
//        house.setCategory(category);

        HouseEntity currentHouse = this.houseService.findById(id);

        if (currentHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }
        //no update id for house
        currentHouse.setHouseName(house.getHouseName());
        currentHouse.setCategory(house.getCategory());
        currentHouse.setPicture(house.getPicture());
        currentHouse.setAddress(house.getAddress());
        currentHouse.setBedroomNumber(house.getBedroomNumber());
        currentHouse.setBathroomNumber(house.getBathroomNumber());
        currentHouse.setDescription(house.getDescription());
        currentHouse.setPrice(house.getPrice());
//        currentHouse.setRate(house.getRate());
        currentHouse.setArea(house.getArea());

        this.houseService.updateHouse(currentHouse);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true, "Update successfully", null),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/houses/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<ResponseMessage> deleteHouse(@PathVariable Long id) {
        HouseEntity house = this.houseService.findById(id);

        if (house == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }

        this.houseService.deleteHouse(id);
        return new ResponseEntity<ResponseMessage>(
                new ResponseMessage(true,"Delete the house successfully",null),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/house/orderOfUser/",method = RequestMethod.GET)
    @PreAuthorize("hasRole('HOST')")
    public  ResponseEntity<ResponseMessage> getHouseOrderByUser(){
        long userId = getCurrentUser().getId();
        List<UserOrderListOfHost> userOrderListsOfHost = this.orderHouseService.userOrderListOfHost(userId);
//        List<OrderHouse> orderHouses = orderHouseService.findOrderHousesByHouseId(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true,"list all order",userOrderListsOfHost),HttpStatus.OK);
    }

}
