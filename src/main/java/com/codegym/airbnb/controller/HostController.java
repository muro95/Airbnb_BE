package com.codegym.airbnb.controller;


import com.codegym.airbnb.message.request.CreateHouseRequest;
import com.codegym.airbnb.message.response.ResponseMessage;
import com.codegym.airbnb.model.Category;
import com.codegym.airbnb.model.House;
import com.codegym.airbnb.model.ImageOfHouse;
import com.codegym.airbnb.security.services.UserPrinciple;
import com.codegym.airbnb.service.CategoryService;
import com.codegym.airbnb.service.HouseService;
import com.codegym.airbnb.service.ImageHouseService;
import com.codegym.airbnb.service.UserService;
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
    private HouseService houseService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private ImageHouseService imageHouseService;

    @Autowired
    private CategoryService categoryService;

    private UserPrinciple getCurrentUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    @GetMapping("/houses")
    @PreAuthorize("hasRole('HOST') or hasRole('ADMIN')")
    public ResponseEntity<ResponseMessage> listHouseOfHost() {
        long userId = getCurrentUser().getId();
        List<House> houses = houseService.findByUserId(userId);
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
    public ResponseEntity<ResponseMessage> editHouse(@RequestBody House house, @PathVariable Long id) {
        Category category=categoryService.findByName(house.getCategory().getName());
        house.setCategory(category);

        House currentHouse = this.houseService.findById(id);

        if (currentHouse == null) {
            return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage(false, "Fail. Not found", null),
                    HttpStatus.NOT_FOUND);
        }
        //no update id for house
        currentHouse.setHouseName(house.getHouseName());
        currentHouse.setCategory(house.getCategory());
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
        House house = this.houseService.findById(id);

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

}
